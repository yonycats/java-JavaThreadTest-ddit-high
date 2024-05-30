package kr.or.ddit.basic;

	/*
	 	싱글 스레드 프로그램 예제 (지금까지 사용했던 프로그램 방식)
	 	하나가 끝나면 하나가 실행되는 순차적 방식
	 	
	 */


public class T01ThreadTest {
	public static void main(String[] args) {
		// 싱글 스레드 프로그램
		for (int i=1; i<=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for (int i=1; i<=200; i++) {
			System.out.print("$");
		}
	}
}
