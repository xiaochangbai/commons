package cn.xdd.utils.structure;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *@author: xchb
 * @param <K>
 * @param <V>
 *@date: 2020年1月4日下午10:37:26
 *@description: good good study,day day up
 *Map集合的简单实现：数组+链表
 */
public class MyMap<K, V> implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	
	private static final Integer ARRAYS_INIT_SIZE = 50 ; 
	
	private Node<K,V> arrays[] = null;
	
	public MyMap() {
		this.arrays = new Node[ARRAYS_INIT_SIZE];
	}
	
	private final class Node<K,V>{
		K k = null;
		V v = null;
		
		public Node() {}
		
		
		public Node(K k, V v) {
			super();
			this.k = k;
			this.v = v;
		}


		public K getK() {
			return k;
		}
		public void setK(K k) {
			this.k = k;
		}
		public V getV() {
			return v;
		}
		public void setV(V v) {
			this.v = v;
		}
		
	}
	
	public void add(K k,V v) {
		this.arrays[hash(k)] = new MyMap.Node(k, v);
	}
	
	public V get(K k) {
		MyMap<K, V>.Node<K, V> node = (MyMap<K, V>.Node<K, V>) this.arrays[hash(k)];
		return node == null ? null : node.getV();
	}
	
	private int hash(K k) {
		if(k == null) {
			return 0;
		}
		return k.hashCode()%ARRAYS_INIT_SIZE;
	}

	

}
