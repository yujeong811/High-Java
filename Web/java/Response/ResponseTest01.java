package kr.or.ddit.basic.reqNres;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * - forward방식 ==> 특정 서블릿에 대한 요청을 다른 서블릿이나 JSP로 제어를 넘긴다.(파라미터도 넘길 수 있다.)
		 *              ==> 클라이언트의 웹브라우저의 URL주소가 변경되지 않는다.
		 *              	(처음 요청할 때의 URL주소로 유지된다.)
		 *              ==> 서버 내부에서만 접근이 가능하다.
		 *              ==> redirect보다 성능이 좋다.
		 * 예) 현재 서블릿에서 responseForwardTest.do주소로 forward방식으로 이동하기   
		 * 
		 * getRequestDispatcher()메서드 형식
		 * ==> getRequestDispatcher("이동할 서블릿이나 jsp주소");       
		 * ==> "이동할 서블릿이나 jsp주소는 '컨텍스트 패스'이후의 주소를 기술하면 된다.
		 * 예) 이동할 주소가 "http://localhost/testWeb/test.do" 라고 하면
		 *     컨텍스트패스 이후의 주소인 "/test.do" 만 기술하면 된다.
		 */
//		request.getParameter("userName");
		
		// 이동되는 페이지로 현재 서블릿에서 구성한 데이터를 넘기려면
		// request객체의 setAttribute()메서드를 이용해서 넘겨준다.
		// 형식) setAttribute("키값", 넘겨줄 데이터)
		
		request.setAttribute("tel", "010-1234-5678");
		
		RequestDispatcher rd = request.getRequestDispatcher("/responseForwardTest.do");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
