package deptTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.Connection;

import dept.dao.DeptDao;
import dept.domain.Dept;
import jdbc.util.ConnectionProvider;




public class DeptDaoTest {

	DeptDao dao = null;
	Connection conn = null;
	Dept insertDept = new Dept(15, "MARKETING", "SEOUL");
	Dept deleteDept = new Dept(10, "ACCOUNTING", "NEWYORK");

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		System.out.println("@BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		conn =(Connection) ConnectionProvider.getConnection();
		dao = DeptDao.getInstance();
 	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After");
	}

	

	@Test
	public void testGetDeptList() {

		assertNotNull("getDeptList(): 메소드 테스트 :" ,dao.getDeptList(conn));
		
		
		
		// fail("Not yet implemented");
	}

	@Test
	public void testInsertDept() {
		
		assertEquals("insertDept() test:", 0, dao.insertDept(conn, insertDept));
		assertEquals("insertDept() test:", 0, dao.insertDept(conn, deleteDept));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testDeleteDept() {
		
		assertEquals("deleteDept() test:" , 0, dao.deleteDept(conn, deleteDept.getDeptno()));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testSelectByDeptno() {
		
		assertSame("selectByDeptno() test: ", insertDept.getDeptno(),(dao.selectByDeptno(conn, insertDept.getDeptno()).getDeptno()));
		
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateDept() {
		
		insertDept = new Dept(22, "TECH", "california");
		assertEquals("updateDept() test:", 0, dao.updateDept(conn, insertDept));
		
		//fail("Not yet implemented");
	}

}
