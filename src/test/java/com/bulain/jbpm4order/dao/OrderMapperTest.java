package com.bulain.jbpm4order.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Order;
import com.bulain.jbpm4order.pojo.OrderSearch;

@DataSet(file = "test-data/init_orders.xml")
public class OrderMapperTest extends ServiceTestCase {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = orderMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Order record = new Order();
        record.setName("name");
        record.setNote("note");
        int insert = orderMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Order record = new Order();
        record.setName("name");
        record.setNote("note");
        int insert = orderMapper.insertSelective(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Order selectByPrimaryKey = orderMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);
        assertEquals("name_102", selectByPrimaryKey.getName());
        assertEquals("note_102", selectByPrimaryKey.getNote());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Order record = new Order();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setNote("note-updated");
        int updateByPrimaryKeySelective = orderMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Order record = new Order();
        record.setId(Long.valueOf(104));
        record.setName("name-updated");
        record.setNote("note-updated");
        int updateByPrimaryKey = orderMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        List<Order> find = orderMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenNoParam() {
        OrderSearch search = new OrderSearch();
        List<Order> find = orderMapper.find(search);
        assertEquals(8, find.size());
    }

    @Test
    public void testShoudExecuteWhenFirstNameIsNullStr() {
        OrderSearch search = new OrderSearch();
        search.setName("");
        List<Order> find = orderMapper.find(search);
        assertEquals(8, find.size());
    }

    @Test
    public void testCount() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        Long count = orderMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        search.setLow(0);
        search.setHigh(20);
        List<Order> listOrder = orderMapper.page(search);
        assertEquals(3, listOrder.size());
    }

    @Test
    public void testSelectByWfId() {
        Order byWfId = orderMapper.selectByWfId("wf_id_wf");
        assertNotNull(byWfId);
        assertEquals("name_wf", byWfId.getName());
        assertEquals("note_wf", byWfId.getNote());
        assertEquals("wf_id_wf", byWfId.getWfId());
    }
}
