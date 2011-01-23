package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Authorize;
import com.bulain.jbpm4order.pojo.AuthorizeSearch;

public class AuthorizeServiceImplTest extends ServiceTestCase {
	private AuthorizeService authorizeService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_authorizes.xml");
		authorizeService = (AuthorizeService) applicationContext.getBean("authorizeService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(AuthorizeServiceImplTest.class);
	}

	public void testGetPermission() {
		String controller = "controller_108";
		String action = "action_108";
		String permission = authorizeService.getPermission(controller, action);
		assertEquals("permission_108", permission);
	}

	public void testGet() {
		Authorize authorize = authorizeService.get(Integer.valueOf(102));
		assertNotNull(authorize);
		
		assertEquals("controller_102", authorize.getController());
		assertEquals("action_102", authorize.getAction());
		assertEquals("permission_102", authorize.getPermission());
	}

	public void testInsert() {
		Authorize record = new Authorize();
		record.setController("controller");
		record.setAction("action");
		record.setPermission("permission");
		authorizeService.insert(record);
	}

	public void testUpdate() {
		Authorize record = new Authorize();
		record.setId(Integer.valueOf(103));
		record.setController("controller-updated");
		record.setAction("action-updated");
		record.setPermission("permission-updated");
		
		authorizeService.update(record, true);
	}

	public void testDelete() {
		authorizeService.delete(Integer.valueOf(101));
	}

	public void testFind() {
		AuthorizeSearch search = new AuthorizeSearch();
		search.setController("controller_page");
		search.setAction("action_page");
		
		List<Authorize> find = authorizeService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		AuthorizeSearch search = new AuthorizeSearch();
		search.setController("controller_page");
		search.setAction("action_page");
		Long count = authorizeService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		AuthorizeSearch search = new AuthorizeSearch();
		search.setController("controller_page");
		search.setAction("action_page");
		Page page= new Page();
		page.setCount(10);
		List<Authorize> listAuthorize = authorizeService.page(search, page);
		assertEquals(3, listAuthorize.size());
	}

}
