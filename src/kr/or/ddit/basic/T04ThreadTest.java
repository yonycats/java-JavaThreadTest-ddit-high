package kr.or.ddit.basic;

public class T04ThreadTest {

	/*
	 	1~20억까지의 합계를 구하는데 걸린 시간 체크하기
	 	
	 	단독으로(스레드1개) 합계를 구하는 경우와 여러개의 스레드로 나누어 합계를
	 	구했을 때의 시간을 확인해보자.
	 */
	
	public static void main(String[] args) {
		
		// 단독으로 처리할 때
		SumThread sTh = new SumThread(1L, 2000000000L);
		
		long startTime = System.currentTimeMillis();
		
		sTh.start();
		
		try {
			sTh.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독 처리 했을 때의 처리시간 : " + (endTime - startTime) + "(ms)");
		
		System.out.println();
		System.out.println("----------------------------------");
		
		
		
		// 여러 스레드가 협력해서 처리했을 때
		// Context Switching (문맥 교환)이 계속해서 번갈아 실행되고 있음
		SumThread[] sumThs = new SumThread[] {
			new SumThread(1L, 500000000L),	
			new SumThread(500000001L, 1000000000L),
			new SumThread(1000000001L, 1500000000L),
			new SumThread(1500000001L, 2000000000L),
		};
		
		startTime = System.currentTimeMillis();
		
		for (Thread th : sumThs) {
			th.start();
		}
		
		for (Thread th : sumThs) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("협력 처리 했을 때의 처리시간 : " + (endTime - startTime) + "(ms)");
	}
	
}


// 범위 값을 합산하기 위한 스레드
class SumThread extends Thread {
	
	private long min, max;

	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public void run() {
		long sum = 0;
		
		for (long i=min; i<=max; i++) {
			sum += i;
		}
		
		System.out.println(min + " ~ " + max + "까지의 합계 : " + sum);
	}

}
