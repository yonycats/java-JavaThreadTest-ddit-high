package kr.or.ddit.basic;

public class T10ThreadStateTest {

	/*
	 	스레드의 상태에 대하여
	 	
	 	- NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
	 	- RUNNABLE : 실행 중 또는 실행 가능한 상태
	 	- BLOCKED : 동기화 블럭에 의해서 일시정지된 상태 (Lock이 풀릴 때까지 기다리는 상태)
	 	- WAITING, TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행이 가능하지 않은 일시정지 상태
	 							   TIMED_WAITING은 일시정지 시간이 지정된 경우임.
	 							   
	 	- TERMINATED : 스레드의 작업이 종료된 상태
	 */
	
	public static void main(String[] args) {
		
		Thread targetThread = new TargetThread();
		
		StatePrintThread spt = new StatePrintThread(targetThread);
		spt.start();
	}
}


// 타겟 스레드의 상태를 출력하기 위한 스레드
class StatePrintThread extends Thread {
	private Thread targetThread;

	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {

		while (true) {
			
			// 스레드의 상태 구하기
			// 0.5초 단위로 타켓의 상태를 가져와서 모니터링하고 있음
			// 종료 시점 : 타겟이 TERMINATED가 되는 시점에 종료됨
			Thread.State state = targetThread.getState();
			System.out.println("타켓 스레드의 상태값 : " + state);
			
			// NEW 상태인지 확인하여 구동시키기
			if (state == Thread.State.NEW) {
				targetThread.start();
			}
			
			// 타겟 스레드가 종료 상태인지 확인하여 무한루프 빠져나가기
			if (state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}


// Target용 스레드
// 상태 변화를 살펴보기 위한 예제
class TargetThread extends Thread {
	
	@Override
	public void run() {
		
		// RUNNABLE 상태
		for (long i=1; i<=10000000000L; i++) {} // 시간 지연용

		// TIMED_WAITING 상태
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// RUNNABLE 상태
		for (long i=1; i<=10000000000L; i++) {} // 시간 지연용
	}
	
}