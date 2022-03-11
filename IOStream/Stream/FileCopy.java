package kr.or.ddit.basic.stream;

import java.awt.Panel;
import java.io.BufferedInputStream;
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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * 문제) D드라이브의 d_other폴더에 있는 '펭귄.jpg'파일을 D드라이브의 d_other폴더에 있는 '연습용'폴더에
 *      '펭귄_복사본.jpg'파일로 복사하는 프로그램을 작성하시오.
 */

public class FileCopy {

	public static void main(String[] args) {
		FileCopy fc = new FileCopy();

//		File file = new File("D:/D_Other/펭귄.jpg");
		File file = fc.getSelectFile("OPEN");
		
		if(file==null) {
			System.out.println("선택한 원본 파일이 없습니다.");
			System.out.println("복사작업 끝...");
			return;
		}
		
		File targetFile = fc.getSelectFile("SAVE");

		if(targetFile==null) {
			System.out.println("선택한 대상 파일이 없습니다.");
			System.out.println("복사작업 끝...");
			return;
		}
		
//		if (!file.exists()) {
//			System.out.println(file.getName() + "파일이 없습니다.");
//			System.out.println("복사작업을 중단합니다.");
//			return;
//		}

		try {
			// 복사본 파일 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(fin);

			// 복사될 파일 스트림 객체 생성
//			FileOutputStream fout = new FileOutputStream("D:/D_Other/연습용/펭귄_복사본.jpg");
			FileOutputStream fout = new FileOutputStream(targetFile);
			BufferedOutputStream bout = new BufferedOutputStream(fout);

			System.out.println("복사 시작...");

			int data;

//			while((data=fin.read()) != -1) {
//				fout.write(data);
//			}
//			fout.flush(); // 버퍼의 모든 내용을 출력소스에 출력 후 버퍼를 비운다.
//			
//			System.out.println("복사 작업 끝...");
//			
//			// 스트림 닫기
//			fin.close();
//			fout.close();

			while ((data = bin.read()) != -1) {
				bout.write(data);
			}
			bout.flush();

			bin.close();
			bout.close();

			System.out.println("복사 끝...");
			
		} catch (IOException e) {

		}

	}

	// 파일을 선택하여 선택한 파일을 반환하는 메서드
	public File getSelectFile(String option) {
		// SWING의 파일 '열기창', '저장창' 연습
		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 확장자 설정 (확장자 객체 생성)
//		FileNameExtensionFilter doc = new FileNameExtensionFilter("Ms Word", "docx", "doc");
//		FileNameExtensionFilter img = new FileNameExtensionFilter("Images", new String[] { "png", "jpg", "gif" });
//		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일", "txt");
//
//		// 구성한 확장자들을 추가한다.
//		chooser.addChoosableFileFilter(doc);
//		chooser.addChoosableFileFilter(img);
//		chooser.addChoosableFileFilter(txt);

		// 확장자 목록 중에 처음부터 선택될 확장자 지정
//		chooser.setFileFilter(txt);

		// 확장자 몰록 중에 '모든 파일' 목록의 표시 여부 설정하기
		// true : 모든 파일 목록 보이기, false : 해제
//				chooser.setAcceptAllFileFilterUsed(false);
		chooser.setAcceptAllFileFilterUsed(true);

		// Dialog 창이 나타낼 기본 경로 설정
		// 예1) 'D:/D_Other' 폴더로 설정하기
//		chooser.setCurrentDirectory(new File("D:/D_Other"));

		// 예2) '바탕화면'으로 설정하기
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/desktop"));
		
		int result;
		
		if ("OPEN".equals(option)) {
			result = chooser.showOpenDialog(new Panel());
		} else if ("SAVE".equals(option)) {
			result = chooser.showSaveDialog(new Panel());
		} else {
			System.out.println("option이 잘못되었습니다.");
			return null;
		}
		
		File selectedFile = null;
		
		// 창에서 '저장' 또는 '열기' 버튼을 클릭했을 때 처리
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
		}
		return selectedFile;
	}

}
