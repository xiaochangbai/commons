package cn.xdd.utils.structure;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 *@author: xchb
 *@date: 2019年12月31日下午5:35:14
 *@description: good good study,day day up
 */
public class BinaryTreeTest {

	@Test
	public void test() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(33);
		bt.add(1);
		bt.add(2);
		bt.add(30);
		System.out.println(Arrays.toString(bt.toArrays()));
		Assert.assertTrue(bt.isExits(1));
	}
}
