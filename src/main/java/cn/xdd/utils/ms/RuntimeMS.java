package cn.xdd.utils.ms;

/**
 *@author: xchb
 *@date: 2019年12月30日下午10:11:30
 *@description: good good study,day day up
 *
 *
 */
public class RuntimeMS {
	public static void main(String[] args) {
		//调整内存:-Xms2048M -Xmx2048M -Xmn1024M
		Runtime run = Runtime.getRuntime();
		System.out.println(run.maxMemory()/1024/1024+"M");
		System.out.println(run.totalMemory()/1024/1024);
		System.out.println(run.freeMemory()/1024/1024+"M");
		//1794M
		//123
		//121M
		System.out.println(run.availableProcessors());
	}
}
