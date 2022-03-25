package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao;
	private static MemberServiceImpl memSer;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(memSer == null) memSer = new MemberServiceImpl();
		
		return memSer;
	}

	@Override
	public Object insertMember(MemberVO memVo) {
		SqlMapClient smc = null;
		Object obj = null;
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			obj = smc.insert("member.insertMember", memVo);	
			
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		} 
		
		return obj;
	}

	@Override
	public int deleteMember(String memId) {
		SqlMapClient smc = null;
		int cnt = 0;
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = smc.delete("member.deleteMember", memId);	
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
	
	}

	@Override
	public List<MemberVO> getAllMember() {
	
	}

	@Override
	public int getMemberCount(String memId) {
		
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		
	}

}
