package com.bulain.jbpm4order.dao;

import java.util.Arrays;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.GroupPermission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public class GroupPermissionMapperTest extends ServiceTestCase {
	private GroupPermissionMapper groupPermissionMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_group_permissions.xml");
		groupPermissionMapper = (GroupPermissionMapper) applicationContext.getBean("groupPermissionMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(GroupPermissionMapperTest.class);
	}

	public void testDeleteGroupPermissionByGroupId() {
		int deleteGroupPermissionByGroupId = groupPermissionMapper.deleteGroupPermissionByGroupId(Integer.valueOf(101));
		assertEquals(2, deleteGroupPermissionByGroupId);
	}

	public void testDeleteGroupPermissionByNotInPermission() {
		GroupSearch search = new GroupSearch();
		search.setGroupId(Integer.valueOf(101));
		search.setListPermission(Arrays.asList(new String[]{"permission_101","permission_102"}));
		int deleteGroupPermissionByNotInPermission = groupPermissionMapper.deleteGroupPermissionByNotInPermission(search );
		assertEquals(1, deleteGroupPermissionByNotInPermission);
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = groupPermissionMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		GroupPermission record = new GroupPermission();
		record.setPermission("permission");
		int insert = groupPermissionMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		GroupPermission record = new GroupPermission();
		record.setPermission("permission");
		int insertSelective = groupPermissionMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

	public void testSelectByPrimaryKey() {
		GroupPermission select = groupPermissionMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertEquals("permission_102", select.getPermission());
	}

	public void testUpdateByPrimaryKeySelective() {
		GroupPermission record = new GroupPermission();
		record.setId(Integer.valueOf(103));
		record.setPermission("permission-updated");
		int updateByPrimaryKeySelective = groupPermissionMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		GroupPermission record = new GroupPermission();
		record.setId(Integer.valueOf(103));
		record.setPermission("permission-updated");
		int updateByPrimaryKey = groupPermissionMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

}
