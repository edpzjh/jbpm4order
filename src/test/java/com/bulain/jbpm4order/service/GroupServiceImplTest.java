package com.bulain.jbpm4order.service;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public class GroupServiceImplTest extends ServiceTestCase {
    @Autowired
	private GroupService groupService;
	
    @BeforeTransaction
	public void setUp() throws Exception {
		super.setUpDB("test-data/init_groups.xml");
	}

    @AfterTransaction
	public void tearDown() throws Exception {
		super.tearDownDB();
	}
	
    @Test
	public void testFindGroupByLoginId() {
		List<Group> findGroupByLoginId = groupService.findGroupByLoginId(Integer.valueOf(105));
		assertEquals(3, findGroupByLoginId.size());
	}

    @Test
	public void testFindGroupByNoLoginId() {
		List<Group> findGroupByNoLoginId = groupService.findGroupByNoLoginId(Integer.valueOf(105));
		assertEquals(4, findGroupByNoLoginId.size());
	}

    @Test
	public void testUpdateGroupLogin() {
		Integer groupId = Integer.valueOf(102);
		List<Integer> listLoginId = Arrays.asList(new Integer[]{102, 105});
		groupService.updateGroupLogin(groupId, listLoginId);
	}

    @Test
	public void testFindPermissionByGroupId() {
		List<Permission> findPermissionByGroupId = groupService.findPermissionByGroupId(Integer.valueOf(105));
		assertEquals(3, findPermissionByGroupId.size());
	}

    @Test
	public void testFindPermissionByNoGroupId() {
		List<Permission> findPermissionByNoGroupId = groupService.findPermissionByNoGroupId(Integer.valueOf(105));
		assertEquals(2, findPermissionByNoGroupId.size());
	}

    @Test
	public void testUpdateGroupPermission() {
		Integer groupId = Integer.valueOf(102);
		List<String> listPermission = Arrays.asList(new String[]{"permission_102", "permission_103"});
		groupService.updateGroupPermission(groupId, listPermission);
	}

    @Test
	public void testFind() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		List<Group> find = groupService.find(search);
		assertEquals(3, find.size());
	}

    @Test
	public void testCount() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		Long count = groupService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

    @Test
	public void testPage() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		Page page = new Page();
		List<Group> page2 = groupService.page(search, page);
		assertEquals(3, page2.size());
	}

    @Test
	public void testGet() {
		Group group = groupService.get(Integer.valueOf(102));
		assertEquals("name_102", group.getName());
		assertEquals("note_102", group.getNote());
	}

    @Test
	public void testInsert() {
		Group record = new Group();
		record.setName("name");
		record.setNote("note");
		groupService.insert(record);
	}

    @Test
	public void testUpdate() {
		Group record = new Group();
		record.setId(Integer.valueOf(103));
		record.setName("name");
		record.setNote("note");
		groupService.update(record, true);
	}

    @Test
	public void testDelete() {
		groupService.delete(Integer.valueOf(101));
	}

}
