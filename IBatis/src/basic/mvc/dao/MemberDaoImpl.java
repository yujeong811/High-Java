package kr.or.ddit.basic.mvc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	// 1번
	private static MemberDaoImpl dao;

	// 2번
	private MemberDaoImpl() {
	}

	// 3번
	public static MemberDaoImpl getInstance() {
		if (dao == null)
			dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public int insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("member.insertMember", memVo);
		if (obj == null)
			cnt = 1; // 성공: 1, 실패: null
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		return (int) smc.delete("member.deleteMember", memId);
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		return (int) smc.update("member.updateMember", memVo);
	}

	@Override
	public List<MemberVO> getAllMember(SqlMapClient smc) throws SQLException {
		return smc.queryForList("member.getAllMember"); // 레코드가 여러 개 : queryForList 사용
	}

	@Override
	public int getMemberCount(SqlMapClient smc, String memId) throws SQLException {
		return (int) smc.queryForObject("member.getMemberCount", memId); // 레코드가 하나 : queryForObject 사용
	}

	@Override
	public int updateMember2(SqlMapClient smc, Map<String, String> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return smc.update("member.updateMember2", paramMap);
	}

}