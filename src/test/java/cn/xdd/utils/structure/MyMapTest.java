package cn.xdd.utils.structure;

import org.junit.Test;

/**
 *@author: xchb
 *@date: 2020年1月4日下午11:00:25
 *@description: good good study,day day up
 */
public class MyMapTest {

	@Test
	public void first() {
		MyMap<String, Object> mm = new MyMap<String, Object>();
		mm.add("aa", "aaaaaa");
		System.out.println(mm.get("aa"));
	}
}
