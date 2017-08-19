package cn.itcast.cralwer.service;

import java.util.List;
import java.util.regex.Pattern;

import cn.itcast.domain.ProxyInfo;
import cn.itcast.utils.IPCom;
import cn.itcast.utils.getThreadLocal;

public class ProxyCralwerService {
    //创建一个线程绑定想要的ip数量
	public static void proxyCralwer(Integer num){
		//将想要的数量设置到线程了
		getThreadLocal.getTlNum().set(num);
		
	    IPCom.getIp("http://www.xicidaili.com/nn/",15);
	    
	    List<ProxyInfo> ip = (List<ProxyInfo>) getThreadLocal.getCurrentTL().get();
		for (ProxyInfo proxyInfo : ip) {
			System.out.println(proxyInfo.getIp());
		}
	}
	

	
	
}
