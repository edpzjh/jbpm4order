package com.bulain.jbpm4order.identity;

import java.util.List;

import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;

import com.bulain.common.ServiceTestCase;

public class IdentitySessionImplTest extends ServiceTestCase {
	private IdentitySession identitySession;
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(IdentitySessionImplTest.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("data/init_identity.xml");
		identitySession = (IdentitySession) applicationContext.getBean("identitySession");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		super.tearDownDB();
	}

	public void testUser(){
		String userId = "id";
		String givenName = "givenName";
		String familyName = "familyName";
		String businessEmail = "businessEmail";
		identitySession.createUser(userId, givenName, familyName, businessEmail);
		
		User findUserById = identitySession.findUserById(userId);
		assertNotNull(findUserById);
		assertEquals("id", findUserById.getId());
		assertEquals("givenName",findUserById.getGivenName());
		assertEquals("familyName",findUserById.getFamilyName());
		assertEquals("businessEmail",findUserById.getBusinessEmail());
		
		List<User> findUsersById = identitySession.findUsersById(userId);
		assertEquals(1, findUsersById.size());
		
		List<User> findUsers = identitySession.findUsers();
		assertEquals(1, findUsers.size());
		
		identitySession.deleteUser(userId);
		
		findUserById = identitySession.findUserById(userId);
		assertNull(findUserById);
	}
	
	public void testGroup(){
		String groupId = "id";
		String groupName = "id";
		String groupType = null;
		String parentGroupId = null;
		identitySession.createGroup(groupName, groupType, parentGroupId);
		
		Group findGroupById = identitySession.findGroupById(groupId);
		assertNotNull(findGroupById);
		assertEquals("id", findGroupById.getId());
		assertEquals("id", findGroupById.getName());
		assertNull(findGroupById.getType());
		
		identitySession.deleteGroup(groupId);
		
		findGroupById = identitySession.findGroupById(groupId);
		assertNull(findGroupById);
	}
	
	public void testMembership(){
		String userId = "id";
		String givenName = "givenName";
		String familyName = "familyName";
		String businessEmail = "businessEmail";
		identitySession.createUser(userId, givenName, familyName, businessEmail);
		
		String groupId = "id";
		String groupName = "id";
		String groupType = null;
		identitySession.createGroup(groupName, groupType, null);
		
		String role = null;
		identitySession.createMembership(userId, groupId, role);
		
		List<User> findUsersByGroup = identitySession.findUsersByGroup(groupId);
		assertEquals(1, findUsersByGroup.size());
		
		List<Group> findGroupsByUserAndGroupType = identitySession.findGroupsByUserAndGroupType(userId, groupType);
		assertEquals(1, findGroupsByUserAndGroupType.size());
		
		List<Group> findGroupsByUser = identitySession.findGroupsByUser(userId);
		assertEquals(1, findGroupsByUser.size());
		
		identitySession.deleteMembership(userId, groupId, role);
		identitySession.deleteGroup(groupId);
		identitySession.deleteUser(userId);
	}
}
