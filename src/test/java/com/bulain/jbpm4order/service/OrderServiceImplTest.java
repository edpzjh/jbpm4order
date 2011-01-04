package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.jbpm4order.model.Order;
import com.bulain.jbpm4order.pojo.OrderSearch;

public class OrderServiceImplTest extends ServiceTestCase {
	private OrderService orderService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_orders.xml");
		orderService = (OrderService) applicationContext.getBean("orderService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(OrderServiceImplTest.class);
	}

	public void testGetByWfId() {
		Order byWfId = orderService.getByWfId("wf_id_wf");
		assertNotNull(byWfId);
		assertEquals("name_wf", byWfId.getName());
		assertEquals("note_wf", byWfId.getNote());
		assertEquals("wf_id_wf", byWfId.getWfId());
	}

	public void testFind() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		List<Order> find = orderService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		Long count = orderService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		Page page = new Page();
		List<Order> listOrder = orderService.page(search, page);
		assertEquals(3, listOrder.size());
	}

	public void testGet() {
		Order order = orderService.get(Integer.valueOf(102));
		assertNotNull(order);
		assertEquals("name_102", order.getName());
		assertEquals("note_102", order.getNote());
	}

	public void testInsert() {
		Order record = new Order();
		record.setName("name");
		record.setNote("note");
		orderService.insert(record);
	}

	public void testUpdate() {
		Order record = new Order();
		record.setId(Integer.valueOf(103));
		record.setName("name-updated");
		record.setNote("note-updated");
		orderService.update(record, true);
	}

	public void testDelete() {
		orderService.delete(Integer.valueOf(101));
	}

}
