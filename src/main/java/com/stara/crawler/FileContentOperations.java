package com.stara.crawler;

public interface FileContentOperations {
	public String getContent(String path);
	public void storage(String url,String dir,int index);
}
