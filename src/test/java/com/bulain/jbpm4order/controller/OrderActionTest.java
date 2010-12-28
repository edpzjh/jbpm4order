package com.bulain.jbpm4order.controller;

import java.util.List;

import com.bulain.common.JbpmTestCase;
import com.bulain.common.page.Page;
import com.bulain.jbpm4order.model.Order;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class OrderActionTest extends JbpmTestCase {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(OrderActionTest.class);
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

	public void testCRUD() throws Exception {
		initServletMockObjects();
		ActionProxy proxy = getActionProxy("/order/new");
		OrderAction orderAction = (OrderAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("order.name", "name");
		request.setParameter("order.note", "note");
		proxy = getActionProxy("/order/create");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/order/list");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		List<Order> listOrder = orderAction.getListOrder();
		assertEquals(1, listOrder.size());
		Page page = orderAction.getPage();
		assertEquals(1, page.getPage());
		
		Integer id = listOrder.get(0).getId();
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/order/edit");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		Order order = orderAction.getOrder();
		assertEquals("name", order.getName());
		assertEquals("note", order.getNote());
		
		initServletMockObjects();
		request.setParameter("order.id", Integer.toString(id));
		request.setParameter("order.name", "name-updated");
		request.setParameter("order.note", "note-updated");
		proxy = getActionProxy("/order/update");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/order/show");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		order = orderAction.getOrder();
		assertEquals("name-updated", order.getName());
		assertEquals("note-updated", order.getNote());
		
		initServletMockObjects();
		request.setParameter("id", Integer.toString(id));
		proxy = getActionProxy("/order/destroy");
		orderAction = (OrderAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
	}
}
