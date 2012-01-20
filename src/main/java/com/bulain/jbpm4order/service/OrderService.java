package com.bulain.jbpm4order.service;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Order;
import com.bulain.jbpm4order.pojo.OrderSearch;

public interface OrderService extends PagedService<Order, OrderSearch> {
    Order getByWfId(String wfId);
}
