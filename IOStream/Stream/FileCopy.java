package kr.or.ddit.basic.stream;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * 문제) D드라이브의 d_other폴더에 있는 '펭귄.jpg'파일을 D드라이브의 d_other폴더에 있는 '연습용'폴더에
 *      '펭귄_복사본.jpg'파일로 복사하는 프로그램을 작성하시오.
 */

public class FileCopy {

	public static void main(String[] args) {
		try {
			File file = new File("D:/D_Other/펭귄.jpg");
			FileInputStream in = new FileInputStream("D:/D_Other/펭귄.jpg");
			
			byte[] buffer = new byte[(int)file.length()];
			
			int pos = 0;
			int size = 800;
			int temp = 0;
			int readByte = 0;
			
			while((readByte = in.read(buffer,pos,size)) > 0) {
				pos += readByte;
				temp = size - pos;
				if(temp < 800)
					size = temp;
			}
			in.close();
			
			FileOutputStream out = new FileOutputStream("D:/D_Other/연습용/펭귄_복사본1.jpg", false);
			out.write(buffer, 0, buffer.length);
			out.flush();
			out.close();
			
			OutputStream out2 = new BufferedOutputStream(new FileOutputStream("D:/D_Other/연습용/펭귄_복사본2.jpg"));
			out2.write(buffer);
			out2.close();
			
			File jpeg = new File("D:/D_Other/펭귄.jpg");
			
			byte[] bytes = new byte[(int)file.length()];
			DataInputStream in1 = new DataInputStream(new FileInputStream(jpeg));
			
			in1.readFully(bytes);
			in1.close();
			
			FileOutputStream out1 = new FileOutputStream("D:/D_Other/연습용/펭귄_복사본.jpg");
			out1.write(bytes);
			out1.close();
			
			Path filePath = Paths.get("D:/D_Other/연습용/펭귄_복사본1.jpg");
			Path filePath2 = Paths.get("D:/D_Other/연습용/펭귄_복사본2.jpg");
			
			Files.delete(filePath);
			Files.delete(filePath2);
			
		} catch (IOException e) {
			
		}
		
	}

}
