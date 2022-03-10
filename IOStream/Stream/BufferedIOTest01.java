package kr.or.ddit.basic.stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력 향상을 위하여 Buffered스트림을 사용한다.
		
		try {
			FileOutputStream fout = new FileOutputStream("D:/D_Other/bufferTest.txt");
			
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char i='1'; i <= '9'; i++) {
				bout.write(i);
			}
			bout.flush();
			
			// 버퍼스트림을 닫아주면 버퍼의 내용을 모두 flush한 후에 닫아준다.
			bout.close();
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			
		}

	}

}
