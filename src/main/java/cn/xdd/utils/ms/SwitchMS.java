package cn.xdd.utils.ms;

/**
 * @author: xchb
 * @date: 2019年12月28日上午9:51:50
 * @description: good good study,day day up
 */
public class SwitchMS {

	public static void main(String[] args) {
		swit(5);
	}

	public static void swit(int a) {
		switch (a) {
		default:
			System.out.println("DEFAULT");
		case 1:
			System.out.println("ONE");
		case 2:
			System.out.println("TWO");
			break;
		case 3:
			System.out.println("HHHHHH");
			break;
		case 6:
			System.out.println("Aaaaaaa");
			break;
		}
	}
}
