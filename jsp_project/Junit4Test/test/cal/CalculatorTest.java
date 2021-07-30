package cal;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {

//	Calculator cal = new Calculator();
	Calculator cal;  	// 컨넥션 변수 선언
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("@Before");
		cal = new Calculator(); // before 에서 setup 해놓을것.
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After");
	}

	@Test
	public void testAdd() {
		System.out.println("testAdd()");

		//Calculator cal = new Calculator();
		
		assertEquals("add()메소드 테스트 ", 12, cal.add(10, 2));
		
		//System.out.println(cal.add(10, 2));
		// fail("Not yet implemented");
		
	}

	@Test
	public void testSubstract() {
		System.out.println("testSubstract()");
		
		assertEquals("substract() 메소드 테스트", 8, cal.substract(10, 2));

		
		//fail("Not yet implemented");
	}

	@Test
	public void testMultiply() {
		System.out.println("testMultiply()");
		
		assertEquals("multiply() 메소드 테스트", 20, cal.multiply(10, 2));

		// fail("Not yet implemented");
	}

	@Test
	public void testDivide() {
		System.out.println("testDivide()");
		
		assertEquals("divide() 메소드 테스트", 5, cal.divide(10, 2));

		//fail("Not yet implemented");
	}

	// 예외 타입을 비교해서 테스트
	@Test(expected =  SQLException.class)
	public void test1() throws SQLException {
		System.out.println("@Test 실행 중 예외타입을 테스트");
		throw new SQLException();
	}
	
	@Test(timeout = 1) 	//  100/ 1000
	public void test2() {
		System.out.println("@Test 테스트중 허용시간 ms 안에 실행이 완료되는지 테스트");
		cal.add(100, 200);
		cal.substract(10, 2);
	}
	
	@Test
	public void test3() {
		//fail("테스트 미실행 ");
		//assertTrue("메소드의 반환값이 boolean 인 경우 사용", false);, 
		//assertNull("메소드의 실행 값이 null이 예상되는 경우", new Object());
		//assertNotNull("메소드의 실행 값이 null이 아닌 값이 예상되는 경우", null);
		//assertSame("같은 값일 경우", 1, 1);
		assertNotSame("같은 값일 경우", 1, 1);

	}
	
	@Test
	public void testMain() {
		//fail("Not yet implemented");
	}

}
