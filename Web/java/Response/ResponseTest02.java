package kr.or.ddit.basic.reqNres;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * redirect 방식
		 * ==> 다른 웹페이지로 넘어가도록 한다. (직접 파라미터를 넘길 수 없다.)
		 * ==> 응답시 클라이언트 웹브라우저에게 '이동할 URL'을 전송하면 클라이언트의 웹브라우저가 해당 URL로 이동하는 방식이다.
		 *     다른 페이지로 이동할 때는 GET방식으로 요청하기 때문에 URL의 길이에 제한이 있다.
		 * ==> redirect방식은 request객체를 유지하지 못한다.
		 *     (이유 : 브라우저에게 새롭게 요청하기 때문에...)
		 *         
		 * 이동할 때 사용하는 메서드 : response객체의 sendRedirect()메서드이다.    
		 * 형식) sendRedirect("URL주소");
		 *      URL주소 ==> '컨텍스트 패스'부터 기술한다.
		 *      예) 이동할 주소가 "http://localhost/testWeb/test.do"라고 하면
		 *         컨텍스트패스부터의 주소인 "/testWeb/test.do"와 같이 기술하면 된다.
		 */
		
		// 이동할 페이지 URL주소 : responseRedirectTest.do
		// ==> http://localhost/testWeb/responseRedirectTest.do
		
		// request.setAttribute("tel", "010-1234-5678");
		
		// 파라미터 값을 우선 구한다.
		String name = request.getParameter("userName");
		
		// GET방식으로 보낼 데이터가 한글이 깨질 경우에 URLEncoding처리를 해주어야 한다.
		name = URLEncoder.encode(name, "utf-8");
		
		String tel = "010-1234-5678";
		System.out.println("name = " + name);
				
		// 새로운 페이지에 전달할 데이터를 GET방식으로 구성해서 보낸다.
		response.sendRedirect("/testWeb/responseRedirectTest.do?userName=" + name + "&tel=" + tel);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
