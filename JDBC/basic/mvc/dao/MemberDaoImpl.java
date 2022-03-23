package kr.or.ddit.basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	@Override
	public int insertMember(Connection conn, MemberVO memVo) throws SQLException {
		String sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
				+ "   values(?,?,?,?,?) ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_id());
		pstmt.setString(2, memVo.getMem_pass());
		pstmt.setString(3, memVo.getMem_name());
		pstmt.setString(4, memVo.getMem_tel());
		pstmt.setString(5, memVo.getMem_addr());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		String sql = "delete from mymember where mem_id = ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, memId);
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int updateMember(Connection conn, MemberVO memVo) throws SQLException {
		String sql = "update mymember set " + "   mem_pass = ?, mem_name = ?, mem_tel = ?, "
				+ "   mem_addr = ? where mem_id = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, memVo.getMem_pass());
		pstmt.setString(2, memVo.getMem_name());
		pstmt.setString(3, memVo.getMem_tel());
		pstmt.setString(4, memVo.getMem_addr());
		pstmt.setString(5, memVo.getMem_id());

		int cnt = pstmt.executeUpdate();

		if(pstmt != null) pstmt.close();
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember(Connection conn) throws SQLException {
		List<MemberVO> memList = null;
		String sql = "select * from mymember";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		memList = new ArrayList<MemberVO>();
		while(rs.next()) {
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(rs.getString("mem_id"));
			memVo.setMem_pass(rs.getString("mem_pass"));
			memVo.setMem_name(rs.getString("mem_name"));
			memVo.setMem_tel(rs.getString("mem_tel"));
			memVo.setMem_addr(rs.getString("mem_addr"));
			
			memList.add(memVo); // List에 MemberVO객체 추가
		}
		
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		
		return memList;
	}

	@Override
	public int getMemberCount(Connection conn, String memId) throws SQLException {
		String sql = "select count(*) cnt from mymember"
				+ "    where mem_id=? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		ResultSet rs = pstmt.executeQuery();
		int count =0;
		if(rs.next()) {
			count = rs.getInt("cnt");
		}

		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		
		return count;
	}

//	@Override
//	public int littleupdateMember(Connection conn, MemberVO memVo) throws SQLException {
//		String sql = "update mymember set " + memVo.getUpdateField() + " = ? where mem_id = ? ";
//		
//		PreparedStatement pstmt = conn.prepareStatement(sql);	
//		pstmt.setString(1, memVo.getUpdateData());
//		pstmt.setString(2, memVo.getMem_id());
//		
//		int cnt = pstmt.executeUpdate();
//
//		if(pstmt != null) pstmt.close();
//		
//		return cnt;
//	}

	@Override
	public int updateMember2(Connection conn, Map<String, String> paramMap) throws SQLException {
		String sql = "update mymember set " + paramMap.get("field") + " = ? where mem_id = ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, paramMap.get("data"));
		pstmt.setString(2, paramMap.get("memid"));

		int cnt = pstmt.executeUpdate();

		if (pstmt != null)
			pstmt.close();

		return cnt;
	}
}
