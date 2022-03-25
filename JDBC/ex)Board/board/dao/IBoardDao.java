package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	public int insertBoard(Connection conn, BoardVO boardVo) throws SQLException;
	
	public int deleteBoard(Connection conn, int boardNo) throws SQLException;
	
	public int updateBoard(Connection conn, int boardNo, BoardVO boardVo) throws SQLException;
	
	public int updateCnt(Connection conn, int boardNo) throws SQLException;
	
	public List<BoardVO> getAllBoard(Connection conn) throws SQLException;
	
	public List<BoardVO> getBoard(Connection conn, String boardTitle) throws SQLException;
	
	public List<BoardVO> getSomeBoard(Connection conn, int boardNo) throws SQLException;
	
}
