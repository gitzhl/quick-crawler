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
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stara.crawler.util.DateFormatter;
import com.stara.crawler.util.PropertiesUtils;

public class FileCrawler extends BaseCrawler implements FileContentOperations {
	
	private JTextArea jar;
	public FileCrawler(JTextArea jar){
		this.jar = jar;
	}
	public FileCrawler(){
	}
	private String fileSource = null;
	private String fileStorage = null;
	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}
	public void setFileStorage(String fileStorage) {
		this.fileStorage = fileStorage;
	}
	@Override
	public void start() {
		String content = null;
		if(fileSource == null){
			content = getContent(PropertiesUtils.getProperty("file.source"));
		}else{
			content = getContent(fileSource);
		}
		Document document = Jsoup.parse(content);
		Elements lis = document.select("img");
		Iterator<Element> iterator = lis.iterator();
		String dir = DateFormatter.format(new Date(), "yyyyMMddhhmmss");
		int count = 0;
		while (iterator.hasNext()) {
			Element element = iterator.next();
			String href = element.attr("src");
			if(!href.startsWith("http")&&!href.startsWith("https")){
				href = element.attr("data-ks-lazyload");
			}
			storage(href,dir,count);
			count++;
		}
		System.out.println(">>>>>>>>>>>>>>>>共下载图片【"+count+"】张");

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

	public void storage(String imageUrl,String dir,int index) {
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
			String storage = fileStorage == null?PropertiesUtils.getProperty("file.storage"):fileStorage;
			File sf = new File(storage+File.separator+dir);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			String rp = sf.getPath() + "\\"+System.currentTimeMillis()+"."+imageUrl.substring(imageUrl.lastIndexOf(".")+1);
			jar.append("第"+(++index)+"个文件>>>>>【"+imageUrl+"】保存地址>>>>>>"+rp+"\n");
			System.out.println("第"+(++index)+"个文件>>>>>【"+imageUrl+"】保存地址>>>>>>"+rp);
			os = new FileOutputStream(rp);
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
