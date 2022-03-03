package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 문제) 학번, 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 *      이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화한다.
 *      이 클래스는 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다.
 *      
 *      이 Student객체는 List에 저장하여 관리한다.
 *      
 *      List에 저장된 Student객체를 총점의 역순으로 정렬하는데 총점이 같으면
 *      이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스도 작성하시오.
 *      
 *      (단, 등수는 List에 전체 데이터가 모두 저장된 후에 구한다.)
 */
public class StudentTest {

	// 등수를 구하는 메서드
	public void setRanking(ArrayList<Student> stuList) {
		for(Student stu1 : stuList) { // 기준이 되는 데이터를 위한 반복문 (등수를 구할 값)
			int rank = 1; // 처음에는 등수를 1로 초기화한다.
			for(Student stu2 : stuList) { // 비교 대상을 찾기 위한 반복문
				if(stu1.getHap() < stu2.getHap()) {
					rank++;
				}
			}
			// 구해진 등수를 Student객체의 rank변수에 저장한다.
			stu1.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		
		ArrayList<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student(1, "이유정", 100, 100, 100));
		stuList.add(new Student(5, "강정인", 95, 87, 71));
		stuList.add(new Student(3, "오혜지", 50, 65, 89));
		stuList.add(new Student(4, "노혜지", 49, 68, 91));
		stuList.add(new Student(2, "문선호", 18, 67, 85));
		stuList.add(new Student(6, "박보검", 54, 82, 39));
		stuList.add(new Student(7, "송강", 49, 68, 91));
		
		test.setRanking(stuList); // 등수를 구하는 메서드 호출
		
		System.out.println("정렬 전");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println();
		
		Collections.sort(stuList);
		
		System.out.println("학번의 오름차순 정렬 후");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println();
		
		Collections.sort(stuList, new SortHap());
		
		System.out.println("총점의 내림차순 정렬 후");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
	}
}

class Student implements Comparable<Student> {

	private int hak;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int hap;
	private int rank;
	
	public Student(int hak, String name, int kor, int eng, int math) {
		super();
		this.hak = hak;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		hap =  kor + eng + math;
	}
	
	public int getHak() {
		return hak;
	}

	public void setHak(int hak) {
		this.hak = hak;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getHap() {
		return hap;
	}

	public void setHap(int hap) {
		this.hap = hap;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [hak=" + hak + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", hap=" + hap + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student stu) { // 학번의 오름차순 내부 정렬 기준 만들기
		return Integer.compare(hak, stu.getHak());
	}
	
}

// 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬하는 외부 정렬 기준 클래스
class SortHap implements Comparator<Student> {

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getHap() == stu2.getHap()) {
			return stu1.getName().compareTo(stu2.getName());
		} else {
			return Integer.compare(stu1.getHap(), stu2.getHap()) * -1;
//			return new Integer(stu1.getHap()).compareTo(stu2.getHap()) * -1;
		}		
	}	
}
