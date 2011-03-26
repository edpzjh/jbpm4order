package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.bulain.jbpm4order.model.Profile;
import com.bulain.jbpm4order.pojo.ProfileView;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class ProfileActionTest extends ActionTestCase {
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
		ActionProxy proxy = getActionProxy("/profile/new");
		ProfileAction profileAction = (ProfileAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("profile.personId", "1");
		request.setParameter("profile.language", "language");
		request.setParameter("profile.country", "country");
		
		proxy = getActionProxy("/profile/create");
		profileAction = (ProfileAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/profile/list");
		profileAction = (ProfileAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		List<ProfileView> listProfile = profileAction.getListProfile();
		assertEquals(1, listProfile.size());
		Page page = profileAction.getPage();
		assertEquals(1, page.getPage());
		
		Integer id = listProfile.get(0).getId();
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/profile/edit");
		profileAction = (ProfileAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		Profile profile = profileAction.getProfile();
		assertEquals(Integer.valueOf(1), profile.getPersonId());
		assertEquals("language", profile.getLanguage());
		assertEquals("country", profile.getCountry());
		
		
		initServletMockObjects();
		request.setParameter("profile.id", Integer.toString(id));
		request.setParameter("profile.personId", "1");
		request.setParameter("profile.language", "language-updated");
		request.setParameter("profile.country", "country-updated");
		proxy = getActionProxy("/profile/update");
		profileAction = (ProfileAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/profile/show");
		profileAction = (ProfileAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		profile = profileAction.getProfile();
		assertEquals(Integer.valueOf(1), profile.getPersonId());
		assertEquals("language-updated", profile.getLanguage());
		assertEquals("country-updated", profile.getCountry());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/profile/destroy");
		profileAction = (ProfileAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}
}
