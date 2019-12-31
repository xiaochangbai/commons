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
		BinaryTree<String> bt = new BinaryTree<String>();
		bt.add("AA");
		bt.add("ab");
		bt.add("CCC");
		bt.add("11");
		System.out.println(Arrays.toString(bt.toArrays()));
		Assert.assertTrue(bt.isExits("CCC"));
	}
}
