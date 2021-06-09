package threadTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CopyThread extends Thread {
	//복사하고자 하는 파일, 복사할 파일의 디렉토리 주소
	File originFile;
	File newDir;
	public CopyThread(File originFile, File newDir) {
		this.originFile = originFile;
		this.newDir = newDir;
	}
	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(originFile);
			FileOutputStream out = new FileOutputStream(newDir);
			
			
			//카피한 데이터의 크기
			int copyByte = 0;
			//파일에서 읽어올 바이트 데이터 배열
			byte[] buf = new byte[];
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
