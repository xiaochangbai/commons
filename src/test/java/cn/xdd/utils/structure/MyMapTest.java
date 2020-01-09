package cn.xdd.utils.structure;

import java.util.Arrays;
import java.util.Objects;

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
		mm.add("aa", "AAAAAA");
		mm.add("CC", "CCCC");
		mm.add("cc", "cccc");
		System.out.println(mm.get("aa"));
		System.out.println(mm.get("CC"));
		System.out.println(mm.get("cc"));
		//Object[] objs = new Object[100000000];
		System.out.println(hash("aaaa"));
 	}
	
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	
	@Test
	public void test() {
		
		System.out.println(Objects.equals("AA","AA"));
	}
}
