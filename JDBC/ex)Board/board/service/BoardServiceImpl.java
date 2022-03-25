package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardServiceImpl implements IBoardService {

	private IBoardDao boardDao;
	private static BoardServiceImpl boardSer;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(boardSer == null) boardSer = new BoardServiceImpl();
		
		return boardSer;
	}
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		int cnt = 0; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			cnt = boardDao.insertBoard(conn, boardVo);		
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		int cnt = 0; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			cnt = boardDao.deleteBoard(conn, boardNo);		
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateBoard(int boardNo, BoardVO boardVo) {
		Connection conn = null;
		int cnt = 0; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			cnt = boardDao.updateBoard(conn, boardNo, boardVo);		
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		Connection conn = null;
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			boardList = boardDao.getAllBoard(conn);
			
		} catch (SQLException e) {
			boardList = null;
			
		} finally {
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> getBoard(String boardTitle) {
		Connection conn = null;
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			boardList = boardDao.getBoard(conn, boardTitle);
			
		} catch (SQLException e) {
			boardList = null;
			
		} finally {
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> getSomeBoard(int boardNo) {
		Connection conn = null;
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			boardList = boardDao.getSomeBoard(conn, boardNo);
			
		} catch (SQLException e) {
			boardList = null;
			
		} finally {
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public int updateCnt(int boardNo) {
		Connection conn = null;
		int cnt = 0; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			cnt = boardDao.updateCnt(conn, boardNo);		
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
		return cnt;
	}

}
