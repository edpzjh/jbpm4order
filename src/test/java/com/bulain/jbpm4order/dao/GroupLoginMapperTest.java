package com.bulain.jbpm4order.dao;

import java.util.Arrays;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.GroupLogin;
import com.bulain.jbpm4order.pojo.GroupSearch;
import com.bulain.jbpm4order.pojo.LoginSearch;

public class GroupLoginMapperTest extends ServiceTestCase {
	private GroupLoginMapper groupLoginMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_group_logins.xml");
		groupLoginMapper = (GroupLoginMapper) applicationContext.getBean("groupLoginMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(GroupLoginMapperTest.class);
	}

	public void testDeleteGroupLoginByLoginId() {
		int deleteGroupLoginByLoginId = groupLoginMapper.deleteGroupLoginByLoginId(Integer.valueOf(101));
		assertEquals(3, deleteGroupLoginByLoginId);
	}

	public void testDeleteGroupLoginByNotInLoginId() {
		GroupSearch search = new GroupSearch();
		search.setGroupId(Integer.valueOf(101));
		search.setListLoginId(Arrays.asList(new Integer[]{101,102}));
		int deleteGroupLoginByNotInLoginId = groupLoginMapper.deleteGroupLoginByNotInLoginId(search);
		assertEquals(1, deleteGroupLoginByNotInLoginId);
	}

	public void testDeleteGroupLoginByGroupId() {
		int deleteGroupLoginByGroupId = groupLoginMapper.deleteGroupLoginByGroupId(Integer.valueOf(101));
		assertEquals(2, deleteGroupLoginByGroupId);
	}

	public void testDeleteGroupLoginByNotInGroupId() {
		LoginSearch search = new LoginSearch();
		search.setLoginId(Integer.valueOf(101));
		search.setListGroupId(Arrays.asList(new Integer[]{101,102}));
		int deleteGroupLoginByNotInGroupId = groupLoginMapper.deleteGroupLoginByNotInGroupId(search);
		assertEquals(1, deleteGroupLoginByNotInGroupId);
	}

	public void testDeleteGroupLogin() {
		LoginSearch search = new LoginSearch();
		search.setLoginId(Integer.valueOf(101));
		search.setGroupId(Integer.valueOf(101));
		int deleteGroupLogin = groupLoginMapper.deleteGroupLogin(search);
		assertEquals(1, deleteGroupLogin);
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = groupLoginMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		GroupLogin record = new GroupLogin();
		record.setLoginId(Integer.valueOf(105));
		record.setGroupId(Integer.valueOf(105));
		int insert = groupLoginMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		GroupLogin record = new GroupLogin();
		record.setLoginId(Integer.valueOf(105));
		record.setGroupId(Integer.valueOf(105));
		int insertSelective = groupLoginMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

	public void testSelectByPrimaryKey() {
		GroupLogin select = groupLoginMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertEquals(Integer.valueOf(102), select.getLoginId());
		assertEquals(Integer.valueOf(102), select.getGroupId());
	}

	public void testUpdateByPrimaryKeySelective() {
		GroupLogin record = new GroupLogin();
		record.setId(Integer.valueOf(103));
		record.setLoginId(Integer.valueOf(103));
		record.setGroupId(Integer.valueOf(103));
		int updateByPrimaryKeySelective = groupLoginMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		GroupLogin record = new GroupLogin();
		record.setId(Integer.valueOf(104));
		record.setLoginId(Integer.valueOf(104));
		record.setGroupId(Integer.valueOf(104));
		int updateByPrimaryKey = groupLoginMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

}
