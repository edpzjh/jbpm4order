package com.bulain.jbpm4order.dao;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.GroupPermission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public class GroupPermissionMapperTest extends ServiceTestCase {
    @Autowired
	private GroupPermissionMapper groupPermissionMapper;
	
    @BeforeTransaction
	public void setUpDB() throws Exception {
		super.setUpDB("test-data/init_group_permissions.xml");
	}

    @AfterTransaction
	public void tearDownDB() throws Exception {
		super.tearDownDB();
	}
	
    @Test
	public void testDeleteGroupPermissionByGroupId() {
		int deleteGroupPermissionByGroupId = groupPermissionMapper.deleteGroupPermissionByGroupId(Integer.valueOf(101));
		assertEquals(2, deleteGroupPermissionByGroupId);
	}

    @Test
	public void testDeleteGroupPermissionByNotInPermission() {
		GroupSearch search = new GroupSearch();
		search.setGroupId(Integer.valueOf(101));
		search.setListPermission(Arrays.asList(new String[]{"permission_101","permission_102"}));
		int deleteGroupPermissionByNotInPermission = groupPermissionMapper.deleteGroupPermissionByNotInPermission(search );
		assertEquals(1, deleteGroupPermissionByNotInPermission);
	}

    @Test
	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = groupPermissionMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

    @Test
	public void testInsert() {
		GroupPermission record = new GroupPermission();
		record.setPermission("permission");
		int insert = groupPermissionMapper.insert(record);
		assertEquals(1, insert);
	}

    @Test
	public void testInsertSelective() {
		GroupPermission record = new GroupPermission();
		record.setPermission("permission");
		int insertSelective = groupPermissionMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

    @Test
	public void testSelectByPrimaryKey() {
		GroupPermission select = groupPermissionMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertEquals("permission_102", select.getPermission());
	}

    @Test
	public void testUpdateByPrimaryKeySelective() {
		GroupPermission record = new GroupPermission();
		record.setId(Integer.valueOf(103));
		record.setPermission("permission-updated");
		int updateByPrimaryKeySelective = groupPermissionMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

    @Test
	public void testUpdateByPrimaryKey() {
		GroupPermission record = new GroupPermission();
		record.setId(Integer.valueOf(103));
		record.setPermission("permission-updated");
		int updateByPrimaryKey = groupPermissionMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

}
