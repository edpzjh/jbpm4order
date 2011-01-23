package com.bulain.jbpm4order.service;

import java.util.Arrays;
import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.pojo.LoginSearch;

public class LoginServiceImplTest extends ServiceTestCase {
	private LoginService loginService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_logins.xml");
		loginService = (LoginService) applicationContext.getBean("loginService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(LoginServiceImplTest.class);
	}


	public void testCountLoginByGroupId() {
		Long countLoginByGroupId = loginService.countLoginByGroupId(Integer.valueOf(105));
		assertEquals(Long.valueOf(3), countLoginByGroupId);
	}

	public void testFindLoginByGroupId() {
		List<Login> findLoginByGroupId = loginService.findLoginByGroupId(Integer.valueOf(105));
		assertEquals(3, findLoginByGroupId.size());
	}

	public void testFindLoginByNoInGroupId() {
		List<Login> findLoginByNoInGroupId = loginService.findLoginByNoInGroupId(Integer.valueOf(105));
		assertEquals(4, findLoginByNoInGroupId.size());
	}

	public void testUpdateGroupLogin() {
		Integer loginId = Integer.valueOf(102);
		List<Integer> listGroupId = Arrays.asList(new Integer[]{102, 105});
		loginService.updateGroupLogin(loginId, listGroupId);
	}

	public void testGetLogin() {
		Login login = loginService.getLogin("login_name_102", "hashed_password_102");
		assertEquals("login_name_102", login.getLoginName());
		assertEquals("email_102", login.getEmail());
		assertEquals("hashed_password_102", login.getHashedPassword());
		assertEquals("enabled_102", login.getEnabled());
	}

	public void testFindPermission() {
		List<String> findPermission = loginService.findPermission(Integer.valueOf(102));
		assertEquals(1, findPermission.size());
	}

	public void testFind() {
		LoginSearch search = new LoginSearch();
		search.setLoginName("login_name_page");
		search.setEmail("email_page");
		search.setEnabled("enabled_page");
		List<Login> find = loginService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		LoginSearch search = new LoginSearch();
		search.setLoginName("login_name_page");
		search.setEmail("email_page");
		search.setEnabled("enabled_page");
		Long count = loginService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		LoginSearch search = new LoginSearch();
		search.setLoginName("login_name_page");
		search.setEmail("email_page");
		search.setEnabled("enabled_page");
		Page page = new Page();
		List<Login> page2 = loginService.page(search, page);
		assertEquals(3, page2.size());
	}


	public void testGet() {
		Login login = loginService.get(Integer.valueOf(102));
		assertEquals("login_name_102", login.getLoginName());
		assertEquals("email_102", login.getEmail());
		assertEquals("hashed_password_102", login.getHashedPassword());
		assertEquals("enabled_102", login.getEnabled());
	}

	public void testInsert() {
		Login record = new Login();
		record.setLoginName("loginName");
		record.setEmail("email@email.com");
		record.setHashedPassword("hashedPassword");
		record.setEnabled("enabled");
		loginService.insert(record);
	}

	public void testUpdate() {
		Login record = new Login();
		record.setId(Integer.valueOf(103));
		record.setLoginName("loginName-updated");
		record.setEmail("email@email.com-updated");
		record.setHashedPassword("hashedPassword-updated");
		record.setEnabled("enabled-updated");
		loginService.update(record, true);
	}

	public void testDelete() {
		loginService.delete(Integer.valueOf(101));
	}

}
