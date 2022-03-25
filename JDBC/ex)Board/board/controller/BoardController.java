package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class BoardController {
	private Scanner scan = new Scanner(System.in);
	private IBoardService bservice;
	
	public BoardController() {
		bservice = BoardServiceImpl.getInstance();
	}

	private void startBoard() {

		while (true) {
			int choice = displayBoard();
			
			switch (choice) {
			case 1:
				insertBoard();
				break;
			case 2:
				selectBoard();
				break;
			case 3:
				searchBoard();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("작업번호를 잘못 입력했습니다.");	
				System.out.println("다시 입력해주세요.");	
			}
		}
	}
	
	private void searchBoard() {
		System.out.println();
		System.out.println("<< 검색하기 >>");

		System.out.print("검색할 제목 입력 >> ");
		scan.nextLine();
		String title = scan.nextLine();
		
		List<BoardVO> boardList = bservice.getBoard(title);
		
		System.out.println("----------------------------------------");
		System.out.println("   No	    제 목        작성자 	  조회수 ");
		System.out.println("----------------------------------------");

		if(boardList == null || boardList.size() == 0) {
			System.out.println("제목이 " + title + "인 게시글은 존재하지 않습니다.");
			System.out.println("----------------------------------------");
		} else {
			for(BoardVO boardVo : boardList) {
				int boardNo = boardVo.getBoard_no();
				String boardTitle = boardVo.getBoard_title();
				String boardWriter = boardVo.getBoard_writer();
				int boardCnt = boardVo.getBoard_cnt();
				
				System.out.println("    " + boardNo + "\t    " + boardTitle + "\t " + boardWriter + "\t   " + boardCnt);
			}
			System.out.println("----------------------------------------");
		}
	}

	private void selectBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardno = scan.nextInt();
		
		int cnt = bservice.updateCnt(boardno);
		
		List<BoardVO> boardList = bservice.getSomeBoard(boardno);

		System.out.println("----------------------------------------");

		if (boardList == null || boardList.size() == 0) {
			System.out.println(boardno + "번 게시글이 존재하지 않습니다.");

		} else {
			for (BoardVO boardVo : boardList) {
				String boardTitle = boardVo.getBoard_title();
				String boardWriter = boardVo.getBoard_writer();
				String boardContent = boardVo.getBoard_content();
				String boardDate = boardVo.getBoard_date();
				int boardCnt = boardVo.getBoard_cnt();

				System.out.println("제 목 : " + boardTitle);
				System.out.println("작성자 : " + boardWriter);
				System.out.println("내 용 : " + boardContent);
				System.out.println("작성일 : " + boardDate);
				System.out.println("조회수 : " + boardCnt);
			}
		}
		
		System.out.println("----------------------------------------");
		System.out.print("1. 수정    2. 삭제    3. 리스트로 가기 >> ");
		int choice = scan.nextInt();
		
		switch (choice) {
		case 1:
			updateBoard(boardno);
			break;
		case 2:
			deleteBoard(boardno);
			break;
		case 3: 
			startBoard();
			break;
		}
	}

	private void deleteBoard(int boardno) {
		System.out.println();
		System.out.println("<< 삭제하기 >>");

		int cnt = bservice.deleteBoard(boardno);
		
		if (cnt > 0) {
			System.out.println(boardno + "번글이 삭제되었습니다.");
		} else {
			System.out.println(boardno + "번글이 삭제되지 않았습니다.");
		}
		
	}

	private void updateBoard(int boardno) {
		System.out.println();
		System.out.println("<< 수정하기 >>");
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("제목 >> ");
		String title = scan.nextLine();
		
		System.out.print("내용 >> ");
		String content = scan.nextLine();
		
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = bservice.updateBoard(boardno, boardVo);
		
		if (cnt > 0) {
			System.out.println(boardno + "번 글이 수정되었습니다.");
		} else {
			System.out.println(boardno + "번 글이 수정되지 않았습니다.");
		}
	}

	private void insertBoard() {
		System.out.println();
		System.out.println("<< 새글 작성하기 >>");
		
		scan.nextLine();
		System.out.print("제목 >> ");
		String title = scan.nextLine();

		System.out.print("작성자 >> ");
		String writer = scan.next();

		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("내용 >> ");
		String content = scan.nextLine();

		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = bservice.insertBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("새글이 추가되었습니다.");
		} else {
			System.out.println("새글이 추가되지 않았습니다.");
		}
	}

	private int displayBoard() {
		List<BoardVO> boardList = bservice.getAllBoard();
		
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("   No	    제 목        작성자 	  조회수 ");
		System.out.println("----------------------------------------");
		
		if(boardList == null || boardList.size() == 0) {
			System.out.println("출력할 자료가 하나도 없습니다.");
			System.out.println("----------------------------------------");
			System.out.print("1. 새글작성     2. 게시글보기    3. 검색    0. 작업 끝 >> ");
		} else {
			for(BoardVO boardVo : boardList) {
				int boardNo = boardVo.getBoard_no();
				String boardTitle = boardVo.getBoard_title();
				String boardWriter = boardVo.getBoard_writer();
				int boardCnt = boardVo.getBoard_cnt();
				
				System.out.println("    " + boardNo + "\t    " + boardTitle + "\t" + boardWriter + "\t    " + boardCnt);
			}
			System.out.println("----------------------------------------");
			System.out.print("1. 새글작성     2. 게시글보기    3. 검색    0. 작업 끝 >> ");
		}
		
		return scan.nextInt();
	}
	
	public static void main(String[] args) {
		new BoardController().startBoard();
	}

}
