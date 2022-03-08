package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("D:/D_Other/test.TXT");

		System.out.println(f1.getName() + "의 크기 : " + f1.length() + "bytes");
		
		System.out.println("path : " + f1.getPath()); // 객체 생성할 때 지정한 값이 나옴
		System.out.println("absolutePath : " + f1.getAbsolutePath()); // 절대경로
		
		File f2 = new File("."); // 현재 폴더
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
	}

}
