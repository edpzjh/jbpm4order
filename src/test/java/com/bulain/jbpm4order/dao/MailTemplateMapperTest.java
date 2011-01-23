package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.MailTemplate;
import com.bulain.jbpm4order.pojo.MailTemplateSearch;

public class MailTemplateMapperTest extends ServiceTestCase {
	private MailTemplateMapper mailTemplateMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_mail_templates.xml");
		mailTemplateMapper = (MailTemplateMapper) applicationContext.getBean("mailTemplateMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(MailTemplateMapperTest.class);
	}

	public void testUpdateByPrimaryKeyWithBLOBs() {
		MailTemplate record = new MailTemplate();
		record.setId(Integer.valueOf(108));
		record.setName("name-updated");
		record.setLang("lang-updated");
		record.setSubject("subject-updated");
		record.setBody(new byte[]{'a','b','c'});
		int updateByPrimaryKey = mailTemplateMapper.updateByPrimaryKey(record );
		assertEquals(1, updateByPrimaryKey);
	}

	public void testSelectByPrimaryKeyWithoutBLOBs() {
		MailTemplate select = mailTemplateMapper.selectByPrimaryKeyWithoutBLOBs(Integer.valueOf(109));
		assertEquals("name_109", select.getName());
		assertEquals("lang_109", select.getLang());
		assertEquals("subject_109", select.getSubject());
		assertNull(select.getBody());
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = mailTemplateMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		MailTemplate record = new MailTemplate();
		record.setName("name");
		record.setLang("lang");
		record.setSubject("subject");
		record.setBody(new byte[]{'a','b','c'});
		int insert = mailTemplateMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		MailTemplate record = new MailTemplate();
		record.setName("name");
		record.setLang("lang");
		record.setSubject("subject");
		record.setBody(new byte[]{'a','b','c'});
		int insertSelective = mailTemplateMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

	public void testSelectByPrimaryKey() {
		MailTemplate select = mailTemplateMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertEquals("name_102", select.getName());
		assertEquals("lang_102", select.getLang());
		assertEquals("subject_102", select.getSubject());
		assertNull(select.getBody());
	}

	public void testUpdateByPrimaryKeySelective() {
		MailTemplate record = new MailTemplate();
		record.setId(Integer.valueOf(103));
		record.setName("name-updated");
		record.setLang("lang-updated");
		record.setSubject("subject-updated");
		record.setBody(new byte[]{'a','b','c'});
		int updateByPrimaryKeySelective = mailTemplateMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		MailTemplate record = new MailTemplate();
		record.setId(Integer.valueOf(103));
		record.setName("name-updated");
		record.setLang("lang-updated");
		record.setSubject("subject-updated");
		record.setBody(new byte[]{'a','b','c'});
		int updateByPrimaryKey = mailTemplateMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

	public void testFind() {
		MailTemplateSearch search = new MailTemplateSearch();
		search.setName("name_page");
		search.setLang("lang_page");
		List<MailTemplate> find = mailTemplateMapper.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		MailTemplateSearch search = new MailTemplateSearch();
		search.setName("name_page");
		search.setLang("lang_page");
		Long count = mailTemplateMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		MailTemplateSearch search = new MailTemplateSearch();
		search.setName("name_page");
		search.setLang("lang_page");
		Page page = new Page();
		page.setCount(10);
		search.setHigh(page.getHigh());
		search.setLow(page.getLow());
		List<MailTemplate> page2 = mailTemplateMapper.page(search);
		assertEquals(3, page2.size());
	}

}
