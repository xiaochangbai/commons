package cn.xdd.utils.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.rmi.server.RMIClassLoader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 *@author: xchb
 *@date: 2020年1月3日下午9:47:49
 *@description: good good study,day day up
 */
public class MyListTest {

	@Test
	public void testAdd() {
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
		assertTrue(!list.contains("aaa"));
		assertEquals(list.size(), 9);
		assertEquals(list.getCapacity(), 15);
		assertEquals(list.get(1), "bbb");
		System.out.println(list);
	}
	
	@Test
	public void testRemove() {
		MyList<Object> list = new MyList<Object>();
		for(int i =0 ;i<100;i++) {
			list.add("XDD--"+i);
		}
		list.remove("XDD--98");
		System.out.println("元素个数："+list.size());
		System.out.println("根据索引获取指定元素："+list.get(99));
		System.out.println("内部数组容量："+list.getCapacity());
		System.out.println(list);
	}
	
	@Test
	public void testContainsAll() {
		MyList<String> list = new MyList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		boolean containsAll = list.containsAll(Arrays.asList("aaa","bbb","ccc","ddd"));
		assertTrue(!containsAll);
		System.out.println(list);
	}
	
	@Test
	public void testAll() {
		MyList<String> list = new MyList<String>();
		list.add("asdfadf");
		list.addAll(Arrays.asList("aaaa","fasfd","asdfasf"));
		System.out.println(list);
		
		list.addAll(3, Arrays.asList("ccc","jjj"));
		System.out.println(list);
	}
	
	@Test
	public void testRemoveAll() {
		MyList<String> list = new MyList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		System.out.println(list);
		list.removeAll(Arrays.asList("aaa","bbb","c"));
		System.out.println(list);
	}
	
	@Test
	public void testAdd1() {
		MyList<String> list = new MyList<String>();
		list.add("aaa");
		list.add("ggg");
		list.add("cccc");
		list.add(4, "af");
		System.out.println(list);
	}
	
	
	@Test
	public void testRemove1() {
		MyList<String> list = new MyList<String>();
		list.add("aaa");
		list.add("ggg");
		list.add("cccc");
		list.add("dddd");
		String remove = list.remove(10);
		System.out.println(list.indexOf("ggg"));
		System.out.println(remove);
		System.out.println(list.indexOf("ggg"));
		System.out.println(list.lastIndexOf("ggg"));
		System.out.println(list);
	}
	
	@Test
	public void testSubList() {
		MyList<String> list = new MyList<String>();
		list.add("aaa");
		list.add("ggg");
		list.add("cccc");
		list.add("dddd");
		List<String> subList = list.subList(1, 1);
		System.out.println(subList);
	}
}
