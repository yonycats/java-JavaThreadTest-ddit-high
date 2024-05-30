package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
	10마리의 말들이 경주하는 경마 프로그램 작성하기
	
	말은 Horse라는 이름의 클래스로 구성하고,
	이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
	그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
	기능이 있다.( Comparable 인터페이스 구현)
	
	경기 구간은 1~50구간으로 되어 있다.
	
	경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
	예)
	1번말 --->------------------------------------
	2번말 ----->----------------------------------
	...
	
	경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */


public class Homework05_0528 {
	static int ran = 1;
	
	public static void main(String[] args) throws InterruptedException {
		
		List<Horse> game = new ArrayList<Horse>();
		
		for(int i = 1; i <= 10 ; i ++ ) {
			game.add(new Horse(i+"번말"));
		}
		
		for (Horse hs : game) {
			hs.start();
		}
		
		while(Homework05_0528.ran<=10) {
			for (Horse hs : game) {
				System.out.println(hs.getStr());
			}
			System.out.println();
			System.out.println();
			Thread.sleep(200);
		}
		
        for (Horse hs : game) {
            hs.join();
        }
		
		Collections.sort(game);
		System.out.println();
		
		System.out.println("경기 끝");
		System.out.println("---------------------");
		System.out.println("경기 결과");
		System.out.println();
		System.out.println("=====================");
		System.out.println("순위\t:\t이름");
		System.out.println("---------------------");
		
		for (Horse hs : game) {
			System.out.println(hs.getName1()+"\t:\t"+hs.getRank()+"등");
		}

	}

}


class Horse extends Thread implements Comparable<Horse> {
	private String name;
	private int rank;
	private String str;

	public Horse(String name) {
		super();
		this.name = name;
	}

	public String getName1() {
		return name;
	}
	public void setName1(String name) {
		this.name = name;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public void run() {
		int section = 50;
		String a1 = "=";
		String a2 = "-";
		String b = ">";

		for (int i=1; i<=section; i++) {
			String strIng = name + "\t";
			for (int j=1; j<i; j++) {
				strIng += a1;
			}
			strIng += b;

			for (int j=i+1; j<=section; j++) {
				strIng += a2;
			}
			
			setStr(strIng);
			
			try {
				Thread.sleep( (int)(Math.random() * 500 + 200) );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println(name + "\t도착!");
		setRank(Homework05_0528.ran);
		Homework05_0528.ran++;
	}
	@Override
	public int compareTo(Horse o) {
		return Integer.compare(this.getRank(), o.getRank());
	}

}