package homework;

import javax.swing.JOptionPane;

public class Homework05_0528_ThreadGame {

	public static String[] game = {"가위", "바위", "보"}; 
	public static String myInput = "";	
	public static String comInput = "";	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		Homework05_0528_ThreadGame obj = new Homework05_0528_ThreadGame();
		obj.comRandom();
		
		Thread gmTi = new GameTimer();
		Thread myIp = new MyInput();
		gmTi.start();
		myIp.start();
		
		try {
			myIp.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		obj.gameMatch(myInput, comInput);
	}

	public void comRandom() {
		int index = (int) (Math.random()*3);
		String comRan = game[index];
		
		comInput = comRan;
	}
	public void gameMatch(String myInput, String comRandom) {
		String my = myInput;
		String com = comRandom;
		
		String result = "";
		if (my.equals(com)) result = "비겼습니다";
		else if (my.equals("가위") && com.equals("보")
				|| my.equals("보") && com.equals("바위")
				|| my.equals("바위") && com.equals("가위"))  result = "이겼습니다";
		else if (com.equals("가위") && my.equals("보")
				|| com.equals("보") && my.equals("바위")
				|| com.equals("바위") && my.equals("가위"))   result = "졌습니다";
		
		System.out.println("=== 결 과 ===");
		System.out.println("컴퓨터 : " + comRandom);
		System.out.println("당신 : " + myInput);
		System.out.println("결과 : " + result);
	}
}


class GameTimer extends Thread {

	@Override
	public void run() {
		for (int i=5; i>=1; i--) {

			if (Homework05_0528_ThreadGame.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("시간초과!");
		System.exit(0);
	}
}


class MyInput extends Thread {
	
	@Override
	public void run() {
		String str = "";
		
		do {
			str = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력해주세요.");			
		} while ( !str.equals("가위") && !str.equals("바위") && !str.equals("보") );
		
		Homework05_0528_ThreadGame.myInput = str;
		Homework05_0528_ThreadGame.inputCheck = true;
	}
	
}