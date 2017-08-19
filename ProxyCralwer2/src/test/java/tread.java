import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class tread extends Thread {
	private String img;
	private Proxy proxy;
	private String name;
	
     public tread(String img,Proxy proxy,String name) {
		this.img = img;
		this.proxy = proxy;
		this.name = name;
	}

	@Override
    public synchronized void run() {
    	 
 				 System.out.println(name+"运行："+img);
 				 BufferedInputStream bis =null;
 				 BufferedOutputStream bos=null;
 				 try {
 					URL url = new URL(img);
 					URLConnection conn = url.openConnection(proxy);
 					conn.setConnectTimeout(3000);
 					conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
 					conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
 					InputStream is = conn.getInputStream();
 					bis = new BufferedInputStream(is);
 					 bos=new BufferedOutputStream(new FileOutputStream("D:\\imge\\"+img.substring(img.lastIndexOf("/")+1)));
 					byte[] arr= new byte[1024];
 					int i;
 					while((i=bis.read(arr))!=-1){
 						bos.write(arr,0,i);
 					}
 					System.out.println("成功");
 					Thread.sleep(3000);
 				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				 finally{
 					 try {
 						bos.close();
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 					 try {
 						bis.close();
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 				 }
 			 }; 
 		 
    }

