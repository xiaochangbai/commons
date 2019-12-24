package cn.xdd.utils.servlet;

import java.io.Serializable;

/**
 * @author: xchb
 * @date: 2019年12月23日下午6:27:52
 * @description: g@SuppressWarnings("serial") ood good study,day day up
 */
public class MethodInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 方法名
	 */
	private String name;
	
	/**
	 * 请求格式（GET、POST...)
	 */
	private String requestType;
	
	/**
	 * 请求路径
	 */
	private String requestPath;

	public MethodInfo() {
		super();
	}

	public MethodInfo(String name, String requestType, String requestPath) {
		super();
		this.name = name;
		this.requestType = requestType;
		this.requestPath = requestPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==this) {
			return true;
		}
		
		if(obj instanceof MethodInfo) {
			MethodInfo mf = (MethodInfo) obj;
			if(this.name.equals(mf.getName()) 
					&& this.requestPath.equals(mf.getRequestPath())
					&& this.requestType.equals(mf.getRequestType())) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "MethodInfo [name=" + name + ", requestType=" + requestType + ", requestPath=" + requestPath + "]";
	}

}
