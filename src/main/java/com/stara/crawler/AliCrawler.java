package com.stara.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stara.crawler.util.DateFormatter;
import com.stara.crawler.util.PropertiesUtils;

public class AliCrawler extends BaseCrawler implements FileResovleAware{
	
	@Override
	public void start() {
		try {
			String content = readFile(PropertiesUtils.getProperty("file.source"));
			String resourceSave = PropertiesUtils.getProperty("file.storage");
			if(StringUtils.isNotBlank(content)){
				Document document = Jsoup.parse(content);
				Elements lis = document.select("img");
				Iterator<Element> iterator = lis.iterator();
				String kindPath = DateFormatter.format(new Date(), "yyyyMMddHHmmss");
				System.out.println(kindPath);
				while (iterator.hasNext()) {
					Element element = iterator.next();
					String url = element.attr("src");
					System.out.println(url+"fdsdfsd");
					String suffix = StringUtils.lowerCase(StringUtils.substring(url, StringUtils.lastIndexOf(url, Character.valueOf('.'))-1));
					resourceStorage(url, System.currentTimeMillis()+suffix, resourceSave+"/"+kindPath);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void resourceStorage(String url, String fileName,String savePath) {
        URL url2;
		try {
			url2 = new URL(url);
			URLConnection con = url2.openConnection();  
	        con.setConnectTimeout(5*1000);  
	        InputStream is = con.getInputStream();  
	        byte[] bs = new byte[1024];  
	        int len;  
	       File sf=new File(savePath);  
	       if(!sf.exists()){  
	           sf.mkdirs();  
	       }  
	       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+fileName);
	        while ((len = is.read(bs)) != -1) {  
	          os.write(bs, 0, len);  
	        }  
	        os.close();  
	        is.close();  
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        
	}
	
	public static void main(String[] args) {
		//new AliCrawler().storage("http://p0.ifengimg.com/haina/2016_19/933e73cc0a51f0e_w389_h250.jpg", "123.jpg", "d://savePath");
		//String content = new AliCrawler().readFile("d://savePath/weizexi.txt");
		//System.out.println(content);
		new AliCrawler().start();
	}


	@Override
	public String readFile(String path) {
		File dataFile = new File(path);
		if(dataFile.exists()&&!dataFile.isDirectory()){
			StringBuilder builder = new StringBuilder();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile),"GBK"));
				String line = null;
				while ((line = reader.readLine())!=null) {
					builder.append(line);
				}
				reader.close();
				return builder.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
