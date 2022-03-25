package kr.or.ddit.basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.basic.singleton.MySingleton;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {
	
	// 1번
	private static MemberDaoImpl memDao;
	
	// 2번
	private MemberDaoImpl() {
		
	}

	// 3번
	public static MemberDaoImpl getInstance() {
		if(memDao == null) memDao = new MemberDaoImpl();
		
		return memDao;
	}

	@Override
	public Object insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		smc = SqlMapClientFactory.getSqlMapClient();
		
		memVo.setMem_id(memVo.getMem_id());
		memVo.setMem_pass(memVo.getMem_pass());
		memVo.setMem_name(memVo.getMem_name());
		memVo.setMem_tel(memVo.getMem_tel());
		memVo.setMem_addr(memVo.getMem_addr());
		
		
		
		
		return obj;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> getAllMember(SqlMapClient smc) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMemberCount(SqlMapClient smc, String memId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember2(SqlMapClient smc, Map<String, String> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
