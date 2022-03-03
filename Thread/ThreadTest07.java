package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
 * 사용자의 가위 바위 보는 showInputDialog()를 이용해서 입력 받는다.
 * 
 * 입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초 안에 입력이 없으면 게임에 진 것으로 처리한다.
 * 
 * 5초 안에 입력이 있으면 승패를 구해서 출력한다.
 * 
 * 결과 예시)
 * 1) 5초 안에 입력이 없을 때 
 *   - 결  과 -
 *   시간 초과로 당신이 졌습니다.
 *   
 * 2) 5초 안에 입력이 있을 때
 *   - 결  과 -
 *   컴퓨터 : 가위 
 *   당 신 : 바위
 *   결 과 : 당신이 이겼습니다.  
 */

public class ThreadTest07 {

	public static void main(String[] args) {
		Result re = new Result();
		Count count = new Count();
	    count.start();
		re.start();
	}
}

class Result extends Thread {
	
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		int g = (int) (Math.random() * 3 + 1);

		String c = null;
		
		if (g == 1) {
			c = "바위";
		} else if (g == 2) {
			c = "가위";
		} else if (g == 3) {
			c = "보";
		}

		String str = JOptionPane.showInputDialog("가위, 바위, 보 중에서 입력하세요.");
		inputCheck = true;
		System.out.println("당 신 : " + str);
		
		if(inputCheck == true) {
		System.out.println("컴퓨터 : " + c);
		}
		
		if (c.equals("바위") && str.equals("가위") ||
			c.equals("가위") && str.equals("보")  ||
			c.equals("보") && str.equals("바위")) {
			System.out.println("당신이 졌습니다.");
		} else if (c.equals("바위") && str.equals("보") ||
				   c.equals("가위") && str.equals("바위") ||
				   c.equals("보") && str.equals("가위")) {
			System.out.println("당신이 이겼습니다.");
		} else if (c.equals("바위") && str.equals("바위") ||
				   c.equals("가위") && str.equals("가위") ||
				   c.equals("보") && str.equals("보") ) {
			System.out.println("비겼습니다.");
		}		
	}
}

class Count extends Thread{
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {
			System.out.println(i);
			
			if(Result.inputCheck == true) {
				return; 
			}
			
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				
			}
		}
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}