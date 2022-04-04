package kr.or.ddit.basic.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.basic.mvc.service.IMemberService;
import kr.or.ddit.basic.mvc.service.MemberServiceImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	private IMemberService service;
	
	// 생성자 (singleton 사용)
	public MemberController() {
//		service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}
	
	// 시작 메서드
	public void startMember() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 추가
				insertMember();
				break;
			case 2: // 수정
				updateMember();
				break;
			case 3: // 삭제
				deleteMember();
				break;
			case 4: // 전체 출력
				displayMember();
				break;
			case 5 : // 자료 부분 수정
				updateMember2();
				break;	
			case 0: // 작업 끝
				System.out.println("작업을 마칩니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 선택해서 수정하기
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.println("회원ID >> ");
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
			System.out.println(" 1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
			System.out.println("-------------------------------------------");
			System.out.print("수정항목 선택 >> ");
			num = scan.nextInt();

			switch (num) {
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
			default:
				System.out.println("수정 항목을 잘못 선택하셨습니다.");
				System.out.println("다시 선택하세요.");
			}

		} while (num < 1 || num > 5); // 1~4사이

		System.out.println();
		scan.nextLine(); // 버퍼 비우기
		System.out.println("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();
		
		// 수정 작업에 필요한 정보를 Map객체에 셋팅한다.
		// key값 정보 ==> 회원ID(memid), 수정할컬럼명(field), 수정할데이터(data)
		Map<String, String> paramMap = new HashMap<String, String>();
		
		paramMap.put("memid", memId); // 회원ID
		paramMap.put("field", updateField); // 수정할 컬럼명
		paramMap.put("data", updateData); // 수정할 데이터
		
		int cnt = service.updateMember2(paramMap);
		
		if (cnt > 0) {
			System.out.println("회원정보 수정 성공!!");
		} else {
			System.out.println("회원정보 수정 실패~~");
		}

	}

	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 선택해서 수정하기
//		private void updateMember2() {
//			System.out.println();
//			System.out.println("수정할 회원 정보를 입력하세요.");
//			System.out.println("회원ID >> ");
//			String memId = scan.next();
//			
//			int count = service.getMemberCount(memId);
//			if(count == 0) { // 없는 회원이면
//				System.out.println(memId + "은(는) 없는 회원ID입니다.");
//				System.out.println("수정 작업을 마칩니다.");
//				return;
//			}
//			
//			int num;
//			String updateField = null;
//			String updateTitle = null;
//			do {
//				System.out.println();
//				System.out.println("수정할 항목을 선택하세요.");
//				System.out.println(" 1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
//				System.out.println("-------------------------------------------");
//				System.out.print("수정항목 선택 >> ");
//				num = scan.nextInt();
//				
//				switch(num) {
//				case 1: updateField = "mem_pass";
//						updateTitle = "비밀번호";
//						break;
//				case 2: updateField = "mem_name";
//						updateTitle = "회원이름";
//							break;
//				case 3: updateField = "mem_tel";
//						updateTitle = "전화번호";
//							break;
//				case 4: updateField = "mem_addr";
//						updateTitle = "회원주소";
//							break;
//				default: System.out.println("수정 항목을 잘못 선택하셨습니다.");
//						System.out.println("다시 선택하세요.");
//				}
//				
//			}while(num < 1 || num > 5); // 1~4사이
//			
//			System.out.println();
//			scan.nextLine(); // 버퍼 비우기
//			System.out.println("새로운 " + updateTitle + " >> ");
//			String updateData = scan.nextLine();
//
//			int cnt = service.updateMember2(memId, updateField, updateData);
//
//			if (cnt > 0) {
//				System.out.println("회원정보 수정 성공!!");
//			} else {
//				System.out.println("회원정보 수정 실패~~");
//			}
//			
//		}
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() {
		
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println(" ID\t비밀번호\t이름\t전화번호\t주소");
		System.out.println("-------------------------------------------");
		
		if(memList == null || memList.size() == 0) { // 데이터가 없을 경우
			System.out.println("출력할 자료가 하나도 없습니다.");
		}else {
			for(MemberVO memVo : memList) {
				String memId = memVo.getMem_id();
				String memPass = memVo.getMem_pass();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
				
				System.out.println(memId + "\t" + memPass 
						+ "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
		}
		System.out.println("-------------------------------------------");
		System.out.println("출력 끝...");
	}
	
	// 회원 정보를 수정하는 메서드 ==> 전체 항목 수정하기
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.println("회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		if(count == 0) { // 없는 회원이면
			System.out.println(memId + "은(는) 없는 회원ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		System.out.println();
		System.out.println("수정할 내용을 입력하세요.");
		
		System.out.println("새로운 비밀번호 >> ");
		String newMemPass = scan.next();
		
		System.out.println("새로운 회원이름 >> ");
		String newMemName = scan.next();
		
		System.out.println("새로운 전화번호 >> ");
		String newMemTel = scan.next();
		
		scan.nextLine();
		System.out.println("새로운 회원주소 >> ");
		String newMemAddr = scan.nextLine();
		
		// 입력한 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_pass(newMemPass);
		memVo.setMem_name(newMemName);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);
		memVo.setMem_id(memId);

		int cnt = service.updateMember(memVo);

		if (cnt > 0) {
			System.out.println("회원정보 수정 성공!!");
		} else {
			System.out.println("회원정보 수정 실패~~");
		}

	}
	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.println("삭제할 회원ID >> ");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		
		if(cnt > 0) {
			System.out.println("회원정보 삭제 성공!!");
		}else {
			System.out.println("회원정보 삭제 실패~~");
		}
		
	}

	// 회원 정보를 추가(입력)하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");

		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
		int count = 0; // 입력한 회원ID의 개수가 저장될 변수

		String memId; // 회원ID가 저장될 변수
		do {
			System.out.print("회원ID >> ");
			memId = scan.next();

			count = service.getMemberCount(memId);

			if (count > 0) {
				System.out.println(memId + "는 이미 등록된 ID입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}

		} while (count > 0);

		System.out.print("비밀번호 >> ");
		String memPass = scan.next(); // next() : 띄어쓰기를 하지 않는 데이터

		System.out.print("회원이름 >> ");
		String memName = scan.next();

		System.out.print("전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine(); // 입력버퍼 비우기 (nextLine() 전에 오는 next()값 지우기)
		System.out.print("주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력한 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.insertMember(memVo);
		
		if(cnt > 0) {
			System.out.println("회원정보 추가 성공!!");
		}else {
			System.out.println("회원정보 추가 실패~~");
		}
		
	}

	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("---------------------------");
		System.out.println(" == 작 업 선 택 ==");
		System.out.println("1. 자 료 추 가");
		System.out.println("2. 자 료 수 정");
		System.out.println("3. 자 료 삭 제");
		System.out.println("4. 전 체 자 료 출 력");
		System.out.println("5. 자 료 부 분 수 정");
		System.out.println("0. 작 업 끝");
		System.out.println("---------------------------");
		System.out.println("원하는 작업번호 >> ");
		return scan.nextInt();

	}

	public static void main(String[] args) {
		new MemberController().startMember();
	}

}
