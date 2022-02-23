package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;

// 회원번호의 내림차순으로 정렬될 수 있는 외부 정렬 기준을 작성하시오.

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));

		System.out.println("정렬 전...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.sort(memList);
		
		System.out.println("정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}

}

// Member클래스 작성하기
// 자동 생성 : alt + shift + s
// ==> 회원이름을 기준으로 오름차순 정렬이 되도록 내부 정렬 기준을 추가해보자 ==> Comparable 인터페이스를 구현한다.
class Member implements Comparable<Member>{
	private int num; // 번호
	private String name; // 이름
	private String tel; // 전화번호
	
	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 회원 이름의 오름차순 정렬 기준 만들기
	@Override
	public int compareTo(Member mem) { 
		return name.compareTo(mem.getName());
	}
	
	
	
	
}
