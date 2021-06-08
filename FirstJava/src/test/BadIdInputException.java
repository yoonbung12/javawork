package test;

public class BadIdInputException extends Exception {
	
	
	public BadIdInputException(String message) {
		super(message);
	}

}
// 1.콘솔에서 사용자 아이디를 입력 받아 정상적인 영문자와 숫자로만 이루어진 값을 입력했는지 확인하는 프로그램을 만들어봅시다.
//
// ①사용자 예외 클래스를 정의해서 예외를 발생 시켜 봅시다.
//
// ②예외 클래스 이름은 BadIdInputException이라고 정의합시다