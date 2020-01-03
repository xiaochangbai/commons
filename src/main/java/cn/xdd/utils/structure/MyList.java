package cn.xdd.utils.structure;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: xchb
 * @date: 2020年1月3日下午8:59:24
 * @description: good good study,day day up 自己实现的简单List(基于数组动态扩容)
 */
public class MyList<T> implements List<T> {

	private Integer customSize = null; // 用户指定长度
	private Integer size = 10; // 数组大小,默认十个长度
	private T[] arrays; // 数组对象
	private float as = 0.75f; // 当保存的数据量达到整个数组的75%之后就执行扩容操作。
	private float ac = 0.7f; // 第一次扩容时，将容量扩充到原先的70%，每次扩容之后，该数据都会变化。
	private int subscript = 0; // 当前元素的数组下标

	public MyList() {
		this(null);
	}

	public MyList(Integer size) {
		this.customSize = size;
		// 初始化数组
		this.arrays = (T[]) new Object[this.customSize == null ? this.size : this.customSize];
	}

	@Override
	public int size() {
		return this.subscript;
	}

	public int getCapacity() {
		return this.customSize == null ? this.size : this.customSize;
	}

	@Override
	public boolean isEmpty() {
		return this.customSize == null && this.size <= 0;
	}

	@Override
	public boolean contains(Object o) {
		if (o == null || (this.size < 0 && this.customSize == null)) {
			return false;
		}
		Integer s = customSize == null ? this.size : this.customSize;
		for (int i = 0; i < s; i++) {
			if (o.equals(arrays[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		if (this.arrays.length <= 0) {
			return null;
		}
		return Arrays.asList(arrays).iterator();
	}

	@Override
	public Object[] toArray() {
		return arrays;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) arrays;
	}

	@Override
	public boolean add(T e) {
		if (e == null) {
			return false;
		}
		// 如果指定了长度
		if (this.customSize != null) {
			// 新插入的数据不会覆盖原有的数据
			if (this.subscript < this.customSize) {
				this.arrays[subscript++] = e;
				return true;
			} else {
				return false;
			}
		} else {// 没有指定长度时
				// 1、查看是否需要扩容
			if ((this.subscript + 1) >= this.size * as) {
				// 扩容
				// 新数组的长度(旧大小 * (1+扩容率))
				int newSize = (int) (this.size * (1 + this.ac));
				this.size = newSize;
				T[] newArrays = (T[]) new Object[newSize];
				// 将旧数组中的数据放入新数组
				for (int i = 0; i < subscript; i++) {
					newArrays[i] = arrays[i];
				}
				// 将新数组替换就数组
				this.arrays = newArrays;
				// 修改扩容率
				ac = ac * 1.5f;
			}
			// 2、插入数据
			this.arrays[subscript++] = e;
			return true;
		}

	}

	@Override
	public boolean remove(Object o) {
		if (o == null) {
			return false;
		}
		// 数组长度
		int arrayLength = this.customSize == null ? this.size : this.customSize;
		for (int i = 0; i < arrayLength; i++) {
			if (arrays[i].equals(o)) {
				// 将后面的元素往前移一位
				for (int j = i; j < arrayLength - 1; j++) {
					arrays[j] = arrays[j + 1];
				}
				// 最后一个元素为空，将当前下标往前挪一位
				this.subscript--;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public T get(int index) {
		int realIndex = index-1;
		if(realIndex < 0 || realIndex >= subscript) {
			return null;
		}
		return this.arrays[realIndex];
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
