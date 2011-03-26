package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.GroupSearch;
import com.bulain.jbpm4order.pojo.GroupView;
import com.bulain.jbpm4order.pojo.LoginSearch;
import com.bulain.jbpm4order.service.GroupService;
import com.bulain.jbpm4order.service.LoginService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class GroupActionTest extends ActionTestCase {
    @Autowired
	private LoginService loginService;
    @Autowired
	private GroupService groupService;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		super.setUpDB("data/init_action.xml");
		super.setUpAction("admin", "admin");
	}

	@After
	public void tearDown() throws Exception {
		super.tearDownAction();
		super.tearDownDB();
		super.tearDown();
	}

	@Test
	public void testCURD() throws Exception {
		initServletMockObjects();
		ActionProxy proxy = getActionProxy("/group/new");
		GroupAction groupAction = (GroupAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("group.name", "name");
		request.setParameter("group.note", "note");
		
		proxy = getActionProxy("/group/create");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/group/list");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		List<GroupView> listGroup = groupAction.getListGroup();
		assertEquals(2, listGroup.size());
		Page page = groupAction.getPage();
		assertEquals(1, page.getPage());
		
		Integer id = listGroup.get(1).getId();
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/group/edit");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		Group group = groupAction.getGroup();
		assertEquals("name", group.getName());
		assertEquals("note", group.getNote());
		
		initServletMockObjects();
		request.setParameter("group.id", Integer.toString(id));
		request.setParameter("group.name", "name-updated");
		request.setParameter("group.note", "note-updated");
		proxy = getActionProxy("/group/update");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/group/show");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		group = groupAction.getGroup();
		assertEquals("name-updated", group.getName());
		assertEquals("note-updated", group.getNote());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/group/destroy");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}
	
	@Test
	public void testUser() throws Exception{
		Login login = new Login();
		login.setLoginName("loginName");
		login.setEmail("email@email.com");
		login.setHashedPassword("hashedPassword");
		login.setEnabled("enabled");
		loginService.insert(login);
		
		LoginSearch search = new LoginSearch();
		search.setLoginName(login.getLoginName());
		search.setEmail(login.getEmail());
		search.setEnabled(login.getEnabled());
		List<Login> find = loginService.find(search);
		assertTrue(find.size()>0);
		Login login2 = find.get(find.size()-1);
		Integer loginId = login2.getId();
		
		Group group = new Group();
		group.setName("name");
		group.setNote("note");
		groupService.insert(group);
		
		GroupSearch gsearch = new GroupSearch();
		gsearch.setName(group.getName());
		List<Group> find2 = groupService.find(gsearch);
		assertTrue(find2.size()>0);
		Group group2 = find2.get(find2.size()-1);
		Integer groupId = group2.getId();
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		ActionProxy proxy = getActionProxy("/group/editLogin");
		GroupAction groupAction = (GroupAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		List<Login> listLoginSrc = groupAction.getListLoginSrc();
		assertEquals(2, listLoginSrc.size());
		
		List<Login> listLoginDist = groupAction.getListLoginDist();
		assertEquals(0, listLoginDist.size());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		request.setParameter("listLoginId", new String[]{Integer.toString(loginId)});
		proxy = getActionProxy("/group/updateLogin");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		proxy = getActionProxy("/group/editLogin");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		listLoginSrc = groupAction.getListLoginSrc();
		assertEquals(1, listLoginSrc.size());
		
		listLoginDist = groupAction.getListLoginDist();
		assertEquals(1, listLoginDist.size());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		request.setParameter("listLoginId", new String[]{});
		proxy = getActionProxy("/group/updateLogin");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		loginService.delete(loginId);
		
		groupService.delete(groupId);
	}
	
	@Test
	public void testPermission() throws Exception{
		Group group = new Group();
		group.setName("name");
		group.setNote("note");
		groupService.insert(group);
		
		GroupSearch gsearch = new GroupSearch();
		gsearch.setName(group.getName());
		List<Group> find2 = groupService.find(gsearch);
		assertTrue(find2.size()>0);
		Group group2 = find2.get(find2.size()-1);
		Integer groupId = group2.getId();
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		ActionProxy proxy = getActionProxy("/group/editPermission");
		GroupAction groupAction = (GroupAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		List<Permission> listPermissionSrc = groupAction.getListPermissionSrc();
		assertEquals(1, listPermissionSrc.size());
		
		List<Permission> listPermissionDist = groupAction.getListPermissionDist();
		assertEquals(0, listPermissionDist.size());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		request.setParameter("listPermission", new String[]{"Admin"});
		proxy = getActionProxy("/group/updatePermission");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		proxy = getActionProxy("/group/editPermission");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		listPermissionSrc = groupAction.getListPermissionSrc();
		assertEquals(0, listPermissionSrc.size());
		
		listPermissionDist = groupAction.getListPermissionDist();
		assertEquals(1, listPermissionDist.size());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(groupId));
		request.setParameter("listPermission", new String[]{});
		proxy = getActionProxy("/group/updatePermission");
		groupAction = (GroupAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		groupService.delete(groupId);
	
	}
}
