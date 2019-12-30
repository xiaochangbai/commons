package cn.xdd.utils.po;

import java.util.List;

/**
 *@author: xchb
 * @param <T>
 *@date: 2019年12月26日下午11:04:40
 *@description: good good study,day day up
 *
 *分页参数对象
 */
public class PagingBean<T> {

	/**
	 * 当前页码：current page number
	 */
	private Integer cpn;
	
	/**
	 * 总页码数：total page count
	 */
	private Integer tpc;
	
	
	/**
	 * 总数据量：total data count
	 */
	private Long tdc;
	
	/**
	 * 当前页要展示的数据：current page data
	 */
	private List<T> cpd;
	
	/**
	 * 当前分页所对应的url地址
	 */
	private String url;

	public Integer getCpn() {
		return cpn;
	}

	public void setCpn(Integer cpn) {
		this.cpn = cpn;
	}

	public Integer getTpc() {
		return tpc;
	}

	public void setTpc(Integer tpc) {
		this.tpc = tpc;
	}

	public Long getTdc() {
		return tdc;
	}

	public void setTdc(Long tdc) {
		this.tdc = tdc;
	}

	public List<T> getCpd() {
		return cpd;
	}

	public void setCpd(List<T> cpd) {
		this.cpd = cpd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PagingBean [cpn=" + cpn + ", tpc=" + tpc + ", tdc=" + tdc + ", cpd=" + cpd + ", url=" + url + "]";
	}
	
	
	
}
