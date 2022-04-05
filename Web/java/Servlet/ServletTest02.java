package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 이 예제는 애노테이션을 이용해서 Servlet을 등록 설정하는 예제이다.
// 여기서 사용하는 애노테이션은 @WebServlet를 사용하고,
// 이 @WebServlet 애노테이션은 servlet버전 3.0이상에서 사용할 수 있다.

/*
 * - @WebServlet애노테이션의 속성들...
 * 1. name : 서블릿 이름을 설정한다. (기본값 : 빈문자열(""))
 * 2. urlPatterns : 서블릿의 요청URL 패턴을 설정한다.(기본값 : 빈배열({})
 *   예) urlPatterns="/url1" 또는 urlPatterns={"/url1"}
 *       ==> URL패턴을 1개 설정하는 경우
 *   예) urlPatterns={"/url1", "/url2", ...}
 *       ==> URL패턴을 2개 이상 설정하는 경우    
 * 3. value : urlPatterns와 동일한 기능을 한다.
 * 4. description : 주석(설명글)을 설정한다.      
 */
@WebServlet(
		urlPatterns = { "/servletTest02.do" },
		name = "servletTest02",
		description = "애노테이션을 이용한 서블릿 설정입니다."
)
// @WebServlet("/servletTest02.do") ==> urlPatterns만 설정할 경우 이렇게만 기술할 수 있다.
public class ServletTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		// 방법2: print()메서드 또는 println()메서드 이용하기
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>두번째 서블릿</title></head>");
		out.println("<body>");
		out.println("<h2> 두번째 서블릿의 결과 </h2>");
		out.println(a + " + " + b + " = " + c + "<br>");
		out.println(a + " * " + b + " = " + (a*b) + "<br>");
		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
	}
}
