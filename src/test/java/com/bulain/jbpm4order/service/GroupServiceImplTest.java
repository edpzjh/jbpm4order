package com.bulain.jbpm4order.service;

import java.util.Arrays;
import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public class GroupServiceImplTest extends ServiceTestCase {
	private GroupService groupService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_groups.xml");
		groupService = (GroupService) applicationContext.getBean("groupService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(GroupServiceImplTest.class);
	}

	public void testFindGroupByLoginId() {
		List<Group> findGroupByLoginId = groupService.findGroupByLoginId(Integer.valueOf(105));
		assertEquals(3, findGroupByLoginId.size());
	}

	public void testFindGroupByNoLoginId() {
		List<Group> findGroupByNoLoginId = groupService.findGroupByNoLoginId(Integer.valueOf(105));
		assertEquals(4, findGroupByNoLoginId.size());
	}

	public void testUpdateGroupLogin() {
		Integer groupId = Integer.valueOf(102);
		List<Integer> listLoginId = Arrays.asList(new Integer[]{102, 105});
		groupService.updateGroupLogin(groupId, listLoginId);
	}

	public void testFindPermissionByGroupId() {
		List<Permission> findPermissionByGroupId = groupService.findPermissionByGroupId(Integer.valueOf(105));
		assertEquals(3, findPermissionByGroupId.size());
	}

	public void testFindPermissionByNoGroupId() {
		List<Permission> findPermissionByNoGroupId = groupService.findPermissionByNoGroupId(Integer.valueOf(105));
		assertEquals(2, findPermissionByNoGroupId.size());
	}

	public void testUpdateGroupPermission() {
		Integer groupId = Integer.valueOf(102);
		List<String> listPermission = Arrays.asList(new String[]{"permission_102", "permission_103"});
		groupService.updateGroupPermission(groupId, listPermission);
	}

	public void testFind() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		List<Group> find = groupService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		Long count = groupService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		Page page = new Page();
		List<Group> page2 = groupService.page(search, page);
		assertEquals(3, page2.size());
	}

	public void testGet() {
		Group group = groupService.get(Integer.valueOf(102));
		assertEquals("name_102", group.getName());
		assertEquals("note_102", group.getNote());
	}

	public void testInsert() {
		Group record = new Group();
		record.setName("name");
		record.setNote("note");
		groupService.insert(record);
	}

	public void testUpdate() {
		Group record = new Group();
		record.setId(Integer.valueOf(103));
		record.setName("name");
		record.setNote("note");
		groupService.update(record, true);
	}

	public void testDelete() {
		groupService.delete(Integer.valueOf(101));
	}

}
