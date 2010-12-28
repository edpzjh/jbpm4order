package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.jbpm4order.model.Content;
import com.bulain.jbpm4order.pojo.ContentSearch;

public class ContentMapperTest extends ServiceTestCase {
	private ContentMapper contentMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_contents.xml");
		contentMapper = (ContentMapper) applicationContext.getBean("contentMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(ContentMapperTest.class);
	}

	public void testUpdateByPrimaryKeyWithBLOBs() {
		Content record = new Content();
		record.setId(Integer.valueOf(108));
		record.setFileName("fileName-updated");
		record.setContentType("contentType-updated");
		record.setCatagory("catagory-updated");
		record.setLang("lang-updated");
		record.setRefId(Integer.valueOf(1));
		record.setRefName("refName-updated");
		record.setBytes(new byte[]{'a','b','c'});
		int updateByPrimaryKey = contentMapper.updateByPrimaryKey(record );
		assertEquals(1, updateByPrimaryKey);
	}

	public void testSelectByPrimaryKeyWithoutBLOBs() {
		Content select = contentMapper.selectByPrimaryKeyWithoutBLOBs(Integer.valueOf(109));
		assertEquals("file_name_109", select.getFileName());
		assertEquals("content_type_109", select.getContentType());
		assertEquals("catagory_109", select.getCatagory());
		assertEquals("lang_109", select.getLang());
		assertEquals(Integer.valueOf(109), select.getRefId());
		assertEquals("ref_name_109", select.getRefName());
		assertNull(select.getBytes());
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = contentMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
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
		int insert = contentMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		Content record = new Content();
		record.setFileName("fileName");
		record.setContentType("contentType");
		record.setCatagory("catagory");
		record.setLang("lang");
		record.setRefId(Integer.valueOf(1));
		record.setRefName("refName");
		record.setBytes(new byte[]{'a','b','c'});
		int insertSelective = contentMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

	public void testSelectByPrimaryKey() {
		Content select = contentMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertEquals("file_name_102", select.getFileName());
		assertEquals("content_type_102", select.getContentType());
		assertEquals("catagory_102", select.getCatagory());
		assertEquals("lang_102", select.getLang());
		assertEquals(Integer.valueOf(102), select.getRefId());
		assertEquals("ref_name_102", select.getRefName());
		assertNull(select.getBytes());
	}

	public void testUpdateByPrimaryKeySelective() {
		Content record = new Content();
		record.setId(Integer.valueOf(103));
		record.setFileName("fileName-updated");
		record.setContentType("contentType-updated");
		record.setCatagory("catagory-updated");
		record.setLang("lang-updated");
		record.setRefId(Integer.valueOf(1));
		record.setRefName("refName-updated");
		record.setBytes(new byte[]{'a','b','c'});
		int updateByPrimaryKeySelective = contentMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		Content record = new Content();
		record.setId(Integer.valueOf(103));
		record.setFileName("fileName-updated");
		record.setContentType("contentType-updated");
		record.setCatagory("catagory-updated");
		record.setLang("lang-updated");
		record.setRefId(Integer.valueOf(1));
		record.setRefName("refName-updated");
		record.setBytes(new byte[]{'a','b','c'});
		int updateByPrimaryKey = contentMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

	public void testFind() {
		ContentSearch search = new ContentSearch();
		search.setFileName("file_name_page");
		search.setContentType("content_type_page");
		search.setCatagory("catagory_page");
		search.setLang("lang_page");
		search.setRefId(Integer.valueOf(1));
		search.setRefName("ref_name_page");
		List<Content> find = contentMapper.find(search);
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
		Long count = contentMapper.count(search);
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
		page.setCount(10);
		search.setHigh(page.getHigh());
		search.setLow(page.getLow());
		List<Content> page2 = contentMapper.page(search);
		assertEquals(3, page2.size());
	}

}
