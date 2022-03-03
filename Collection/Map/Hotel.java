package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	private HashMap<Integer, Room> hotelMap;
	private Scanner scan;
	
	// 생성자 --> 방번호와 방종류를 초기화 작업 진행
	public Hotel() {
		hotelMap = new HashMap<>();
		scan = new Scanner(System.in);
		
		// 객실 초기화 작업
		for (int i = 2; i <= 4; i++) {
			String roomType = null;
			switch (i) {
			case 2:
				roomType = "싱글룸";
				break;
			case 3:
				roomType = "더블룸";
				break;
			case 4:
				roomType = "스위트룸";
				break;
			}
			
			for(int j = 1; j <= 9; j++) {
				int roomNum = i * 100 + j;
				Room r = new Room(roomNum, roomType);
				hotelMap.put(roomNum, r);
			}
		}
	}

	public static void main(String[] args) {
		new Hotel().hotelStart();
	}

	// 시작 메서드
	private void hotelStart() {
		System.out.println();
		System.out.println("*****************************************");
		System.out.println("      호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*****************************************");
		System.out.println();
		
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				displayRoomState();
				break;
			case 4:
				System.out.println("*****************************************");
				System.out.println("            호텔문을 닫았습니다.");
				System.out.println("*****************************************");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}

	// 체크인하는 메서드
	private void checkIn() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("                체크인 작업");
		System.out.println("------------------------------------------");
		System.out.println(" * 201 ~ 209 : 싱글룸");
		System.out.println(" * 301 ~ 309 : 더블룸");
		System.out.println(" * 401 ~ 409 : 스위트룸");
		System.out.println("------------------------------------------");
		System.out.print("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		// 입력한 값이 Map의 키값에 없으면 없는 방번호이다.
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		// 해당 객실에 다른 투숙객이 있는지 검사
		if(hotelMap.get(num).getGuestName() != null) {
			System.out.println(num + "호 객실에는 이미 손님이 있습니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		// 해당 방번호의 Room객체의 투숙객 저장변수에 입력받은 이름을 저장한다.
		hotelMap.get(num).setGuestName(name);
		System.out.println(name + "씨가 " + num + "호 객실에 체크인 하였습니다.");
	}
	
	private void checkOut() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("               체크아웃 작업");
		System.out.println("------------------------------------------");
		System.out.println("체크아웃 할 방번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int num = scan.nextInt();
		
		// 입력한 값이 Map의 키값에 없으면 없는 방번호이다.
		if (!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		// 해당 객실에 투숙객이 있는지 검사
		if (hotelMap.get(num).getGuestName() == null) {
			System.out.println(num + "호 객실에는 체크인한 손님이 없습니다.");
			return;
		}
		
		// 체크아웃 작업은 해당 객실의 투숙객 이름을 null로 변경하는 것이다.
		
		// 현재 투숙해 있는 사람의 이름을 구한다.
		String name = hotelMap.get(num).getGuestName();
		
		// 투숙객 이름을 null로 변경한다.
		hotelMap.get(num).setGuestName(null);
		
		System.out.println(num + "호 객실의 " + name + "님 체크아웃을 완료하였습니다.");

	}

	// 객실 상태를 출력하는 메서드
	private void displayRoomState() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("              현재 객실 상태");
		System.out.println("------------------------------------------");
		System.out.println("  방 번호        방 종류       투숙객 이름");
		System.out.println("------------------------------------------");
		
		// 전체 방번호를 가져와 처리한다.
		
		// 방번호를 순서대로 나오게하기 위해서 방번호(Map의 Key값)만 List에 넣은 후 정렬하여 사용한다.
		ArrayList<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());
		
		// 정렬하기
		Collections.sort(roomNumList);
			
		for(Integer roomNum : roomNumList) {
			Room r = hotelMap.get(roomNum);
			
			String name = "-";
			
			if(r.getGuestName() != null) {
				name = r.getGuestName();
			}
			
			System.out.print("   " + r.getRoomNum() + "\t        ");
			System.out.print(r.getRoomType() + "\t       ");
			System.out.println(name);
		}
		System.out.println("------------------------------------------");
		System.out.println();
	}
	
	// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인  2. 체크아웃  3. 객실상태  4. 업무종료");
		System.out.println("------------------------------------------");
		return scan.nextInt();
	}
}

class Room {
	private int roomNum;  		// 방번호
	private String roomType;    // 방종류
	private String guestName;	// 투숙객이름
	
	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
	}
	
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
}