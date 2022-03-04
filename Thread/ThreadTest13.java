package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Comparator;

/*
 * 10마리의 말들이 경주하는 경마 프로그램 작성하기
 * 
 * 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데
 * 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
 * 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다. 
 * (Compare인터페이스 구현)
 * 
 * 경기 구간은 1 ~ 50 구간으로 되어있다.
 * 
 * 경기가 끝나면 등수 순으로 출력한다.
 * 
 * 경기가 진행 중일때는 중간 중간에 말들의 위치를 아래와 같이 나타낸다. (하나의 쓰레드)
 * 예)
 *	  01번말 : ----🐎--------------------(50개)
 *    02번말 : -------------🐎------------
 *      :
 *    10번말 : ---------🐎----------------
 */
public class ThreadTest13 {

	public static void main(String[] args) {
		ThreadTest13 th = new ThreadTest13();
		
		ArrayList<Horse> horList = new ArrayList<Horse>();
		
		horList.add(new Horse("1번말"));
		horList.add(new Horse("2번말"));
		horList.add(new Horse("3번말"));
		horList.add(new Horse("4번말"));
		horList.add(new Horse("5번말"));
		horList.add(new Horse("6번말"));
		horList.add(new Horse("7번말"));
		horList.add(new Horse("8번말"));
		horList.add(new Horse("9번말"));
		horList.add(new Horse("10번말"));
	
	}

}

class Horse extends Thread {
	public String name;
	public static int setRank = 0;
	public int location;

	public Horse(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 50; i++) {
			System.out.print("-");
			try {
				Thread.sleep((int)(Math.random() * 50 + 1));
			} catch (InterruptedException e) {
				
			}
		}
		
		Horse.setRank += name + " ";
	}
}

class HorseRank implements Comparable<Horse> {

	@Override
	public int compareTo(Horse h) {
		
		return Integer.compare(0, );
	}
	
}
