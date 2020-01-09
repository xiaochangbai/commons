package cn.xdd.utils.structure;

import java.util.Arrays;

/**
 * @author: xchb
 * @date: 2019年12月31日下午6:09:22
 * @description: 链表的简单实现
 */
public class LinkedList<T> {

	
	/**
	 * 节点类
	 * @author XDD
	 *
	 */
	private class Node {
		// 上一个节点
		private Node next;
		// 下一个节点
		private Node previous;
		// 当前节点的数据
		private T data;

		/**
		 * 插入下一个节点
		 * 
		 * @param t
		 */
		public void addNext(T t) {
			if (this.next == null) {
				this.next = new Node();
				this.next.previous = this;// 设置当前节点的下一个节点为当前节点
				this.next.data = t;
			} else {
				this.next.addNext(t);
			}
		}

		public void toArray(Object[] t) {
			t[supscript++] = this.data;
			if (this.next != null) {
				this.next.toArray(t);
			}
		}

		public void count(T t) {
			if (this.data == t || (this.data != null && this.data.equals(t))) {
				toCount++;
			}
			if (this.next != null) {
				this.next.count(t);
			}
		}

		public void deleteFirst(T t) {
			if (this.data.equals(t)) {
				// 删除当前元素
				// 当前元素没有上一个节点的时候，直接将根节点指向下一个节点
				if (this.previous == null && this.next != null) {
					root = this.next;
					// 链表数量-1
					size--;
				}
				// 当前元素还有上一个节点的时候
				// 将当前元素的上一个节点的下一个节点指向下一个节点
				if (this.previous != null && this.next != null) {
					this.previous.next = this.next;
					size--;
				}
			}else {
				this.next.deleteFirst(t);
			}
		}

		@Override
		public String toString() {
			T previous = this.previous == null ? null : this.previous.data; 
			T next = this.next == null ? null : this.next.data; 
			return "Node [next=" + next + ", previous=" + previous + ", data=" + data + "]";
		}
		
		

	}

	private Node root; // 根节点
	private Integer size = 0; // 链表大小
	private Integer supscript = 0; // 数组下标，主要用于toArrays()方法
	private Integer toCount = 0; // 统计元素出现的次数

	/**
	 * 添加节点
	 * 
	 * @param t
	 */
	public void add(T t) {
		// 当根节点不存在时，添加根本节点
		if (this.root == null) {
			this.root = new Node();
			this.root.previous = null; // 根节点的上一个节点为空
			this.root.data = t;
		} else {
			this.root.addNext(t);
		}
		this.size++;
	}

	/**
	 * 链表转数组
	 * @return
	 */
	public T[] toArrays() {
		if (this.root == null) {
			return null;
		}
		Object[] t = new Object[size];
		this.root.toArray(t);
		T[] tt = (T[]) t;
		supscript = 0;//下标清零
		return tt;
	}

	/**
	 * 查看某元素在链表中出现的次数
	 * @param t
	 * @return
	 */
	public int count(T t) {
		if (this.root == null) {
			return 0;
		}
		this.root.count(t);
		int tc = toCount;
		toCount = 0; // 清空统计结果，方便下一次统计
		return tc;
	}

	/**
	 * 删除首次出现的元素
	 * @param t
	 */
	public void deleteFirst(T t) {
		if (this.root == null) {
			return;
		}
		this.root.deleteFirst(t);
	}
	
	/**
	 * 根据序号获取元素
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if(size <= 0 || size < index) {
			return null;
		}
		if(index == 1) {
			return root.data;
		}
		T t = null;
		Node n = root;
		for(int i=0;i<index;i++) {
			t = n.data;
			n = n.next;
		}
		return t;
	}
	
	/**
	 * 返回链表的大小
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	@Override
	public String toString() {
		while(root ==null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Node n = root;
		sb.append("["+n.data+", previous: "+n.previous+", next: "+n.next+"]");
		while((n = n.next) != null) {
			sb.append("["+n.data+", previous: "+n.previous+", next: "+n.next+"]");
		}
		return sb.toString();
	}
}
