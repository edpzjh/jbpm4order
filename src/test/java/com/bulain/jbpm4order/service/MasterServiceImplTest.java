package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.jbpm4order.pojo.Master;

public class MasterServiceImplTest extends ServiceTestCase {
	private MasterService masterService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_masters.xml");
		masterService = (MasterService) applicationContext.getBean("masterService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(MasterServiceImplTest.class);
	}


	public void testGetValue4Group() {
		String value4Group = masterService.getValue4Group(Integer.valueOf(105));
		assertEquals("name_page", value4Group);
	}

	public void testFindMaster4Group() {
		List<Master> findMaster4Group = masterService.findMaster4Group();
		assertEquals(4, findMaster4Group.size());
	}

	public void testGetValue4Person() {
		String value4Person = masterService.getValue4Person(Integer.valueOf(105));
		assertEquals("last_name_page, first_name_page", value4Person);
	}

	public void testFindMaster4Person() {
		List<Master> findMaster4Person = masterService.findMaster4Person();
		assertEquals(4, findMaster4Person.size());
	}

}
