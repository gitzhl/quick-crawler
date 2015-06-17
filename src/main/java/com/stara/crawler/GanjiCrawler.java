package com.stara.crawler;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 赶集网爬虫
 *
 * @author zhijun.zhang
 *
 *         2013-9-10
 */
public class GanjiCrawler extends BaseCrawler {

	private Logger logger = Logger.getLogger(getClass());

	//set the detail url
	String url = "";

	//set the website baseutl
	String baseUrl = "";

	@Override
	public void start() {
		String content = null;
		try {
			content = doGet(url);
		} catch (Exception e) {
			logger.error("获取页面数据失败,url = " + url, e);
			return;
		}

		Document document = Jsoup.parse(content);
		Elements lis = document.select(".list-img");
		Iterator<Element> iterator = lis.iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			String title = element.select(".f14").text();
			String url = element.select(".f14").attr("href");
			String tel = element.select(".tel").text();
			String intro = element.select(".intro").text();
			String img = element.select(".pic a img").attr("src");
			System.out.println(title + " " + tel + "  " + intro + "  " + img + "  " + url);
			getDetail(baseUrl + url);
		}
	}

	public void getDetail(String url) {
		String content = null;
		try {
			content = doGet(url);
		} catch (Exception e) {
			logger.error("获取页面数据失败,url = " + url, e);
			return;
		}
		Document document = Jsoup.parse(content);
		String contentHtml = document.select("#part_desc").html();
		System.out.println(contentHtml);
	}

	public static void main(String[] args) throws Exception {
		new GanjiCrawler().start();
	}

}
