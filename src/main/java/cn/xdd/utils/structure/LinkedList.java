package cn.xdd.utils.structure;

import java.util.Arrays;

/**
 *@author: xchb
 *@date: 2019年12月31日下午6:09:22
 *@description: good good study,day day up
 *链表的简单实现
 */
public class LinkedList<T> {

	private class Node{
		private Node next;
		private T data;
		public void addNext(T t) {
			if(this.next == null) {
				this.next = new Node();
				this.next.data = t;
			}else {
				this.next.addNext(t);
			}
		}
		@Override
		public String toString() {
			return "Node [next=" + next + ", data=" + data + "]";
		}
		public void toArray(Object[] t) {
			t[supscript++] = this.data;
			if(this.next != null) {
				this.next.toArray(t);
			}
		}
		public void count(T t) {
			if(this.data == t || (this.data !=null && this.data.equals(t))) {
				toCount++;
			}
			if(this.next != null) {
				this.next.count(t);
			}
		}
		
		
	}
	
	private Node root;  //根节点
	private Integer count = 0; //计数器
	private Integer supscript = 0; //数组下标，主要用于toArrays()方法
	private Integer toCount = 0;  // 统计元素出现的次数
	
	/**
	 * 添加节点
	 * @param t
	 */
	public void add(T t) {
		//当根节点不存在时，添加根本节点
		if(this.root == null) {
			this.root = new Node();
			this.root.data = t;
		}else {
			this.root.addNext(t);
		}
		this.count ++ ;
	}
	
	/**
	 * 链表转数组
	 * @return
	 */
	public T[] toArrays() {
		if(this.root == null) {
			return null;
		}
		Object[] t = new Object[count];
		this.root.toArray(t);
		return (T[]) t;
	}
	
	/**
	 * 查看某元素在链表中出现的次数
	 * @param t
	 * @return
	 */
	public int count(T t) {
		if(this.root == null) {
			return 0;
		}
		this.root.count(t);
		int tc = toCount;
		toCount = 0; //清空统计结果，方便下一次统计
		return tc;
	}
	
	
	public void delete(T t) {
		
	}
	
	
	public static void main(String[] args) {
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
		
	}
	
}
