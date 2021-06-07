package ex.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FootballPlayerManager {

	List<FootballPlayer> players;
	File file;
	Scanner scanner;
	
	public FootballPlayerManager() {
		scanner = new Scanner(System.in);
		
		file = new File("players.ser");
		if(file.exists()) {
			loadFile();
		} else {
			players = new ArrayList<FootballPlayer>();
		}
	
	}
	
	public void displayPlayers() {
		if(players.isEmpty()) {
			System.out.println("저장된 선수 데이터가 없습니다.");
		} else {
			for(FootballPlayer footballPlayer : players) {
				System.out.println(footballPlayer);
			}
		}
	}
	public void addPlayer() {
		System.out.println("이름을 입력해주세요.");
		String name = scanner.nextLine();
		System.out.println("등번호를 입력해주세요.");
		String number = scanner.nextLine();
		System.out.println("팀이름을 입력해주세요.");
		String team = scanner.nextLine();
		System.out.println("나이를 입력해주세요.");
		String age = scanner.nextLine();
		
		players.add(new FootballPlayer(name, Integer.parseInt(number), team, Integer.parseInt(age)));
	}
	public void saveFile() {
		ObjectOutputStream outputStream = null;
		FileOutputStream fos = null;

		try {
		
			fos = new FileOutputStream(file);
			outputStream = new ObjectOutputStream(fos);
			outputStream.writeObject(players);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings("unchecked")
	public void loadFile() {
		
			if(file.exists()) {
				ObjectInputStream inputStream = null;
				FileInputStream fis;
				
				try {
					System.out.println("파일로부터 데이터를 읽어 옵니다.");
					
					fis = new FileInputStream(file);
					inputStream = new ObjectInputStream(fis);
					players = (List<FootballPlayer>) inputStream.readObject();
					
					displayPlayers();
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
	}
		
	
	public static void main(String[] args) {
		
		FootballPlayerManager manager = new FootballPlayerManager();
		
		while(true) {
			System.out.println("\n");
			System.out.println("JAVA Memo=============================");
			System.out.println("[1] 선수 리스트 보기 [2] 선수등록 [3] 파일저장 [4] 종료");
			System.out.println("===========================");
			System.out.println("원하시는 기능의 번호를 입력하세요.");
			
			int select = Integer.parseInt(manager.scanner.nextLine());
			
			switch(select) {
			case 1:
				manager.displayPlayers();
				break;
			case 2:
				manager.addPlayer();
				break;
			case 3:
				manager.saveFile();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
			
		}
		

	}

}
