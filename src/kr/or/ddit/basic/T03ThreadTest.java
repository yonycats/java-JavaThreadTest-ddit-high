package kr.or.ddit.basic;

/*
	 	스레드의 수행시간 체크 예제
 */

public class T03ThreadTest {
	public static void main(String[] args) {
		
		Thread th = new Thread(new MyRunner());
		
		// UTC(Universal Time Coodinates 협정 세계 표준시)를 사용하여 
		// 1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 밀리세컨드 단위로 나타낸다.
		
		// 스레드를 실행하기 전의 시간 기준
		long startTime = System.currentTimeMillis();
		
		th.start(); // 스레드 구동
		
		try {
			// 메인 스레드가 .join을 실행하는 순간, 현재 작업 중인 th 스레드가 종료될 때까지 대기 후,
			// 작업이 종료되면 메인 스레드가 다시 실행된다
			th.join(); // 현재 실행중인 스레드에서
					   // 작업 중인 스레드 (지금은 th 스레드)가 종료될 때까지 기다린다.	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 실행한 스레드가 종료됐을 때의 시간 기준
		long endTime = System.currentTimeMillis();
		
		// 실행한 스레드가 종료됐을 때의 시간 - 스레드를 실행하기 전의 시간 = 스레드 실행된 동안의 시간
		System.out.println("경과 시간(ms) : " + (endTime - startTime));
		System.out.println(startTime);
		System.out.println(endTime);
		
	}
}


// 1~1000000000(10억)까지의 합계를 구하기 위한 스레드
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		
		for (int i=1; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}