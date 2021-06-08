package ch12;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//①복사할 대상 파일의 경로와 복사해올 위치 경로를 받는 메인 스레드는 멈춤 없이 실행하고 
//데이터의 복사는 스레드로 처리하는 프로그램을 만들어 봅시다.
public class CopyThread extends Thread{
	
	//복사할 파일, 복사해올 위치경로
	File originFile;
	File copyDir;
	
	public CopyThread(File originFIle, File copyDir) {
		this.originFile = originFIle;
		this.copyDir = copyDir;
	}

	@Override
	public void run() {
			
			
			try {
				//파일 데이터를 읽어올 스트림 생성: FileInputStream
				InputStream in = new FileInputStream(originFile);
				//파일 쓰기위한 스트림 생성: FileOutputStream
				OutputStream out = new FileOutputStream(copyDir);
				
				//필터스트림 생성 -> 기본스트림 필요
				BufferedInputStream fin = new BufferedInputStream(in);
				BufferedOutputStream fout = new BufferedOutputStream(out);
				
				int copyByte = 0;
				int bData;
				
				while(true) {
					bData = fin.read();
					if(bData == -1) {
						break;
					}
					fout.write(bData);
					copyByte++;
					
				}
				fin.close();
				fout.close();
				System.out.println("복사된 바이트 크기 " + copyByte);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
}
