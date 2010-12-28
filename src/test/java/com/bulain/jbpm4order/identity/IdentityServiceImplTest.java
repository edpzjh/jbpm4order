package com.bulain.jbpm4order.identity;

import java.util.List;

import com.bulain.common.ServiceTestCase;

public class IdentityServiceImplTest extends ServiceTestCase {
	private IdentityService identityService;
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(IdentityServiceImplTest.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("data/init_identity.xml");
		identityService = (IdentityService) applicationContext.getBean("identityServiceImpl");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		super.tearDownDB();
	}

	public void testUser(){
		UserImpl user = new UserImpl("id", "givenName", "familyName");
		user.setBusinessEmail("businessEmail");
		identityService.saveUser(user);
		
		String userId = "id";
		UserImpl findUserById = identityService.findUserById(userId);
		assertNotNull(findUserById);
		assertEquals("id", findUserById.getId());
		assertEquals("givenName",findUserById.getGivenName());
		assertEquals("familyName",findUserById.getFamilyName());
		assertEquals("businessEmail",findUserById.getBusinessEmail());
		
		List<UserImpl> findUsers = identityService.findUsers();
		assertEquals(1, findUsers.size());
		
		String[] userIds = new String[]{userId};
		List<UserImpl> findUsersById = identityService.findUsersById(userIds);
		assertEquals(1, findUsersById.size());
		
		identityService.deleteUser(userId);
		
		findUserById = identityService.findUserById(userId);
		assertNull(findUserById);
	}
	
	public void testGroup(){
		GroupImpl group = new GroupImpl();
		group.setId("id");
		group.setName("name");
		group.setType("type");
		identityService.saveGroup(group);
		
		String groupId = "id";
		GroupImpl findGroupById = identityService.findGroupById(groupId );
		assertNotNull(findGroupById);
		assertEquals("id", findGroupById.getId());
		assertEquals("id", findGroupById.getName());
		assertNull(findGroupById.getType());
		
		List<GroupImpl> findGroups = identityService.findGroups();
		assertEquals(1, findGroups.size());
		
		identityService.deleteGroup(groupId);
		
		findGroupById = identityService.findGroupById(groupId );
		assertNull(findGroupById);
	}
	
	public void testMembership(){
		UserImpl user = new UserImpl("id", "givenName", "familyName");
		user.setBusinessEmail("businessEmail");
		identityService.saveUser(user);
		
		GroupImpl group = new GroupImpl();
		group.setId("id");
		group.setName("name");
		group.setType("type");
		identityService.saveGroup(group);
		
		String userId = "id";
		String groupId = "id";
		String role = null;
		identityService.saveMembership(userId , groupId, role);
		
		List<UserImpl> findUsersByGroup = identityService.findUsersByGroup(groupId );
		assertEquals(1, findUsersByGroup.size());
		
		List<GroupImpl> findGroupsByUser = identityService.findGroupsByUser(userId);
		assertEquals(1, findGroupsByUser.size());
		
		List<GroupImpl> findGroupsByUserAndGroupType = identityService.findGroupsByUserAndGroupType(userId, null);
		assertEquals(1, findGroupsByUserAndGroupType.size());
		
		identityService.deleteMembership(userId, groupId, role);
		identityService.deleteGroup(groupId);
		identityService.deleteUser(userId);
	}
}
