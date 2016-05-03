package com.youzitech.crawler.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;

/**
 * 
 * @author zhuheliang
 * @time 2015-9-2 上午9:25:10
 */
public class HttpClientPool {
    
    /**
     * httpClient池
     */
    private static final Queue<HttpClient> HTTP_CLIENT_POOL = new ConcurrentLinkedQueue<HttpClient>();
    
    private static Lock lock = new ReentrantLock();
    
    private static final Condition AWAIT_CONDITION = lock.newCondition();
    
    private static final Condition SIGNAL_CONDITION = lock.newCondition();
    
    private static AtomicInteger currentConnection = new AtomicInteger(); //当前连接原子
    
    private static int minConnection = 5;
    
    private static int maxConnection = 10;
    
    private static int timeoutCount = 5;
    
    private static int awaitTime = 1000;
    
    static{
        for (int i = 0; i < minConnection; i++) {
            HTTP_CLIENT_POOL.add(createHttpClient());
            currentConnection.addAndGet(1);
        }
    }
    
    /**
     * @return 返回连接对象
     */
    public static HttpClient getHttpClient(){
        lock.lock();//锁定
        boolean await = false;
        try {
            int timeout = 0;
            while (HTTP_CLIENT_POOL.isEmpty()&&timeout<timeoutCount) {
                if(currentConnection.get()>=maxConnection){//当前连接数大于最大连接数
                    await = true;//等待
                    AWAIT_CONDITION.await(awaitTime, TimeUnit.MILLISECONDS);//等待1000毫秒
                    timeout++;
                }else{
                    await = false;
                    HTTP_CLIENT_POOL.add(createHttpClient());
                    currentConnection.addAndGet(1);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            if(await)SIGNAL_CONDITION.signal();
            lock.unlock();
        }
        if(HTTP_CLIENT_POOL.isEmpty())return null;
        return HTTP_CLIENT_POOL.poll();
    }
    
    /**
     * @return 创建连接对象
     */
    private static HttpClient createHttpClient() {
        RequestConfig config =
                RequestConfig.custom().setSocketTimeout(20 * 1000).setConnectTimeout(30 * 1000)
                        .build();
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";//更新浏览器版本
        HttpClient httpClient =
                HttpClients.custom().setDefaultRequestConfig(config).setUserAgent(userAgent).build();
        return httpClient;
    }
    
    /**
     * 
     * @param httpClient 释放连接对象
     */
    public static void releaseHttpClientPool(HttpClient httpClient){
        if (httpClient == null) return;
        HTTP_CLIENT_POOL.add(httpClient);
    }
}
