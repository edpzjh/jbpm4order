package com.bulain.jbpm4order.service;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Order;
import com.bulain.jbpm4order.pojo.OrderSearch;

public class OrderServiceImplTest extends ServiceTestCase {
    @Autowired
	private OrderService orderService;

    @BeforeTransaction
	public void setUpDB() throws Exception {
		super.setUpDB("test-data/init_orders.xml");
	}

    @AfterTransaction
	public void tearDownDB() throws Exception {
		super.tearDownDB();
	}
	
    @Test
	public void testGetByWfId() {
		Order byWfId = orderService.getByWfId("wf_id_wf");
		assertNotNull(byWfId);
		assertEquals("name_wf", byWfId.getName());
		assertEquals("note_wf", byWfId.getNote());
		assertEquals("wf_id_wf", byWfId.getWfId());
	}

    @Test
	public void testFind() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		List<Order> find = orderService.find(search);
		assertEquals(3, find.size());
	}

    @Test
	public void testCount() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		Long count = orderService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

    @Test
	public void testPage() {
		OrderSearch search = new OrderSearch();
		search.setName("name_page");
		Page page = new Page();
		List<Order> listOrder = orderService.page(search, page);
		assertEquals(3, listOrder.size());
	}

    @Test
	public void testGet() {
		Order order = orderService.get(Integer.valueOf(102));
		assertNotNull(order);
		assertEquals("name_102", order.getName());
		assertEquals("note_102", order.getNote());
	}

    @Test
	public void testInsert() {
		Order record = new Order();
		record.setName("name");
		record.setNote("note");
		orderService.insert(record);
	}

    @Test
	public void testUpdate() {
		Order record = new Order();
		record.setId(Integer.valueOf(103));
		record.setName("name-updated");
		record.setNote("note-updated");
		orderService.update(record, true);
	}

    @Test
	public void testDelete() {
		orderService.delete(Integer.valueOf(101));
	}

}
