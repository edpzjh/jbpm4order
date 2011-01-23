package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.pojo.LoginSearch;

public class LoginMapperTest extends ServiceTestCase {
	private LoginMapper loginMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_logins.xml");
		loginMapper = (LoginMapper) applicationContext.getBean("loginMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(LoginMapperTest.class);
	}

	public void testCountLoginByGroupId() {
		Long countLoginByGroupId = loginMapper.countLoginByGroupId(Integer.valueOf(105));
		assertEquals(Long.valueOf(3), countLoginByGroupId);
	}

	public void testFindLoginByGroupId() {
		List<Login> findLoginByGroupId = loginMapper.findLoginByGroupId(Integer.valueOf(105));
		assertEquals(3, findLoginByGroupId.size());
	}

	public void testFindLoginByNoGroupId() {
		List<Login> findLoginByNoGroupId = loginMapper.findLoginByNoGroupId(Integer.valueOf(105));
		assertEquals(4, findLoginByNoGroupId.size());
	}

	public void testFindLoginByLoginNames() {
		String[] loginNames = new String[]{"login_name_page"};
		List<Login> findLoginByLoginNames = loginMapper.findLoginByLoginNames(loginNames);
		assertEquals(3, findLoginByLoginNames.size());
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = loginMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		Login record = new Login();
		record.setLoginName("loginName");
		record.setEmail("email@email.com");
		record.setHashedPassword("hashedPassword");
		record.setEnabled("enabled");
		int insert = loginMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		Login record = new Login();
		record.setLoginName("loginName");
		record.setEmail("email@email.com");
		record.setHashedPassword("hashedPassword");
		record.setEnabled("enabled");
		int insertSelective = loginMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

	public void testSelectByPrimaryKey() {
		Login select = loginMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertEquals("login_name_102", select.getLoginName());
		assertEquals("email_102", select.getEmail());
		assertEquals("hashed_password_102", select.getHashedPassword());
		assertEquals("enabled_102", select.getEnabled());
	}

	public void testUpdateByPrimaryKeySelective() {
		Login record = new Login();
		record.setId(Integer.valueOf(103));
		record.setLoginName("loginName-updated");
		record.setEmail("email@email.com-updated");
		record.setHashedPassword("hashedPassword-updated");
		record.setEnabled("enabled-updated");
		int updateByPrimaryKeySelective = loginMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		Login record = new Login();
		record.setId(Integer.valueOf(103));
		record.setLoginName("loginName-updated");
		record.setEmail("email@email.com-updated");
		record.setHashedPassword("hashedPassword-updated");
		record.setEnabled("enabled-updated");
		int updateByPrimaryKey = loginMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

	public void testFind() {
		LoginSearch search = new LoginSearch();
		search.setLoginName("login_name_page");
		search.setEmail("email_page");
		search.setEnabled("enabled_page");
		List<Login> find = loginMapper.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		LoginSearch search = new LoginSearch();
		search.setLoginName("login_name_page");
		search.setEmail("email_page");
		search.setEnabled("enabled_page");
		Long count = loginMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		LoginSearch search = new LoginSearch();
		search.setLoginName("login_name_page");
		search.setEmail("email_page");
		search.setEnabled("enabled_page");
		Page page = new Page();
		page.setCount(10);
		search.setHigh(page.getHigh());
		search.setLow(page.getLow());
		List<Login> page2 = loginMapper.page(search);
		assertEquals(3, page2.size());
	}

}
