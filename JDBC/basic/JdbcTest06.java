package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * 회원을 관리하는 프로그램을 작성하시오.
 * (MYMEMBER 테이블 이용)
 * 
 * 아래 메뉴의 기능을 모두 구현하시오(CRUD기능 구현하기)
 * 
 * 메뉴예시)
 * ----------------------
 *     == 작업 선택 ==
 *     1. 자료 추가 		-> insert (C)
 *     2. 자료 수정 		-> update (U)
 *     3. 자료 삭제 		-> delete (D)
 *     4. 전체 자료 출력   -> select (R)
 *     0. 작업 끝
 * ----------------------
 * 
 * 조건)
 * 1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
 * 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 * 3) 자료 수정에서 '회원ID'는 변경되지 않는다.
 */
public class JdbcTest06 {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new JdbcTest06().memberStart();
	}

	private void memberStart() {
		
		while (true) {
			System.out.println();
			System.out.println("*****************************");
			System.out.println("       회원 관리 프로그램");
			System.out.println("-----------------------------");
			System.out.println("1. 자료 추가");
			System.out.println("2. 자료 수정");
			System.out.println("3. 자료 삭제");
			System.out.println("4. 자료 전체 출력");
			System.out.println("0. 작업 끝");
			System.out.println("*****************************");
			System.out.print("번호 입력 >> ");
			int choice = scan.nextInt();
			
			switch (choice) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				select();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}

	private void insert() {
		String id;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			
			do {
				System.out.print("아이디 입력 : ");
				id = scan.next();

				String sql = "select count(*) cnt from mymember where mem_id = ?";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}

				if (count > 0) {
					System.out.println("입력한 아이디 " + id + "는 이미 등록된 아이디입니다.");
					System.out.println("다시 입력해주세요.");
				}

			} while (count > 0);

			System.out.print("이름 입력 : ");
			String name = scan.next();

			System.out.print("비밀번호 입력 : ");
			String pass = scan.next();

			System.out.print("전화번호 입력 : ");
			String tel = scan.next();

			System.out.print("주소 입력 : ");
			String add = scan.next();

			String sql = "insert into mymember(mem_id, mem_name, mem_pass, mem_tel, mem_addr)"
					+ "    values(?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setString(4, tel);
			pstmt.setString(5, add);

			int cnt = pstmt.executeUpdate();

			System.out.println();
			if (cnt > 0) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패~~~");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
	}

	private void update() {
	
		try {
			conn = DBUtil.getConnection();
			
			String id;
			int count = 0;
			
			do {
				System.out.print("수정할 아이디 입력 : ");
				id = scan.next();

				String sql = "select count(*) cnt from mymember where mem_id = ?";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}

				if (count == 0) {
					System.out.println("입력한 아이디 " + id + "는 등록되지 않은 아이디입니다.");
					System.out.println("다시 입력해주세요.");
				}

			} while (count == 0);
			
			System.out.print("이름 입력 : ");
			String name = scan.next();

			System.out.print("비밀번호 입력 : ");
			String pass = scan.next();

			System.out.print("전화번호 입력 : ");
			String tel = scan.next();

			System.out.print("주소 입력 : ");
			String add = scan.next();
			
			String sql = "update mymember"
					+ "       set mem_name = ?, mem_pass = ?, mem_tel = ?, mem_addr =?"
					+ "     where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.setString(3, tel);
			pstmt.setString(4, add);
			pstmt.setString(5, id);

			int cnt = pstmt.executeUpdate();
			
			System.out.println();
			if (cnt > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패~~~");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
	}

	private void delete() {
		String id;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			
			do {
				System.out.print("아이디 입력 : ");
				id = scan.next();

				String sql = "select count(*) cnt from mymember where mem_id = ?";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}

				if (count == 0) {
					System.out.println("입력한 아이디 " + id + "는 등록되지 않은 아이디입니다.");
					System.out.println("다시 입력해주세요.");
				}

			} while (count == 0);

			String sql = "delete from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			int cnt = pstmt.executeUpdate();

			System.out.println();
			if (cnt > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패~~~");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}

	}

	private void select() {
		try {
			conn = DBUtil.getConnection();

			String sql = "select * from mymember";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			System.out.println("========= 회원 정보 =========");
			
			// rs.next() ==> ResultSet객체의 데이터를 가리키는 포인터를 다음번째 위치로 이동시키고
			//               그 곳에 데이터가 있으면 true, 없으면 false를 반환한다.
			while(rs.next()) {
				// 포인터가 가리키는 곳의 자료를 가져오는 방법
				// 형식1) rs.get자료형이름("컬럼명") -> 컬럼명은 대소문자 상관 없음
				// 형식2) rs.get자료형이름(컬럼번호) -> 컬럼번호는 1번부터 시작
				// 형식3) rs.get자료형이름("컬럼의 alias명")
				System.out.println("아이디 : " + rs.getString(1));
				System.out.println("이름 : " + rs.getString(2));
				System.out.println("비밀번호 : " + rs.getString(3));
				System.out.println("전화번호 : " + rs.getString(4));
				System.out.println("주소 : " + rs.getString(5));
				System.out.println("---------------------------");			
			}	

		} catch (SQLException e) {
			e.printStackTrace();

		} finally { 
			if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}

	}

}
