package com.youzitech.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.youzitech.crawler.util.HttpProxool;


/**
 *爬虫的基类
 * 
 * @author heliang.zhu
 */
public abstract class BaseCrawler {

    public abstract void start();
    
  
    public String doGet(String url, String... params) throws Exception {
        HttpClient httpclient = HttpProxool.getHttpClient();
        String content = "";
        try {
            String charset = "UTF-8";
            if (null != params && params.length >= 1) {
                charset = params[0];
            }
            HttpGet httpget = new HttpGet();
            httpget.setURI(new java.net.URI(url));
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 使用EntityUtils的toString方法，传递默认编码，在EntityUtils中的默认编码是ISO-8859-1
                content = EntityUtils.toString(entity, charset);
            }
            httpget.abort();
        } finally {
            HttpProxool.releaseHttpClientPool(httpclient);
        }
        return content;
    }
}
