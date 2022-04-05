package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿이란? ==> 컨테이너(서블릿엔진)에 의해 관리되는 자바 기반의 웹 컴포넌트로서 동적인 웹 컨텐츠 생성을 가능하게 해준다.

/*
 * 요청URL주소 : http://localhost:80/testWeb/test.jsp
 * - http ==> 프로토콜
 * - localhost ==> 서버의 IP주소 또는 컴퓨터이름(도메인명)
 * - 80 ==> 포트번호 (80번은 생략 가능)
 * - /testWeb ==> 컨텍스트 패스 (보통 프로젝트 이름으로 지정한다.)
 * - test.jsp ==> 실제 실행할 문서명 또는 서블릿
 * 
 * 이 예제는 배포 서술자(web.xml)를 이용해서 요청한 URL주소와 실행할 클래스를 연결해 주는 예제이다.
 */
public class ServletTest01 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 이 영역에서는 대부분 service()메서드 또는 doGet()메서드나 doPost()메서드를 재정의 해서 작성한다.
	
	// doGet()메서드나 doPost()메서드는 service() 메서드를 통해서 호출된다.
	
    // 이 메서드들은 다음과 같은 매개변수를 자동으로 갖는다.
	// 1) HttpServletRequest ==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// 2) HttpServletResponse ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이 메서드는 GET방식으로 요청이 왔을 때 처리되는 메서드입니다.
		
		// 응답 문서의 인코딩 방식 설정
		response.setCharacterEncoding("utf-8");
		// 응답 문서의 ContentType 설정
		response.setContentType("text/html; charset=UTF-8");
		
		// 처리한 내용을 응답으로 보내기 위해서 스트림 객체가 필요하다.
		// 여기서 사용하는 스트림 객체는 PrintWriter객체를 사용한다.
		// 이 스트림 객체는 Response객체를 통해서 구한다.
		PrintWriter out = response.getWriter();
		
		int a = 10;
		int b = 50;
		int c = a + b;
		
		// 처리한 결과를 출력한다.
		// 방법1: append() 메서드 이용하기
		out.append("<!DOCTYPE html>")
		   .append("<html>")
		   .append("<head><meta charset='utf-8'>")
		   .append("<title>첫번째 서블릿</title></head>")
		   .append("<body>")
		   .append(a + " + " + b  + " = " + c + "<br>")
		   .append(a + " * " + b + " = " + (a * b) + "<br>")
		   .append("</body>")
		   .append("</html>");	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이 메서드는 POST방식으로 요청이 왔을 때 처리되는 메서드입니다.
	}
}
