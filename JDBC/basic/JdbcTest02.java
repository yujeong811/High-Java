package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 문제) 사용자로부터 LPROD_ID값을 입력 받아서 입력한 값보다 LPROD_ID가 큰 자료들을 출력하시오.
 */
public class JdbcTest02 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("lprod_id 입력 : ");
		int num = scan.nextInt();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe" 
					, "LYJ98" 
					, "java"
			);

			String sql = "select * from lprod where lprod_id > " + num;

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			System.out.println("== 쿼리문 처리 결과 ==");

			while (rs.next()) {
					System.out.println("Lprod_ID : " + rs.getInt("lprod_id"));
					System.out.println("Lprod_GU : " + rs.getString(2));
					System.out.println("Lprod_NM : " + rs.getString("lprod_nm"));
					System.out.println("-------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {}
			if (stmt != null) try {stmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}

	}

}
