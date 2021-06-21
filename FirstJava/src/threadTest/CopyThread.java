package threadTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyThread extends Thread {
	//복사하고자 하는 파일, 복사할 파일의 디렉토리 주소
	File originFile;
	File copyDir;
	
	public CopyThread(File originFile, File copyDir) {
		this.originFile = originFile;
		this.copyDir = copyDir;
	}

	@Override
	public void run() {
		
		
		try {
			InputStream in = new FileInputStream(originFile);
			
			OutputStream out = new FileOutputStream(copyDir);
		
		
			//필터 스트림 생성 -> 기본스트림 필요
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
			System.out.println("복사된 바이트 크기" + copyByte);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
