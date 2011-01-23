package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Profile;
import com.bulain.jbpm4order.pojo.ProfileSearch;

public class ProfileMapperTest extends ServiceTestCase {
	private ProfileMapper profileMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_profiles.xml");
		profileMapper = (ProfileMapper) applicationContext.getBean("profileMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(ProfileMapperTest.class);
	}

	public void testFind() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		List<Profile> find = profileMapper.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		Long count = profileMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		Page page = new Page();
		page.setCount(10);
		search.setHigh(page.getHigh());
		search.setLow(page.getLow());
		List<Profile> page2 = profileMapper.page(search);
		assertEquals(3, page2.size());
	}

	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = profileMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		Profile record = new Profile();
		record.setPersonId(Integer.valueOf(108));
		record.setLanguage("language");
		record.setCountry("country");
		int insert = profileMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		Profile record = new Profile();
		record.setPersonId(Integer.valueOf(108));
		record.setLanguage("language");
		record.setCountry("country");
		int insertSelective = profileMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

	public void testSelectByPrimaryKey() {
		Profile selectByPrimaryKey = profileMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertNotNull(selectByPrimaryKey);
		assertEquals(Integer.valueOf(102), selectByPrimaryKey.getPersonId());
		assertEquals("language_102", selectByPrimaryKey.getLanguage());
		assertEquals("country_102", selectByPrimaryKey.getCountry());
	}

	public void testUpdateByPrimaryKeySelective() {
		Profile record = new Profile();
		record.setId(Integer.valueOf(103));
		record.setPersonId(Integer.valueOf(103));
		record.setLanguage("language-updated");
		record.setCountry("country-updated");
		int updateByPrimaryKeySelective = profileMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		Profile record = new Profile();
		record.setId(Integer.valueOf(104));
		record.setPersonId(Integer.valueOf(104));
		record.setLanguage("language-updated");
		record.setCountry("country-updated");
		int updateByPrimaryKey = profileMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

}
