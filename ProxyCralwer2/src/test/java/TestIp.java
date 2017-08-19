

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import cn.itcast.cralwer.service.GetJpg;
import cn.itcast.cralwer.service.ProxyCralwerService;
import cn.itcast.domain.ProxyInfo;
import cn.itcast.utils.IPCom;
import cn.itcast.utils.getThreadLocal;

public class TestIp {
	@Test
	public void test() throws IOException{
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "39.71.138.98");
		System.setProperty("http.proxyPort", "8118");
		
		URL url = new URL("http://www.baidu.com");  
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();  
	    InputStreamReader isr = new InputStreamReader(con.getInputStream());  
	    char[] cs = new char[1024];  
	    int i = 0;  
	    while ((i = isr.read(cs)) > 0) {  
	        System.out.println(new String(cs, 0, i));  
	    } 
	    System.getSecurityManager().checkAccept("39.71.138.98", 8118);
	   System.out.println(con.getHeaderFields());
	    isr.close();
	}
	@Test
	public void test1(){
		
		String[] str={"110.216.60.43","49.87.49.38","182.246.205.42","116.196.5.150","122.72.32.73",
				"139.208.199.232","42.231.251.200","122.72.32.74","171.116.69.61","58.217.88.188"};
		int[] port={80,8118,80,808,80,8118,80,80,80,8118};
		for(int i=0;i<10;i++){
			  boolean checkedIp = IPCom.checkedIp(str[i], port[i]);
			   System.out.println(checkedIp);
		}
	 
	}
	@Test
	public void test2(){
	   //ProxyCralwerService.proxyCralwer(10);
		final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("122.72.32.73", 80));
	   //List<ProxyInfo> list = (List<ProxyInfo>) getThreadLocal.getCurrentTL().get();
	   ProxyInfo proxyInfo = new ProxyInfo("114.234.97.156","8118","http");
	   List<String> start = GetJpg.Start(proxyInfo, "http://www.bzl9.com/video/4132.html?4132-0-0",15);
	   for (String img : start) {
		   System.out.println(img);
		/*   try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*  tread t = new tread(img, proxy, 'A'+img.substring(img.lastIndexOf("/")+1));
		  t.start();*/
		
	}
	}
	@Test
	public void test3(){
		ProxyCralwerService.proxyCralwer(2);
	}
	
}
