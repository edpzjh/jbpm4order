package com.bulain.jbpm4order.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.OrderMapper;
import com.bulain.jbpm4order.model.Order;
import com.bulain.jbpm4order.pojo.OrderSearch;


public class OrderServiceImpl extends PagedServiceImpl<OrderSearch, Order> implements OrderService {
	private OrderMapper orderMapper;
	
	@Override
	protected PagedMapper<OrderSearch, Order> getPagedMapper() {
		return orderMapper;
	}

	public Order getByWfId(String wfId) {
		return orderMapper.selectByWfId(wfId);
	}

	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
}
