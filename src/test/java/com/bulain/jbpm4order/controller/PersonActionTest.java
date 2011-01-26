package com.bulain.jbpm4order.controller;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.test.Struts2TestCase;
import com.bulain.jbpm4order.model.Person;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class PersonActionTest extends Struts2TestCase {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(PersonActionTest.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("data/init_action.xml");
		super.setUpAction("admin", "admin");
	}

	protected void tearDown() throws Exception {
		super.tearDownAction();
		super.tearDownDB();
		super.tearDown();
	}

	public void testCURD() throws Exception {
		initServletMockObjects();
		ActionProxy proxy = getActionProxy("/person/new");
		PersonAction personAction = (PersonAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("person.firstName", "firstName");
		request.setParameter("person.lastName", "lastName");
		
		proxy = getActionProxy("/person/create");
		personAction = (PersonAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/person/list");
		personAction = (PersonAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		List<Person> listPerson = personAction.getListPerson();
		assertEquals(2, listPerson.size());
		Page page = personAction.getPage();
		assertEquals(1, page.getPage());
		
		Integer id = listPerson.get(1).getId();
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/person/edit");
		personAction = (PersonAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		Person person = personAction.getPerson();
		assertEquals("firstName", person.getFirstName());
		assertEquals("lastName", person.getLastName());
		
		initServletMockObjects();
		request.setParameter("person.id", Integer.toString(id));
		request.setParameter("person.firstName", "firstName-updated");
		request.setParameter("person.lastName", "lastName-updated");
		proxy = getActionProxy("/person/update");
		personAction = (PersonAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/person/show");
		personAction = (PersonAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		person = personAction.getPerson();
		assertEquals("firstName-updated", person.getFirstName());
		assertEquals("lastName-updated", person.getLastName());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/person/destroy");
		personAction = (PersonAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}

}
