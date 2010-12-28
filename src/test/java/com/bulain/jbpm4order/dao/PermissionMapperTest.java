package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.PermissionSearch;

public class PermissionMapperTest extends ServiceTestCase {
	private PermissionMapper permissionMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_permissions.xml");
		permissionMapper = (PermissionMapper) applicationContext.getBean("permissionMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(PermissionMapperTest.class);
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = permissionMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		Permission record = new Permission();
		record.setPermission("permission");
		int insert = permissionMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		Permission record = new Permission();
		record.setPermission("permission");
		int insert = permissionMapper.insertSelective(record);
		assertEquals(1, insert);
	}

	public void testSelectByPrimaryKey() {
		Permission selectByPrimaryKey = permissionMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertNotNull(selectByPrimaryKey);
		
		assertEquals("permission_102", selectByPrimaryKey.getPermission());
	}

	public void testUpdateByPrimaryKeySelective() {
		Permission record = new Permission();
		record.setId(Integer.valueOf(103));
		record.setPermission("permission-updated");
		int updateByPrimaryKeySelective = permissionMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		Permission record = new Permission();
		record.setId(Integer.valueOf(104));
		record.setPermission("permission-updated");
		int updateByPrimaryKey = permissionMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

	public void testFind(){
		PermissionSearch search = new PermissionSearch();
		search.setPermission("permission_page");
		List<Permission> find = permissionMapper.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		PermissionSearch search = new PermissionSearch();
		search.setPermission("permission_page");
		Long count = permissionMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		PermissionSearch search = new PermissionSearch();
		search.setPermission("permission_page");
		Page page= new Page();
		page.setCount(10);
		search.setHigh(page.getHigh());
		search.setLow(page.getLow());
		List<Permission> listPermission = permissionMapper.page(search);
		assertEquals(3, listPermission.size());
	}

	public void testFindPermissionByGroupId() {
		List<Permission> findPermissionByGroupId = permissionMapper.findPermissionByGroupId(Integer.valueOf(105));
		assertEquals(3, findPermissionByGroupId.size());
	}

	public void testFindPermissionByNoGroupId() {
		List<Permission> findPermissionByNoGroupId = permissionMapper.findPermissionByNoGroupId(Integer.valueOf(105));
		assertEquals(4, findPermissionByNoGroupId.size());
	}
}
