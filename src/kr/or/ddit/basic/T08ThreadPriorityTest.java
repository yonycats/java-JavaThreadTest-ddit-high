package kr.or.ddit.basic;

public class T08ThreadPriorityTest {
	// 스레드의 우선순위 알아보기
	// 우선순위 순서 : 10이 가장 높고, 1이 가장 낮다
	
	public static void main(String[] args) {
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		
		Thread[] ths = new Thread[] {
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest1(), new ThreadTest1(),
				new ThreadTest2()
		};
		
		// 우선 순위는 start()메서드를 실행하기 전에 설정해야 한다.
		// 우선순위 세팅은 소문자가 우선순위가 높도록 세팅했지만, 1순위로 세팅한 것이 절대적으로 먼저 종료되지는 않는다.
		// 우선순위가 높은 스레드는 실행 기회가 더 많지만, 항상 먼저 실행되는 것은 아니다.
		for (int i=0; i<ths.length; i++) {
			if (i==16) {
				ths[i].setPriority(10);
			} else {
				ths[i].setPriority(1);
			}
		}
		
		// 설정된 우선순위 정보 가져오기
		for (Thread th : ths) {
			System.out.println(th.getName() + "의 우선순위 : " + th.getPriority());
		}
		
		// 스레드 구동시키기
		for (Thread th : ths) {
			th.start();
		}
		
	}
}


// 대문자를 출력하는 스레드
class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(ch);
			
			// 시간 때우기용
			for (long i=1; i<=1000000000; i++) {}
		}
	}
}


// 소문자를 출력하는 스레드
class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++) {
			System.out.println(ch);
			
			// 시간 때우기용
			for (long i=1; i<=1000000000; i++) {}
		}
	}
}