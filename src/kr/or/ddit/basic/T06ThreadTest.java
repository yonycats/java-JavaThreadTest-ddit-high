package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T06ThreadTest {
	
	// 사용자가 입력을 했는지 안했는지 체크하는 용도
	public static boolean INPUT_CHECK = false;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
	}
}

/*
 	사용자 입력을 처리하는 스레드
 	
 */


// T05ThreadTest에서 한번에 한 2가지의 작업을 DataInput와 CountDown에 각각 나눠서 할당시킴
class DataInput extends Thread {

	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		// 입력이 완료되면 정적변수를 true로 변경해주기
		T06ThreadTest.INPUT_CHECK = true;
		
		System.out.println("입력한 값은 " + str + "입니다.");
	}
	
}


class CountDown extends Thread {

	@Override
	public void run() {
		for (int i=10; i>=1; i--) {
			
			// 사용자 입력이 완료되었는지 여부를 확인하여 완료된 경우에는 카운트 다운을 종료한다.
			if (T06ThreadTest.INPUT_CHECK) {
				return; // run() 메서드를 끝낸다.
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 10초가 경과되었는데도 사용자가 입력을 하지 않으면 프로그램을 종료시킨다.
		// exit(0) => 0은 정상 종료 상태를 나타내는 값
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램을 종료시킨다.
	}
	
}