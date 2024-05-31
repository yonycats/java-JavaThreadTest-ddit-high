package kr.or.ddit.basic;

public class T09ThreadDaemonTest {
	public static void main(String[] args) {

		AutoSaveThread autoTh = new AutoSaveThread();
		
		// 데몬 스레드로 설정하기 (start() 실행 전에 설정해야 한다. 
		// 그렇지 않으면 IllegalThreadStateException이 발생한다.
		
		// 디폴트값은 false임, true로 세팅한 순간 데몬으로 세팅한 것
		// 이 부분이 없으면 종료되지 않는다.        
		// 디폴트: 독립==> setDaemon(true): 데몬 스레드로서
		// (메인 스레드 종료시 종속 스레드는 작업을 다 끝내지 못해도 메인 스레드의 종료와 함께 종료된다.)
		autoTh.setDaemon(true);
		
		// 데몬을 실행하지 않으면, 조건이 무한루프이기 때문에 스레드가 계속 돌아감
		// 일반 스레드가 끝나도록 보조하는 역할
		// 일반 스레드가 없으면 데몬 스레드는 의미가 없다.
		autoTh.start();		
		
		try {
			// 작업을 출력해서 가독성 좋게 하기, 너무 빠르면 안되니 1초에 1번 출력되도록
			for (int i=1; i<=20; i++) {
				System.out.println("작업 - " + i);
				
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 스레드 종료");
		
	}
}


class AutoSaveThread extends Thread {
	@Override
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save(); // 저장기능 호출
		}
		
	}
	
	private void save() {
		System.out.println("작업 내용을 저장합니다.");
	}
	
}