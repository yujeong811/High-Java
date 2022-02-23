package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  문제1) 5명 별명을 입력받아 ArrayList에 저장하고 이 별명 중에 제일 긴 별명을 출력하시오.
 *       (단, 별명의 길이는 모두 다르게 입력한다.)
 *       
 *  문제2) 5명 별명을 입력받아 ArrayList에 저장하고 이 별명 중에 제일 긴 별명을 출력하시오.
 *       (단, 별명의 길이는 같은 것을 입력할 수 있다.)
 */
public class ArrayListTest03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<String>();
		
		System.out.println("5명의 별명을 입력하세요.");
		for(int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 사람의 별명 입력 : ");
			String name = sc.nextLine();
			nameList.add(name);
		}

		// 첫번째 문제
		// 제일 긴 별명이 저장될 변수 선언
		// ==> List의 첫번째 자료로 초기화 한다.
		String max = nameList.get(0);
		for(int i = 1; i < nameList.size(); i++) {
			if(max.length() < nameList.get(i).length()) {
				max = nameList.get(i);
			}
		}
		System.out.println("제일 긴 별명 : " + max);
		
		// 두번째 문제
		// 제일 긴 별명의 길이가 저장될 변수 선언
		// ==> 첫번째 별명의 길이로 초기화한다.
		int maxLength = nameList.get(0).length();
		for(int i = 1; i < nameList.size(); i++) {
			if(maxLength < nameList.get(i).length()) {
				maxLength = nameList.get(i).length();
			}
		}

		System.out.println("제일 긴 별명들...");
		for(String str : nameList) {
			if(maxLength == str.length()) {
				System.out.println(str);
			}
		}
		
	}

}
