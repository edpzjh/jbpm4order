package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.jbpm4order.pojo.Master;

public class MasterMapperTest extends ServiceTestCase {
	private MasterMapper masterMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_masters.xml");
		masterMapper = (MasterMapper) applicationContext.getBean("masterMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(MasterMapperTest.class);
	}

	public void testSelectList4Group() {
		List<Master> selectList4Group = masterMapper.selectList4Group();
		assertEquals(3, selectList4Group.size());
	}

	public void testSelectMaster4Group() {
		Master selectMaster4Group = masterMapper.selectMaster4Group(Integer.valueOf(105));
		assertEquals(Integer.valueOf(105), selectMaster4Group.getKey());
		assertEquals("name_page", selectMaster4Group.getValue());
	}

	public void testSelectList4Person() {
		List<Master> selectList4Person = masterMapper.selectList4Person();
		assertEquals(3, selectList4Person.size());
	}

	public void testSelectMaster4Person() {
		Master selectMaster4Person = masterMapper.selectMaster4Person(Integer.valueOf(105));
		assertNotNull(selectMaster4Person);
		assertEquals(Integer.valueOf(105), selectMaster4Person.getKey());
		assertEquals("last_name_page, first_name_page", selectMaster4Person.getValue());
	}

}
