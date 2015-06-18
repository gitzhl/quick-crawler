package com.stara.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stara.crawler.util.PropertiesUtils;

public class FileCrawler extends BaseCrawler implements FileContentOperations {

	@Override
	public void start() {
		String content = getContent(PropertiesUtils.getProperty("file.source"));
		Document document = Jsoup.parse(content);
		Elements lis = document.select("img");
		Iterator<Element> iterator = lis.iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			String href = element.attr("src");
			if(!href.startsWith("http")&&!href.startsWith("https")){
				href = element.attr("data-ks-lazyload");
			}
			storage(href);
		}

	}

	public String getContent(String path) {
		StringBuilder sb = new StringBuilder();
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public void storage(String imageUrl) {
		URL url = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			url = new URL(imageUrl);
			URLConnection con = url.openConnection();
			con.setConnectTimeout(5 * 1000);
			is = con.getInputStream();
			byte[] bs = new byte[1024];
			int len;
			File sf = new File(PropertiesUtils.getProperty("file.storage"));
			if (!sf.exists()) {
				sf.mkdirs();
			}
			os = new FileOutputStream(sf.getPath() + "\\"+System.currentTimeMillis()+"."+imageUrl.substring(imageUrl.lastIndexOf(".")+1));
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws Exception {
		new FileCrawler().start();
	}

}
