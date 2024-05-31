package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T16SyncCollectionTest {
	
	/*
	 	Vector, Hashtable 등의 예전부터 존재하던 Collection클래스들은  내부에 동기화 처리가 되어 있다.
	 	하지만, 최근에 새로 구성된 컬렉션 클래스들은  동기화 처리가 되어있지 않다.
	 	그래서 동기화가 필요한 경우에는 직접 동기화 처리를 해서 사용해야 한다.
	 */
	
	
	// 동기화 처리를 하지 않았을 경우
	private static List<Integer> List1 = new ArrayList<Integer>();
	
	// 동기화 처리를 했을 경우
	private static List<Integer> List2 = Collections.synchronizedList(new ArrayList<Integer>());

	
	
	public static void main(String[] args) {
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// 스레드 하나당 인덱스를 만개씩 추가하고 있음 (5만개)
				// 스레드끼리 부딪혀 오류가 발생하므로 5만개가 만들어지지 않음
				for(int i=1; i<=10000; i++) {
//					List1.add(i);
					List2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r),
				new Thread(r)
		};
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("list1의 갯수 : " + List1.size());
		System.out.println("list2의 갯수 : " + List2.size());
		
		
	}
}
