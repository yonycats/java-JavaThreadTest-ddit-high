package kr.or.ddit.basic;

public class T17WaitNotifyTest {

	/*
	 	wait() => 동기화 영역에서 락을 풀고 Wait-Set 영역(공유객체별 존재)으로 이동시킨다.
	 	
	 	notify() 또는 notifyALL() => Wait-Set 영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
	 	(notify)는 하나, notifyALL()은 Wait-Set에 존재하는 모든 스레드를 깨운다.)
	 	
	 	=> wait()와 notify(), notifyALL() 메서드는 동기화 영역에서만 실행할 수 있고, 
	 		Object 클래스에서 제공되는 메서드이다.
	 */
	
	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject(); 
		
		// 동기화처리를 했기 때문에, 한개의 메서드가 끝날 때까지 기다렸다 B가 호출됨
		// 번갈아서 호출을 하게 하기 위해서 메서드에 notify()를 사용해볼 것임
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
	
		thA.start();
		thB.start();
	}
}


// 공유객체용 클래스 
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 처리 중...");
		
		// wait에 들어간 methodB를 깨우고 wait에 들어감
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 처리 중...");
		
		// wait에 들어간 methodA를 깨우고 wait에 들어감
		// methodA가 먼저 종료됐고, wait에 들어간 methodB를 깨워줄 곳이 없어서 작업이 끝나지 않음
		notify();
		
		try {
//			wait();
			// 단위는  ms, 3초 뒤에 스스로 깨어나도록 설정
			wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}


// WorkObject의 methodA() 메서드만 10번 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}


//WorkObject의 methodB() 메서드만 10번 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}












