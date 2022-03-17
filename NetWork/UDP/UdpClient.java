package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// 송신용, 수신용 패킷객체 변수 선언
		DatagramPacket inpacket, outpacket;
		
		// 수신받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			// 접속할 곳의 주소 생성한다.
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			while(true) {
				// 전송할 메시지 입력
				System.out.print("보낼 메시지 입력 : ");
				String msg = scan.nextLine();
				
				byte[] sendMsg = msg.getBytes("utf-8");
				
				// 전송할 패킷객체 생성
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, 8888);
				
				// 전송하기
				socket.send(outpacket);
				
				// 메시지 중지 여부 검사
				if("/end".equals(msg)) {
					System.out.println("작업을 마칩니다...");
					break;
				}
				
				// 서버에서 보내온 데이터를 받아서 화면에 출력하기
				
				// 수신용 패킷객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 수신
				socket.receive(inpacket);
				
				System.out.println("서버의 응답 데이터 : " + new String(bMsg, 0, inpacket.getLength(), "utf-8"));
				System.out.println();
			}
			socket.close();
			
		} catch (Exception e) {
			
		}
	}

}
