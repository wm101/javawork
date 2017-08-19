package cn.itcast.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.itcast.domain.ProxyInfo;

//获取ip,传入base url
public class IPCom {
     
	public static void getIp(String url,int num){
		//读取配置文件
		try{
		    InputStream ras = IPCom.class.getResourceAsStream("header.properties");
		    Properties prop = new Properties();
			prop.load(ras);
		
		//定义匹配规则
		String ipReg="\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{1,6}";
	    Pattern compile = Pattern.compile(ipReg);
	    //循环获取ip
	    ThreadLocal tlNum = getThreadLocal.getTlNum();
	    for(int i=1;i<num;i++){
	    	
			//判断当前线程已有的ip数是否已经达到想要的ip
	    	if(getCurrentIpSize() > (Integer)tlNum .get()){
	    		return;
	    	}
	    	
	    	
	    		
	    		//利用jsuop链接网络，设置头信息
	    		 Document doc = Jsoup.connect(url+i+"/")
	    		      .header("Accept", prop.getProperty("Accept"))
	    		      .header("Accept-Encoding", prop.getProperty("Accept-Encoding"))
	    		      .header("Accept-Language", prop.getProperty("Accept-Language"))
	    		      .header("Cache-Control",prop.getProperty("Cache-Control")) 
	    		      .header("User-Agent", prop.getProperty("User-Agent"))
	    		      .header("Cookie", prop.getProperty("Cookie"))
	    		      .header("Host", prop.getProperty("Host"))
	    		      .header("Referer", prop.getProperty("Referer"))
	    		      .timeout(2000)
	    		      .get();
	    		     
	    		//匹配获取到的文本
	    		 Matcher findIp = compile.matcher(doc.text());
	    		 while(findIp.find()){
	    			 if(getCurrentIpSize()>(Integer)tlNum .get()){
	    				 break;
	    			 }
	    			 //分割
	    			 String[] str = findIp.group().split(" ");
	    			 /**
	    			  * 测试ip是否可用
	    			  */
	    			 if(checkedIp(str[0],Integer.parseInt(str[1]))){
	    				 System.out.println("可用ip:" +str[0]+"端口： "+str[1]);
	    				 addProxy(str[0],str[1],"http");
	    			 }
	    		 }
	    }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    	
	    	
	    }	
	   
	
	/**
	 * 测试ip是否可用
	 * @param string
	 * @param parseInt
	 * @return
	 */
	public static boolean checkedIp(String ip, int port) {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
		try {
			URL url = new URL("http://1212.ip138.com/ic.asp");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
			conn.setUseCaches(false);
			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setRequestProperty("Cookie", "ASPSESSIONIDCQQQQSQS=IDKEILLDCDPBKJKHOJFDNNAC");
			conn.setRequestProperty("Host", ":1212.ip138.com");
		    conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
		    conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
		    conn.setRequestProperty("DNT", "1");
		    InputStream is = conn.getInputStream();
		    FileOutputStream fos = new FileOutputStream("F:\\aaa.txt");
		    ThreadLocal offsetTl = getThreadLocal.getOffsetTl();
		    Integer offset = (Integer) offsetTl.get();
			int i;
			byte[] arr=new byte[1024];
			while((i=is.read(arr))!=-1){
				if(offset==null){
					fos.write(arr,0,i);
				}
				else{
					fos.write(arr,offset,i);
				}
			}
			offsetTl.set(is.available()+offset);
			if(conn.getResponseCode()==200){
				System.out.println("成功");
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private static void addProxy(String ip, String port, String http) {
		//获取当前存储ip的线程
		ThreadLocal currentTL = getThreadLocal.getCurrentTL();
		List<ProxyInfo> IpProxyInfo = (List<ProxyInfo>) currentTL.get();
		ProxyInfo proxyInfo = new ProxyInfo(ip,port,http);
		if(IpProxyInfo==null){
			IpProxyInfo=new ArrayList<ProxyInfo>();
		}
		IpProxyInfo.add(proxyInfo);
		currentTL.set(IpProxyInfo);
			
		
	}

	/**
	 * 获取当前线程里，绑定的以获取ip数量
	 * @return
	 */
	private static int getCurrentIpSize(){
		ThreadLocal currentTL = getThreadLocal.getCurrentTL();
		  List<ProxyInfo> proxyIp = (List<ProxyInfo>)currentTL .get();
		  if(proxyIp==null){
			  proxyIp=new ArrayList<ProxyInfo>();
			  currentTL.set(proxyIp);
			  return 0;
		  }
		return proxyIp.size();
		
	}
}
