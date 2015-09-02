package com.stara.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * 
 * @author zhl
 * @time 2015-9-2 下午4:19:59
 */
public class WebClientMain {
    
    private static final WebClient WEB_CLIENT = new WebClient();
    
    static{
        WEB_CLIENT.getOptions().setCssEnabled(false);
        WEB_CLIENT.getOptions().setJavaScriptEnabled(false);
    }
    
    public static String asText(Map<String, String> params,String url){
        HtmlPage page = null;
        try {
            page = WEB_CLIENT.getPage(url);
            closeWebClient(WEB_CLIENT);
            return page.asText();
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String asXML(Map<String, String> params,String url){
        HtmlPage page = null;
        try {
            page = WEB_CLIENT.getPage(url);
            closeWebClient(WEB_CLIENT);
            return page.asXml();
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void closeWebClient(WebClient webClient){
        webClient.close();
    }
}
