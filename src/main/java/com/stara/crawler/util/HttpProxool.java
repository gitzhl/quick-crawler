package com.stara.crawler.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpProxool {

	private static final Queue<HttpClient> httpClientPool = new ConcurrentLinkedQueue<HttpClient>();

	private static Lock lock = new ReentrantLock();

	private static final Condition lockAwait = lock.newCondition();

	private static final Condition lockSignal = lock.newCondition();

	private static int AWAITE_TIME = 1000;

	private static int TIMEROUT_COUNT = 5;

	private static int MIN_CONNECTION = 5;

	private static int MAX_CONNECTION = 10;


	private static AtomicInteger CURRENT_CONNECTION = new AtomicInteger();
	static {
		try {
			MIN_CONNECTION = Integer.parseInt(PropertiesUtils.getProperty("httpproxool.min.size"));
			MAX_CONNECTION = Integer.parseInt(PropertiesUtils.getProperty("httpproxool.max.size"));
			for (int i = 0; i < MIN_CONNECTION; i++) {
				httpClientPool.add(createHttpClient());
				CURRENT_CONNECTION.addAndGet(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分配连接池
	 * 
	 * @return
	 */
	public static HttpClient getHttpClient() {
		lock.lock();
		boolean await = false;
		try {
			int timeout = 0;
			while (httpClientPool.isEmpty() && timeout < TIMEROUT_COUNT) {
				if (CURRENT_CONNECTION.get() >= MAX_CONNECTION) {
					await = true;
					// 等待
					lockAwait.await(AWAITE_TIME, TimeUnit.MILLISECONDS);
					timeout++;
				} else {
					await = false;
					try {
						httpClientPool.add(createHttpClient());
						CURRENT_CONNECTION.addAndGet(1);
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (await) {
				lockSignal.signal();
			}
			lock.unlock();
		}
		if (httpClientPool.isEmpty()) {
			return null;
		}
		return httpClientPool.poll();
	}

	private static HttpClient createHttpClient() {
		// 设置超时时间为20秒,设置连接超时时间为60秒
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000 * 20).setConnectTimeout(1000 * 30)
				.build();
		// 伪装成谷歌浏览器
		String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
				.setUserAgent(userAgent).build();
		return httpClient;
	}

	/**
	 * 将httpClient放回到池中
	 * 
	 * @param httpClient
	 */
	public static void releaseHttpClientPool(HttpClient httpClient) {
		if (httpClient == null) {
			return;
		}
		httpClientPool.add(httpClient);
	}
}
