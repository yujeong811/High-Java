package kr.or.ddit.basic.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.basic.mvc.service.IMemberService;
import kr.or.ddit.basic.mvc.service.MemberServiceImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	private IMemberService service;
	
	public MemberController() {
		service = new MemberServiceImpl();
	}

	private void startMember() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1:
				insertMember();
				break;
			case 2:
				updateMember();
				break;
			case 3:
				deleteMember();
				break;
			case 4:
				displayMember();
				break;
			case 5:
				updateMember2();
				break;
			case 0: // 작업 끝
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}

	private int displayMenu() {
		System.out.println();
		System.out.println("*****************************");
		System.out.println("       회원 관리 프로그램");
		System.out.println("-----------------------------");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 수정");
		System.out.println("3. 자료 삭제");
		System.out.println("4. 자료 전체 출력");
		System.out.println("5. 자료 선택 수정");
		System.out.println("0. 작업 끝");
		System.out.println("*****************************");
		System.out.print("번호 입력 >> ");

		return scan.nextInt();
	}

	// 회원 정보를 추가(입력)하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원정보를 입력하세요.");

		String memId; // 회원ID가 저장될 변수
		int count = 0; // 입력한 회원ID의 개수가 저장될 변수

		do {
			System.out.print("회원ID >> ");
			memId = scan.next();

			count = service.getMemberCount(memId);

			if (count > 0) {
				System.out.println("입력한 아이디 " + memId + "는 이미 등록된 아이디입니다.");
				System.out.println("다시 입력해주세요.");
			}

		} while (count > 0);

		System.out.print("이름 >> ");
		String name = scan.next();

		System.out.print("비밀번호 >> ");
		String pass = scan.next();

		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("주소 >> ");
		String add = scan.nextLine();

		// 입력한 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(add);
		
		int cnt = service.insertMember(memVo);
		
		if(cnt > 0) {
			System.out.println("회원정보 추가 성공!!!");
		} else {
			System.out.println("회원정보 추가 실패~~~");
		}
	}

	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();

		int cnt = service.deleteMember(memId);
		
		if (cnt > 0) {
			System.out.println(memId + "회원 삭제 성공~~~");
		} else {
			System.out.println(memId + "없는 회원이거나 삭제에 실패했습니다.");
		}
	}
	
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();

		int count = service.getMemberCount(memId);
		if (count == 0) { // 없는 회원이면
			System.out.println(memId + "은(는) 없는 회원ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}

		System.out.println();
		System.out.println("수정할 내용을 입력하세요.");

		System.out.print("새로운 비밀번호 >> ");
		String newMemPass = scan.next();

		System.out.print("새로운 회원이름 >> ");
		String newMemName = scan.next();

		System.out.print("새로운 전화번호 >> ");
		String newMemTel = scan.next();

		scan.nextLine();
		System.out.print("새로운 회원주소 >> ");
		String newMemAddr = scan.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(newMemPass);
		memVo.setMem_name(newMemName);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);
		
		int cnt = service.updateMember(memVo);
		
		if (cnt > 0) {
			System.out.println(memId + "회원 정보 수정 완료~~~");
		} else {
			System.out.println(memId + "회원 정보 수정 실패!!!");
		}
	}
	
	private void displayMember() {
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("  ID   비밀번호   이름    전화번호    주소");
		System.out.println("----------------------------------------");
		
		if(memList == null || memList.size() == 0) {
			System.out.println("출력할 자료가 하나도 없습니다.");
		} else {
			for(MemberVO memVo : memList) {
				String memId = memVo.getMem_id();
				String memPass = memVo.getMem_pass();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
				
				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("----------------------------------------");
			System.out.println("출력 끝...");
		}
	}
	
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();

		int count = service.getMemberCount(memId);
		if (count == 0) { // 없는 회원이면
			System.out.println(memId + "은(는) 없는 회원ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		int num;
		String updateField = null;
		String updateTitle = null;
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.print("1. 비밀번호  2. 회원이름  3.전화번호  4.회원주소 >> ");
			num = scan.nextInt();
			
			switch(num) {
			case 1:
				updateField = "mem_pass";
				updateTitle = "비밀번호";
				break;
			case 2:
				updateField = "mem_name";
				updateTitle = "회원이름";
				break;
			case 3:
				updateField = "mem_tel";
				updateTitle = "전화번호";
				break;
			case 4:
				updateField = "mem_addr";
				updateTitle = "회원주소";
				break;
			default :
				System.out.println("수정 항목을 잘못 선택했습니다.");
				System.out.println("다시 선택하세요.");
			}
		} while(num < 1 || num > 4);
		
		System.out.println();
		scan.nextLine(); // 버퍼비우기
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();
	
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setUpdateField(updateField);
		memVo.setUpdateData(updateData);
		
		int cnt = service.littleupdateMember(memVo);
		
		if (cnt > 0) {
			System.out.println(memId + " 회원 정보 수정 완료~~~");
		} else {
			System.out.println(memId + " 회원 정보 수정 실패!!!");
		}
	}
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}

}
