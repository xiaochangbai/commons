package cn.xdd.utils.structure;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author: xchb
 * @date: 2020年1月1日下午1:13:47
 * @description: good good study,day day up
 */
public class LinkedListTest {

	@Test
	public void test() {
		LinkedList<String> ls = new LinkedList<String>();
		ls.add("HHHHH");
		ls.add("AAAAA");
		ls.add("CCCC");
		ls.add("CCCC");
		ls.add("CCCC");
		ls.add(null);
		System.out.println(Arrays.toString(ls.toArrays()));
		System.out.println(ls.count(null));
		System.out.println(ls.count("CCCC"));
		ls.deleteFirst("CCCC");
		System.out.println(Arrays.toString(ls.toArrays()));
		System.out.println(ls.get(1));
		System.out.println(ls.getSize());

	}
}
