package cn.itcast.cralwer.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.itcast.domain.ProxyInfo;
import cn.itcast.utils.getThreadLocal;

//通过代理方式获取ip，然后多线程趴图片
public class GetJpg {
	private static Properties prop;
	private static InputStream is;
	 static{
	     is = GetJpg.class.getResourceAsStream("header.properties");
	     prop = new Properties();
	    
	 }
	   
	
	public void getJpg(int num){
	   ProxyCralwerService.proxyCralwer(num);
	   List<ProxyInfo> proxyIp = (List<ProxyInfo>) getThreadLocal.getCurrentTL().get();
	   for (ProxyInfo proxyInfo : proxyIp) {
		  
	  }
	}
	
	public static List<String> Start(ProxyInfo proxyInfo,String url,int num){
		String reg="\\ting\\w";
		Pattern regex = Pattern.compile(reg);
		List<String> list=new ArrayList<String>();
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", proxyInfo.getIp());
		System.setProperty("http.proxyPort", proxyInfo.getPort());
		 //获取页面
		String src=null;
		 try {
			//String base=url;
			 prop.load(is);
			/*for(int i=1;i<num;i++){*/
				/*if(i>=2){
					url=base+"index_"+i+".shtml";
				}*/
		     Document doc = Jsoup.connect(url)
		      .header("Accept", prop.getProperty("Accept"))
		  /*    .header("Accept-Encoding", prop.getProperty("Accept-Encoding"))
		      .header("Accept-Language", prop.getProperty("Accept-Language"))*/
		      /*.header("Cache-Control",prop.getProperty("Cache-Control")) */
		      .header("User-Agent", prop.getProperty("User-Agent"))
		      /*.header("Cookie", prop.getProperty("Cookie"))*/
		    /* .header("Host", prop.getProperty("Host"))*/
		      /*.header("Referer", prop.getProperty("Referer"))*/
		      .timeout(300000)
		      .get();
		      Elements result = doc.getElementsByClass("player_l");
		        for (Element href : result) {
					Matcher m = regex.matcher(href.attr("href"));
					if(m.find()){
						list.add(m.group());
					}
					
				}
		   
		   /*  
			 }*/
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		 
		 //获取一个流读取属性文件
		
		
	}
	
	/**
	 * 开启线程
	 */
	
	private void ThreadStart(Proxy proxy,String url1){
		
	}
	
}
