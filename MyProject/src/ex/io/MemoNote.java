package ex.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MemoNote {
	
	Scanner scanner;
	File saveDir;
	File[] newList;
	
	//메소드 종류(메모 리스트, 메모쓰기, 메모 보기)
	public MemoNote() {
			scanner = new Scanner(System.in);
			saveDir = new File("C:\\javamemo");
	}
	//메모리스트 메소드
	public void viewMemoList() {
		if(!saveDir.exists()) {
			System.out.println("저장 폴더가 존재하지 않아 폴더를 생성합니다.");
			saveDir.mkdir();
		} else {
			newList = saveDir.listFiles();
			
			System.out.println("메모 리스트 ====================");
			if(newList.length > 0) {
				int cnt = 0;
				for(File file : newList) {
					if(file.isDirectory()) {
						//System.out.print("[DIR]");
					} else if(file.isFile()) {
						System.out.print("[FILE]");
					}
					System.out.println("[" + ++cnt + "]" + file.getName());
				}
			} else {
				System.out.println("저장 폴더에 파일이 존재하지 않습니다.");
			}
			System.out.println("================================");
		}
	}
	//메모 등록 메소드
	public void writeMemo() {
		
		BufferedWriter out = null;
		
		try {
		//제목 입력
		System.out.print("제목을 입력해주세요.");
		String subject = scanner.nextLine();
		
		System.out.println();
		
		//메모 내용 입력
		System.out.print("메모내용을 입력해주세요.");
		String memo = scanner.nextLine();
		
		//메모쓴 날짜
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-dd");
		
		String writeDate = format.format(now);
		System.out.println(writeDate);
		
		String newFileName = writeDate + "_" + subject;
		out = new BufferedWriter(new FileWriter(new File(saveDir, newFileName + ".txt")));
		
		System.out.println(subject);
		System.out.println(memo);
		out.write(subject);
		out.newLine();
		out.newLine();
		out.write(writeDate);
		out.newLine();
		out.newLine();
		out.write(memo);
		out.close();
		
		System.out.println("Memo 입력 완료.");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//메모보기 메소드
	public void viewMemo() {
		
		viewMemoList();
		
		System.out.println("파일의 번호를 입력해주세요.");
		
		int select = Integer.parseInt(scanner.nextLine());
		
		String fileName = newList[select].getName();
		
		File viewFile = new File(saveDir, fileName);
		
		BufferedReader in = null;
		String str = null;
		
		System.out.println("파일 읽기 =======================");
		try {
				in = new BufferedReader(new FileReader(viewFile));
				while(true) {
					str = in.readLine();
					if(str == null)
						break;
					System.out.println(str);
				}
				System.out.println("===================");
				in.close();
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		MemoNote memoNote = new MemoNote();
		
		while(true) {
			System.out.println("JAVA Memo ======================");
			System.out.println(" [1] 메모 목록 보기 [2]메모 등록 [3]메모 보기 [4]종료");
			System.out.println("===================================");
			System.out.println("번호를 입력하시오.");
			
			int number = Integer.parseInt(memoNote.scanner.nextLine());
			
			switch(number) {
			case 1:
				memoNote.viewMemoList();
				break;
			//메모 등록메소드
			case 2:
				
				break;
			//메모 보기메소드
			case 3:
				
				break;
			//종료
			case 4:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}

	}

}
