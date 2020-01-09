package cn.xdd.utils.structure;

import java.util.Arrays;

/**
 * @author: xchb
 * @date: 2019年12月30日下午10:42:32
 * @description: 简单的二叉树
 */
public class BinaryTree<T> {

	/**
	 * *******************************************
	 * 节点类
	 * @author XDD
	 */
	private class Node {
		private T data = null; // 数据
		private Node left; // 左节点
		private Node right; // 右节点

		/**
		 * 添加节点
		 * @param ce
		 */
		private void addNode(T ce) {
			// 新增的元素大于当前元素
			if (((Comparable<Object>) this.data).compareTo(ce) < 0) {
				// 右节点不为空
				if (this.right == null) {
					this.right = new Node();
					// 添加到右节点上
					this.right.data = ce;
				} else {
					// 右节点不为空，调用右节点的添加方法进行递归调用
					this.right.addNode(ce);
				}
			} else { // 新增元素小于当前元素
				if (this.left == null) {
					this.left = new Node();
					// 添加到左节点上
					this.left.data = ce;
				} else {
					// 左节点不为空，调用左节点的添加方法进行递归调用
					this.left.addNode(ce);
				}
			}
		}

		/**
		 * 所有将节点转成数组（排序）
		 * @param result
		 */
		private void toArray(Object[] result) {
			//左节点存在时，递归调用
			if (this.left != null) {
				this.left.toArray(result);
			}
			//将当前节点的数据赋值给数组
			result[subscript++] = this.data;
			//右节点存在时，递归调用
			if (this.right != null) {
				this.right.toArray(result);
			}

		}

		@SuppressWarnings("unchecked")
		public boolean isExit(T ce) {
			Comparable<T> comparable = (Comparable<T>) ce;
			if(comparable.compareTo(this.data) == 0) {
				return true;
			}
			//大于当前节点的数据，往右边查找
			if(comparable.compareTo(this.data) > 0 && this.right != null) {
				return this.right.isExit(ce);
			}else if(comparable.compareTo(this.data) < 0 && this.left != null){
					return this.left.isExit(ce);
			}
			return false;

		}
	}

	/**
	 ***************************************
	 * 二叉树的属性和操作
	 */
	private Node root; // 根节点
	private int count; // 统计二叉树中节点个数
	private int subscript = 0; // 数组下标（用于输出二叉数）

	/**
	 * 往二叉树中添加数据
	 * @param object
	 */
	public void add(T t) {
		if (!(t instanceof Comparable<?>)) {
			throw new RuntimeException("所需数据格式类必要实现Comparable接口");
		}
		if (this.root == null) {
			this.root = new Node();
			this.root.data = t;
		} else {
			this.root.addNode(t);
		}
		count++;
	}

	/**
	 * 排序输出二叉树的所有数据
	 * @return
	 */
	public T[] toArrays() {
		if (this.root == null) {
			return null;
		}
		// 中序遍历输出
		Object[] result = new Object[count];
		this.root.toArray(result);
		return (T[]) result;
	}
	
	/**
	 * 查询一个元素是否存在当前二叉树中
	 * @param object
	 * @return
	 */
	public boolean isExits(T t) {
		if(root == null) {
			return false;
		}
		if (!(t instanceof Comparable<?>)) {
			throw new RuntimeException("所需数据格式类必要实现Comparable接口");
		}
		//比较
		return this.root.isExit(t);
	}
	
	/**
	 * 打印二叉树
	 */
	/*
	 * @Override public String toString() { StringBuffer sb = new StringBuffer();
	 * if(this.root == null) { return null; } double treeHeight =
	 * Math.log(count)/Math.log(2); int height = (int) (treeHeight/10 ==
	 * 0?treeHeight:treeHeight+1); this.root.toString(); return null; }
	 */
}
