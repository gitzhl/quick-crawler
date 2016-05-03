package com.youzitech.crawler;

public interface FileResovleAware {
	public String readFile(String path);
	public void resourceStorage(String url,String fileName,String dir);
}
