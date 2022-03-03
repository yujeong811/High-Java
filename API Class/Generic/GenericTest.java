package kr.or.ddit.basic.generic;

/*
 * Generic 클래스 만드는 방법
 * 형식) class 클래스명<제네릭타입글자> {
 * 			private 제네릭타입글자 변수명;   // 변수 선언에 제네릭을 사용할 경우
 * 			...
 * 
 * 			제네릭타입글자 메서드명() {      // 반환값이 있는 메서드에서 사용하기
 * 				...
 * 				return 값;
 * 			}
 * 
 * 			void 메서드명(제네릭타입글자 변수명) {   // 메서드의 매개변수에 제네릭 사용하기
 * 				...
 * 			}
 *      }
 *      
 * -- 제네릭타입글자 --
 * T ==> Type     
 * K ==> Key
 * V ==> Value
 * E ==> Element    
 */

class MyGeneric<T> {
	private T value;
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
}





class NonGenericClass {
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}

public class GenericTest {

	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setValue("가나다라");

		NonGenericClass ng2 = new NonGenericClass();
		ng2.setValue(100);
		
		String rtnNg1 = (String)ng1.getValue();
		System.out.println("문자열 반환값 rtnNg1 = " + rtnNg1);
		
		Integer irtnNg2 = (Integer)ng2.getValue();
		System.out.println("정수형 반환값 itrnNg2 = " + irtnNg2);
		System.out.println("------------------------------------");
		
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setValue("안녕하세요.");
		
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setValue(500);
		
		// 제네릭을 사용한 클래스에서 데이터를 꺼내올 때 형변환 없이 사용 가능하다.
		String rtnMg1 = mg1.getValue();
		Integer irtnMg2 = mg2.getValue();
		
		System.out.println("제네릭 문자열 반환값 : " + rtnMg1);
		System.out.println("제네릭 정수형 반환값 : " + irtnMg2);
		
		// 제네릭을 사용했을 때와 사용하지 않았을 때의 오류가 발생하는 시점이 서로 다른 경우
		
//		NonGenericClass test = new NonGenericClass();
//		test.setValue("우리나라");
		
//		실행 단계에서 오류가 발생한다.
//		Integer num = (Integer)test.getValue();  // 문법적으론 오류가 안나지만 실행할때 오류남
//		System.out.println("num = " + num);
//	  --------------------------------------------------	
//		MyGeneric<String> test2 = new MyGeneric<>();
//		test2.setValue("대한민국");
		
//		컴파일 단계에서 오류가 발생한다.
//		Integer num2 = test2.getValue();  // 컴파일 에러남
//		System.out.println("num2 = " + num2);
	}

}
