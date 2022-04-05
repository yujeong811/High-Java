package kr.or.ddit.basic.reqNres;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num1 = Integer.parseInt(request.getParameter("num1"));
		String cal = request.getParameter("cal");
		int num2 = Integer.parseInt(request.getParameter("num2"));
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Request객체 연습</title></head>");
		out.println("<body>");
		
		double result = 0; // 계산된 결과가 저장될 변수
		boolean calcOk = true; // 계산 성공 여부가 저장될 변수
		
		switch(cal) {
		case "+" : result = num1 + num2; break;
		case "-" : result = num1 - num2; break;
		case "*" : result = num1 * num2; break;
		case "/" : 
			if(num2 != 0) {
				result = (double)num1 / num2; break;	
			} else {
				calcOk = false;
			}
			break;
		case "%" : 
			if(num2 != 0) {
				result = num1 % num2; break;	
			} else {
				calcOk = false;
			}
			break;
		}
		
		out.println("<h2>계산 결과</h2><hr>");
		out.print(num1 + " " +  cal + " " +  num2 + " = ");
		
		if(calcOk == true) {
			out.println(result);
		} else {
			out.println("계산 불능");
		}
		
		// if문 계산
//		if(cal.equals("+")) {
//			out.println(num1 + " " +  cal + " " + num2 + " = " + (num1 + num2));
//		} else if(cal.equals("-")) {
//			out.println(num1 + " " +  cal + " " + num2 + " = " + (num1 - num2));
//		} else if(cal.equals("*")) {
//			out.println(num1 + " " +  cal + " " + num2 + " = " + (num1 * num2));
//		} else if(cal.equals("/")){
//			out.println(num1 + " " +  cal + " " + num2 + " = " + (num1 / num2));
//		} else {
//			out.println(num1 + " " +  cal + " " + num2 + " = " + (num1 % num2));
//		}
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
