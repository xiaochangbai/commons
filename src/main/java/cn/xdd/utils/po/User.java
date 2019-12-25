package cn.xdd.utils.po;

import java.io.Serializable;

/**
 *@author: xchb
 *@date: 2019年12月4日下午1:29:22
 *@description: good good study,day day up
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String pwd;
	
	public User() {}

	public User(Integer id, String name, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + "]";
	}
	
}
