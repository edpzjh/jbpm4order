package com.bulain.jbpm4order.dao;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.pojo.Master;

public class MasterMapperTest extends ServiceTestCase {
    @Autowired
	private MasterMapper masterMapper;
	
    @BeforeTransaction
	public void setUpDB() throws Exception {
		super.setUpDB("test-data/init_masters.xml");
	}

    @AfterTransaction
	public void tearDownDB() throws Exception {
		super.tearDownDB();
	}
	
    @Test
	public void testSelectList4Group() {
		List<Master> selectList4Group = masterMapper.selectList4Group();
		assertEquals(3, selectList4Group.size());
	}

    @Test
	public void testSelectMaster4Group() {
		Master selectMaster4Group = masterMapper.selectMaster4Group(Integer.valueOf(105));
		assertEquals(Integer.valueOf(105), selectMaster4Group.getKey());
		assertEquals("name_page", selectMaster4Group.getValue());
	}

    @Test
	public void testSelectList4Person() {
		List<Master> selectList4Person = masterMapper.selectList4Person();
		assertEquals(3, selectList4Person.size());
	}

    @Test
	public void testSelectMaster4Person() {
		Master selectMaster4Person = masterMapper.selectMaster4Person(Integer.valueOf(105));
		assertNotNull(selectMaster4Person);
		assertEquals(Integer.valueOf(105), selectMaster4Person.getKey());
		assertEquals("last_name_page, first_name_page", selectMaster4Person.getValue());
	}

}
