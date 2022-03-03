package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/*
 * 문제) Set을 활용하여 숫자 야구 게임 프로그램을 작성하시오.
 *      컴퓨터의 숫자는 난수를 이용해서 구한다.
 *      (스트라이크는 S, 볼은 B로 나타낸다.)
 *      
 * 예시) 컴퓨터 난수 ==> 9 5 7
 * 
 *       숫자 입력 : 3 5 6     
 *       출력 : 3 5 6 => 1S 0B
 *       
 *       숫자 입력 : 7 8 9   
 *       출력 : 7 8 9 => 0S 2B
 *       
 *       숫자 입력 : 9 7 5  
 *       출력 : 9 7 5 => 1S 2B
 *       
 *       숫자 입력 : 9 5 7
 *       출력 : 9 5 7 => 3S 0B
 *       
 *       축하합니다...
 *       당신은 4번째만에 맞췄습니다.
 */
public class BaseballTest {

	public static void main(String[] args) {
		
		HashSet<Integer> baseball = new HashSet<Integer>();
		
		while(baseball.size() < 3) {
			int random = (int)(Math.random() * 9 + 1);
			baseball.add(random);
		}
		
		ArrayList<Integer> numList = new ArrayList<Integer>();
		for(int num : baseball) {
			numList.add(num);
		}
		Collections.shuffle(numList);
		System.out.println(numList);
		
		Scanner sc = new Scanner(System.in);
		
		int strike = 0;
		int ball = 0;
		int i = 0;
		
		do {
			strike = 0;
			ball = 0;
			
			System.out.print("첫번째 숫자를 입력하세요 : ");
			int num1 = Integer.parseInt(sc.nextLine());
			
			System.out.print("두번째 숫자를 입력하세요 : ");
			int num2 = Integer.parseInt(sc.nextLine());
			
			System.out.print("세번째 숫자를 입력하세요 : ");
			int num3 = Integer.parseInt(sc.nextLine());
			
			if (numList.get(0) == num1) {
				strike++;
			} else if (numList.get(1) == num1 || numList.get(2) == num1) {
				ball++;
			}

			if (numList.get(1) == num2) {
				strike++;
			} else if (numList.get(0) == num2 || numList.get(2) == num2) {
				ball++;
			}

			if (numList.get(2) == num3) {
				strike++;
			} else if (numList.get(0) == num3 || numList.get(1) == num3) {
				ball++;
			}

			System.out.println(strike + "S " + ball + "B");
			
			i++;
			
		} while (strike != 3);
		
		System.out.println("축하합니다. 당신은 " + i + "번째만에 맞췄습니다.");

	}

}
