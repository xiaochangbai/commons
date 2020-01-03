package cn.xdd.utils.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *@author: xchb
 *@date: 2020年1月3日下午9:47:49
 *@description: good good study,day day up
 */
public class MyListTest {

	@Test
	public void test() {
		MyList<String> list = new MyList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ccc");
		list.add("ccc");
		list.add("ccc");
		list.add("ccc");
		list.add("fff");
		list.add("fff");
		list.add("fff");
		list.remove("aaa");
		assertTrue(list.contains("aaa"));
		assertEquals(list.size(), 10);
		assertEquals(list.getCapacity(), 17);
		assertEquals(list.get(1), "bbb");
	}
}
