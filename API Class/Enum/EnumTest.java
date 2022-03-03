package kr.or.ddit.basic.enumTest;

/*
 * enum(열거형) 
 * 	==> 서로 관련있는 상수들의 집합을 나타낸다.
 * 	==> 클래스처럼 보이게하는 상수
 *  ==> 열거형은 클래스처럼 독립된 java파일로 만들 수 있고, 하나의 java파일에 클래스와 같이 만들 수 있고,
 *      클래스 안에 내부 클래스처럼 만들 수 있다.
 * 
 * - 열거형의 속성 및 메서드
 *   1) name()    			 ==> 열거형 상수의 이름을 문자열로 반환한다.     
 *   2) ordinal() 			 ==> 열거형 상수가 정의된 순서값(index값)을 반환한다. (0부터 시작)
 *   3) valueOf("열거형상수명") ==> 지정된 열거형에서 '열거형상수명'과 일치하는 열거형 상수를 반환한다.
 *   4) 열거형이름.상수명        ==> 해당 열거형 상수를 반환한다. valueOf()메서드와 같은 결과를 나타낸다.
 *   5) 열거형이름.values()    ==> 모든 상수들을 배열로 가져온다.
 *   
 * - 열거형 선언하기
 * 방법1) enum 열거형이름 {상수명1, 상수명2, ...., 상수명n}
 * 방법2) 상수에 임의의 값을 지정하는 방법
 * 		 enum 열거형이름 {
 * 			상수명1(값들...),
 * 			상수명2(값들...),
 * 			...
 * 			상수명n(값들...);  // 마지막 상수를 지정한 후에 세미콜론을 붙인다.
 * 			
 * 			// '값들'이 저장될 변수들을 선언한다...
 * 			private 자료형이름 변수명1;
 * 			....
 * 
 * 			// 열거형의 생성자를 만든다.
 *			// 열거형의 생성자는 '열거형 상수에 지정된 '값들'을 값들이 저장될 변수에 셋팅하는 역할을 한다.
 *			// 열거형 생성자의 접근제한자는 묵시적으로 private이다.
 *
 *			// 변수명의 개수는 '값들'의 개수와 같아야 하고 각 변수들의 자료형도 '값들'의 자료형과 같아야 한다.
 *			private 열거형이름(자료형이름 변수명1, ... ) {
 *				위에 선언된 변수들을 초기화하는 작업 진행
 *				...
 *			}
 *
 *			// 위에 선언된 변수의 값을 외부에서 불러서 사용할 수 있도록 getter메서드를 만든다. (접근제한자는 public으로 한다.)
 *			public 자료형이름 get변수명() {
 *				return 변수명;
 *			}
 *			...
 * 		 }
 */

public class EnumTest {
	public enum Color { RED, GREEN, BLUE }
	public enum Count { ONE, TWO, THREE }
	
	public enum Season {
		봄(1, "3월부터 5월까지"),  // 상수명(값들) 형식의 선언
		여름(2, "6월부터 8월까지"),
		가을(3, "9월부터 11월까지"),
		겨울(4, "12월부터 2월까지");
		
		// '값들'이 저장될 변수 선언
		private int num;
		private String span;
		
		// 열거형의 생성자 작성하기 ==> 열거형의 생성자의 접근제한자는 무조건 private이어야 한다.
		//                         접근제한자를 생략하면 자동으로 private이 된다.
		Season(int num, String span) {
			this.num = num;
			this.span = span;
		}

		// getter작성하기
		public int getNum() {
			return num;
		}

		public String getSpan() {
			return span;
		}		
		
	}

	public static void main(String[] args) {
	
//		System.out.println("Red : " + ConstTest.RED);
//		System.out.println("Three : " + ConstTest.THREE);
//		
//		if(ConstTest.RED == ConstTest.TWO) {
//			System.out.println("같다.");
//		} else {
//			System.out.println("다르다.");
//		}
		
		Color mycol = Color.valueOf("GREEN"); // Color.GREEN와 같다.
		Count mycnt = Count.THREE; // Count.valueOf("THREE")와 같다.
		
	    System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
		
		System.out.println("mycol의 ordinal : " + mycol.ordinal());
		System.out.println("mycnt의 ordinal : " + mycnt.ordinal());
		System.out.println("----------------------------");
		
		// 서로 다른 종류의 열거형끼리의 비교는 불가능하다.
//		if(mycol == mycnt) {
//			System.out.println("같다.");
//		} else {
//			System.out.println("다르다.");
//		}
		
		// if문에서의 비교...
		if(mycol == Color.BLUE) {
			System.out.println("같다.");
		} else {
			System.out.println("다르다.");
		}
		System.out.println("----------------------------");
		
		switch(mycnt) {
		// case문에 지정하는 열거형값은 열거형이름을 뺀 상수명만 사용해야 한다.
		case ONE:
			System.out.println("하나");
			break;
		case TWO:
			System.out.println("둘");
			break;
		case THREE:
			System.out.println("셋");
			break;
		}
		System.out.println("----------------------------");
		
		Season ss = Season.봄;
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("num : " + ss.getNum());
		System.out.println("span : " + ss.getSpan());
		System.out.println("----------------------------");
		
		// Season열거형 전체 데이터 출력하기
		for(Season time : Season.values()) {
			System.out.println(time.name() + "==" + time + " ==> " + time.getSpan());
		}
		System.out.println();
		
		for(Color col : Color.values()) {
			System.out.println(col + " => " + col.ordinal());
		}
	}

}
