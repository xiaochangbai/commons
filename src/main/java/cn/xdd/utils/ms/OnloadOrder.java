package cn.xdd.utils.ms;

/**
 *@author: xchb
 *@date: 2020年1月2日下午10:22:51
 *@description: 类的加载顺序
 */

class Person{
	public Person(String name) {
		System.out.println("PERSON: name = "+name);
	}
}

class A{
	
	private Person person = new Person("A");
	
	static {
		System.out.println("A类static代码块");
	}
	
	{
		System.out.println("A类代码块");
	}
	
	public A() {
		System.out.println("A类无参构造");
	}
}

class B extends A{
	
	private Person person = new Person("B");
	
	static {
		System.out.println("B类static代码块");
	}
	
	{
		System.out.println("B类代码块");
	}
	
	public B() {
		System.out.println("B类无参构造");
	}
	
}


public class OnloadOrder {
	public static void main(String[] args) {
		A a = new B();
	}
}
