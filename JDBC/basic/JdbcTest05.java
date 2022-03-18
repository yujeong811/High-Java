package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * Lprod테이블에 새로운 데이터를 추가하기
 * 
 * lprod_gu와 lprod_nm은 직접 입력받아서 처리하고,
 * lprod_id는 현재의 lprod_id 중에서 제일 큰 값보다 1크게 한다.
 * 
 * 그리고 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
 */

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			/*
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "LYJ98", "java");
			*/
			conn = DBUtil.getConnection();
			
			// lprod_id는 현재의 lprod_id 중에서 제일 큰 값보다 1크게 한다.
//			String sql = "select max(lprod_id) from lprod";
			String sql = "select max(lprod_id) maxnum from lprod";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			int maxNum = 0;
			
			if(rs.next()) {
				// alias가 없을 때
//				maxNum = rs.getInt("max(lprod_id)");
//				maxNum = rs.getInt(1);
				
				// alias가 있을 때
				maxNum = rs.getInt("maxnum");		
			}
			maxNum++;
			
			// 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			String gu; // 상품분류코드(lprod_gu)
			int count = 0; // 입력한 상품분류코드의 개수가 저장될 변수
			
			do {
				System.out.print("상품분류코드 입력 : ");
				gu = scan.next();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
				
				pstmt = conn.prepareStatement(sql2);
				
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count > 0) {
					System.out.println("입력한 상품분류코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
				
			} while(count > 0); // 중복되면 반복처리 되도록 한다.	
			
			System.out.print("상품분류명 입력 : ");
			String nm = scan.next();
			
			String sql3 = "insert into lprod " + " (lprod_id, lprod_gu, lprod_nm) "
				    	+ "values(?, ?, ?)";

			pstmt = conn.prepareStatement(sql3);
			
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패~~~");
			}

		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if (stmt != null) try {stmt.close();} catch (SQLException e) {}
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
			if (rs != null) try {rs.close();} catch (SQLException e) {}
		}
	}
}
