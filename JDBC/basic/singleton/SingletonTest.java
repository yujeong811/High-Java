package kr.or.ddit.basic.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		
//		MySingleton test1 = new MySingleton(); // 외부에서 new 명령으로 생성 불가

		MySingleton test2 = MySingleton.getInstance(); // getInstance()를 여러번 써도 같은 객체
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 => " + test2); // test2, test3 같은 참조값 주소 나옴
		System.out.println("test3 => " + test3);
		
		System.out.println(test2 == test3);
		System.out.println(test2.equals(test3));
		
		test2.displayTest();
	}

}
