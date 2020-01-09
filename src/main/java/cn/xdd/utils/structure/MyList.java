package cn.xdd.utils.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: xchb
 * @date: 2020年1月3日下午8:59:24
 * @description: 自己实现的简单List(基于数组动态扩容)
 */
public class MyList<T> implements List<T>,Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	private final static float AS = 0.75f; // 当保存的数据量达到整个数组的75%之后就执行扩容操作。
	private final static float FIRSTADD = 0.5f; // 第一次扩容比率
	private final static Integer ARRAYS_INIT_SISE = 10;
	private Integer customSize = null; // 用户指定长度
	private Integer size = ARRAYS_INIT_SISE; // 数组大小,默认十个长度
	private T[] arrays; // 数组对象
	private float ac = FIRSTADD; // 第一次扩容时，将容量扩充到原先的50%，每次扩容之后，该数据都会变化。
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
			if ((this.subscript + 1) >= this.size * AS) {
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
		int arrayLength = this.customSize == null ? this.subscript : this.customSize;
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
		if (c == null || c.size() <= 0 || this.subscript < c.size()) {
			return false;
		}
		Object[] cArrays = c.toArray();
		boolean flag = false;
		for (int i = 0; i < this.subscript - cArrays.length + 1; i++) {
			// 首位元素相等
			if (this.arrays[i].equals(cArrays[0])) {
				flag = true;
				// 比较其他元素
				for (int j = 1; j < cArrays.length; j++) {
					if (!arrays[i + j].equals(cArrays[j])) {
						flag = false;
						break;
					}
				}
				//
				if (flag) { // 已经有匹配结果
					return true;
				}
				continue; // 执行下一次比较
			}
		}
		return flag;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (c == null || c.size() < 0) {
			return false;
		}
		Iterator<? extends T> iterator = c.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			this.add(next);
		}
		return true;
	}

	/**
	 * 从指定位置插入元素 索引从1开始
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if (c == null || c.size() < 0 || index - 1 < 0) {
			return false;
		}
		Iterator<? extends T> iterator = c.iterator();
		// 查看要插入的索引位置是否存在元素
		if (this.subscript <= index - 1) {
			// 索引位置没有元素，从数组的最后面开始添加
			while (iterator.hasNext()) {
				this.add(iterator.next());
			}
		} else {
			// 索引位置存在元素
			// 1.将索引后面的元素都移入到一个新的数组
			int newSize = subscript - index + 1;
			T[] newArrays = (T[]) new Object[newSize];
			for (int i = 0; i < newSize; i++) {
				newArrays[i] = this.arrays[index - 1 + i];
				this.subscript--;
			}
			// 2.从索引位置开始插入元素
			while (iterator.hasNext()) {
				T next = iterator.next();
				this.add(next);
			}
			// 3.将步骤一中的移出的数组重新放回数组对象中
			for (int i = 0; i < newArrays.length; i++) {
				this.add(newArrays[i]);
			}
		}

		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null || c.size() <= 0 || this.subscript < c.size()) {
			return false;
		}

		Object[] cArrays = c.toArray();
		boolean flag = false;
		for (int i = 0; i < this.subscript - cArrays.length + 1; i++) {
			// 首位元素相等
			if (this.arrays[i].equals(cArrays[0])) {
				flag = true;
				int firstSubScript = i; // 匹配到的首个元素下标
				// 比较其他元素
				for (int j = 1; j < cArrays.length; j++) {
					if (!arrays[i + j].equals(cArrays[j])) {
						flag = false;
						break;
					}
				}
				//
				if (flag) { // 已经有匹配结果
					// 执行移除操作(将后面的元素往前移)
					// 获取的元素下标
					int sufixIndex = firstSubScript + c.size();
					int templateSubscript = this.subscript;
					for (int j = sufixIndex; j < templateSubscript; j++) {
						this.arrays[j - c.size()] = this.arrays[j];
					}
					// 改变数组下标
					this.subscript = this.subscript - c.size();
					return true;
				}
				continue; // 执行下一次比较
			}
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		System.out.println("未实现该方法------------【retainAll(Collection<?> c)】");
		return false;
	}

	@Override
	public void clear() {
		this.subscript = 0;
		this.arrays = (T[]) new Arrays[ARRAYS_INIT_SISE];
	}

	@Override
	public T get(int index) {
		int realIndex = index - 1;
		if (realIndex < 0 || realIndex >= subscript) {
			return null;
		}
		return this.arrays[realIndex];
	}

	@Override
	public T set(int index, T element) {
		if (index - 1 <= this.subscript) {
			add(element);
		} else {
			this.arrays[index] = element;
		}
		return element;
	}

	@Override
	public void add(int index, T element) {
		if (index <= 0 || element == null) {
			return;
		}
		index = index - 1;
		if (index >= this.subscript) {
			this.add(element);
		} else {
			// 将index索引后面的元素都放入一个新数组中
			int size = subscript - index;
			T[] newArrays = (T[]) new Object[size];
			for (int i = 0; i < size; i++) {
				newArrays[i] = arrays[i + index];
				this.subscript--;
			}
			arrays[index] = element;
			this.subscript++;
			for (int i = 0; i < newArrays.length; i++) {
				this.add(newArrays[i]);
			}
		}
	}

	@Override
	public T remove(int index) {
		index = index - 1;
		T t = null;
		if (index < 0 || index >= this.subscript) {
			return null;
		} else {
			t = arrays[index];
			// 将后面的元素都往前移一位
			for (int i = index; i < this.subscript - 1; i++) {
				arrays[i] = arrays[i + 1];
			}
			this.subscript--;
		}
		return t;
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			return -1;
		}
		for (int i = 0; i < subscript; i++) {
			if (o.equals(arrays[i])) {
				return i + 1;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			return -1;
		}
		for (int i = subscript - 1, j = 0; i >= 0; i--, j++) {
			if (o.equals(arrays[i])) {
				return j + 1;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		System.out.println("未实现该方法------------【retainAll(Collection<?> c)】");
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		System.out.println("未实现该方法------------【retainAll(Collection<?> c)】");
		return null;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		fromIndex = fromIndex - 1;
		toIndex = toIndex - 1;
		if (fromIndex < 0 || toIndex >= this.subscript || fromIndex > toIndex) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (int i = fromIndex; i <= toIndex; i++) {
			list.add(this.arrays[i]);
		}

		return list;
	}

	@Override
	public String toString() {
		if (this.subscript <= 0 && this.customSize == null) {
			return null;
		}
		// 数组长度
		int arrayLength = this.customSize == null ? this.subscript : this.customSize;
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < arrayLength; i++) {
			sb.append(this.arrays[i]);
			if (i < arrayLength - 1) {
				sb.append(",");
			}
			if (i == arrayLength - 1) {
				sb.append("]");
			}
			if (i != 0 && i % 7 == 0) {
				sb.append("\n");
			}

		}
		return sb.toString();
	}

}
