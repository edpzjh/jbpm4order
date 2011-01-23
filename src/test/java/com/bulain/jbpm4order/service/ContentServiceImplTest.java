package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Content;
import com.bulain.jbpm4order.pojo.ContentSearch;

public class ContentServiceImplTest extends ServiceTestCase {
	private ContentService contentService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_contents.xml");
		contentService = (ContentService) applicationContext.getBean("contentService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(ContentServiceImplTest.class);
	}

	public void testGetWithoutBLOBs() {
		Content select = contentService.getWithoutBLOBs(Integer.valueOf(109));
		assertEquals("file_name_109", select.getFileName());
		assertEquals("content_type_109", select.getContentType());
		assertEquals("catagory_109", select.getCatagory());
		assertEquals("lang_109", select.getLang());
		assertEquals(Integer.valueOf(109), select.getRefId());
		assertEquals("ref_name_109", select.getRefName());
		assertNull(select.getBytes());
	}

	public void testGet() {
		Content select = contentService.get(Integer.valueOf(102));
		assertEquals("file_name_102", select.getFileName());
		assertEquals("content_type_102", select.getContentType());
		assertEquals("catagory_102", select.getCatagory());
		assertEquals("lang_102", select.getLang());
		assertEquals(Integer.valueOf(102), select.getRefId());
		assertEquals("ref_name_102", select.getRefName());
		assertNull(select.getBytes());
	}

	public void testInsert() {
		Content record = new Content();
		record.setFileName("fileName");
		record.setContentType("contentType");
		record.setCatagory("catagory");
		record.setLang("lang");
		record.setRefId(Integer.valueOf(1));
		record.setRefName("refName");
		record.setBytes(new byte[]{'a','b','c'});
		contentService.insert(record);
	}

	public void testUpdate() {
		Content record = new Content();
		record.setId(Integer.valueOf(103));
		record.setFileName("fileName-updated");
		record.setContentType("contentType-updated");
		record.setCatagory("catagory-updated");
		record.setLang("lang-updated");
		record.setRefId(Integer.valueOf(1));
		record.setRefName("refName-updated");
		record.setBytes(new byte[]{'a','b','c'});
		contentService.update(record, true);
	}

	public void testDelete() {
		contentService.delete(Integer.valueOf(101));
	}

	public void testFind() {
		ContentSearch search = new ContentSearch();
		search.setFileName("file_name_page");
		search.setContentType("content_type_page");
		search.setCatagory("catagory_page");
		search.setLang("lang_page");
		search.setRefId(Integer.valueOf(1));
		search.setRefName("ref_name_page");
		List<Content> find = contentService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		ContentSearch search = new ContentSearch();
		search.setFileName("file_name_page");
		search.setContentType("content_type_page");
		search.setCatagory("catagory_page");
		search.setLang("lang_page");
		search.setRefId(Integer.valueOf(1));
		search.setRefName("ref_name_page");
		Long count = contentService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		ContentSearch search = new ContentSearch();
		search.setFileName("file_name_page");
		search.setContentType("content_type_page");
		search.setCatagory("catagory_page");
		search.setLang("lang_page");
		search.setRefId(Integer.valueOf(1));
		search.setRefName("ref_name_page");
		Page page = new Page();
		List<Content> page2 = contentService.page(search, page);
		assertEquals(3, page2.size());
	}

}
