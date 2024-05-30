package kr.or.ddit.basic;

public class T15SyncAccountTest {
	public static void main(String[] args) {
		
		SyncAccount sAcc = new SyncAccount();
		// 10000원 입금하기
		sAcc.deposit(10000);
		
		BankThread bTh = new BankThread(sAcc);
		// 6000원 인출하기
		bTh.start();
		
		BankThread bTh2 = new BankThread(sAcc);
		// 잔액이 없어도 출금이 되는 상황 발생 => 동기화 필요
		// Withdraw()에  synchronized 붙여서 동기화 처리함 => 출금 실패
		bTh2.start();
		
	}
}


// 은행의 입출금을 관리하기 위한 클래스
class SyncAccount {
	private int balance; // 잔액

	public int getBalance() {
		return balance;
	}
	
	
	// 입금 처리를 수행하기 위한 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	
	// 출금을 처리하기 위한 메서드 (출금 성공 : true, 출금 실패 : false 반환)
	// 동기화 영역에서 호출되는 메서드도 동기화 처리를 해주어야 한다.
	
// 동기화 방법 1
//	synchronized public boolean Withdraw(int money) {
//		
//		// 잔액(balance)이 출금요청한 금액 (money)보다 작은지 큰지에 따라 반환
//		if (balance >= money) {
//			// 시간 지연시키기
//			for (int i=1; i<1000000000; i++) {}
//			
//			balance -= money; // 출금작업
//			
//			System.out.println("메서드 안에서 balance = " + getBalance());
//			
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	
// 동기화 방법 2
	public boolean Withdraw(int money) {

		synchronized (this) {
			if (balance >= money) {
				// 시간 지연시키기
				for (int i=1; i<1000000000; i++) {}

				balance -= money; // 출금작업

				System.out.println("메서드 안에서 balance = " + getBalance());

				return true;
			} else {
				return false;
			}
		}
	}
	
	
	
}


// 은행 업무를 처리하기 위한 스레드
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.Withdraw(6000); // 6000원 인출
		System.out.println("스레드 안에서 result = " + result + ", balace = " + sAcc.getBalance());
	}
}







