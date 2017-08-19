package cn.itcast.domain;

public class ProxyInfo {
    
	//定义用户名
	private String username="";
	//定义密码
	private String password="";
	//定义ip
	private String ip;
	//定义端口
	private String port;
	//定义版本协议
	private String type;
	//判断是否联网
	private int is_internet=1;
	
	public ProxyInfo() {
	
	}

	public ProxyInfo(String ip, String port, String type) {
		super();
		this.ip = ip;
		this.port = port;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String  getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIs_internet() {
		return is_internet;
	}

	public void setIs_internet(int is_internet) {
		this.is_internet = is_internet;
	}
	
	
	
	
	
}
