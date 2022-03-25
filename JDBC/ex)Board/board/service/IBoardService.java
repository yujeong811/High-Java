package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	public int insertBoard(BoardVO boardVo);

	public int deleteBoard(int boardNo);

	public int updateBoard(int boardNo, BoardVO boardVo);
	
	public int updateCnt(int boardNo);

	public List<BoardVO> getAllBoard();

	public List<BoardVO> getBoard(String boardTitle);
	
	public List<BoardVO> getSomeBoard(int boardNo);
}
