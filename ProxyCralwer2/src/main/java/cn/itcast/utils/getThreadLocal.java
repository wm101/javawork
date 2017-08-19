package cn.itcast.utils;

import java.util.List;

import cn.itcast.domain.ProxyInfo;

public class getThreadLocal {
	//设置想要的ip数量
    private static ThreadLocal tlNum;
    //当前的获取的有效ip数量
    private static ThreadLocal currentTL;
    //当前的ip写出的偏移量
    private static ThreadLocal offsetTl;
	
   public static ThreadLocal getTlNum() {
		if(tlNum==null){
			tlNum=new ThreadLocal<Integer>();
		}
		return tlNum;
	}
   public static ThreadLocal getOffsetTl() {
		if(offsetTl==null){
			offsetTl=new ThreadLocal<Integer>();
		}
		return tlNum;
	}
	public static ThreadLocal getCurrentTL() {
		if(currentTL==null){
			currentTL=new ThreadLocal<List<ProxyInfo>>();
		}
		return currentTL;
	}
	
    
	
	
	
}
