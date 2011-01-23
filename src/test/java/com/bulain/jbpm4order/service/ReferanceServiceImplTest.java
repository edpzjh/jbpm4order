package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.model.ReferanceBean;
import com.bulain.jbpm4order.pojo.Item;

public class ReferanceServiceImplTest extends ServiceTestCase {
	private ReferanceService referanceService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_referances.xml");
		referanceService = (ReferanceService) applicationContext.getBean("referanceService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(ReferanceServiceImplTest.class);
	}


	public void testDelete() {
		referanceService.delete(Integer.valueOf(101));
	}

	public void testGetInteger() {
		Referance referance = referanceService.get(Integer.valueOf(102));
		assertNotNull(referance);
		assertEquals("name_102", referance.getName());
		assertEquals("code_102", referance.getCode());
		assertEquals("text_102", referance.getText());
		assertEquals("lang_102", referance.getLang());
		assertEquals("catagory_102", referance.getCatagory());
	}

	public void testInsertReferance() {
		Referance record = new Referance();
		record.setName("name");
		record.setCode("code");
		record.setLang("lang");
		record.setCatagory("catagory");
		record.setText("text");
		referanceService.insert(record);
	}

	public void testInsertReferanceBean() {
		ReferanceBean referanceBean = new ReferanceBean();
		referanceBean.setName("name");
		referanceBean.setCode("code");
		referanceBean.setCatagory("catagory");
		referanceBean.setTextEN("textEN");
		referanceBean.setTextEN("textEN");
		referanceService.insert(referanceBean);
	}

	public void testUpdateReferanceBoolean() {
		Referance record = new Referance();
		record.setId(Integer.valueOf(103));
		record.setName("name-updated");
		record.setCode("code-updated");
		record.setLang("lang-updated");
		record.setCatagory("catagory-updated");
		record.setText("text-updated");
		referanceService.update(record, true);
	}

	public void testGetTextStringStringString() {
		String text = referanceService.getText("name_102", "code_102", "lang_102");
		assertEquals("", text);
	}

	public void testGetTextStringStringStringString() {
		String text = referanceService.getText("name_102", "code_102", "lang_102", "catagory_102");
		assertEquals("text_102", text);
	}

	public void testFindItemStringString() {
		List<Item> findItem = referanceService.findItem("name_page", "lang_page");
		assertEquals(1, findItem.size());
	}

	public void testFindItemStringStringString() {
		List<Item> findItem = referanceService.findItem("name_page", "lang_page", "catagory_page");
		assertEquals(4, findItem.size());
	}

}
