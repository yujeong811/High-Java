package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest { // properties는 환경설정할때 많이 쓰임

	public static void main(String[] args) {
		/*
		 * Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
		 * 
		 * Map은 key값과 value값에 모든 형태의 객체를 사용할 수 있지만 Properties는 key값과 value값에 String만 사용할 수 있다. // 숫자도 문자열처럼
		 * 
		 * Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만 Properties는 setProperty(), getProperty()메서드를 통해서 데이터를 입출력한다.
		 * 
		 * Properties는 데이터를 파일로 입출력할 수 있다.
		 */

		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "30");
		
		int age = 40;
		prop.setProperty("age2", age + ""); // int인 변수를 문자열로 바꾸는 방법1
		prop.setProperty("age3", String.valueOf(age)); // 방법2
		
		prop.setProperty("tel", "010-1111-1111");
		prop.setProperty("addr", "대전");
		
		String name = prop.getProperty("name");
		int rtnAge = Integer.parseInt(prop.getProperty("age"));
		int rtnAge2 = Integer.parseInt(prop.getProperty("age2"));
		int rtnAge3 = Integer.parseInt(prop.getProperty("age3"));
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 : " + name);
		System.out.println("나이1 : " + rtnAge);
		System.out.println("나이2 : " + rtnAge2);
		System.out.println("나이3 : " + rtnAge3);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + addr);
							
	}

}
