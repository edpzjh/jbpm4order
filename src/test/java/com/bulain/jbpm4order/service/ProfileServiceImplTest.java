package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.jbpm4order.model.Profile;
import com.bulain.jbpm4order.pojo.ProfileSearch;

public class ProfileServiceImplTest extends ServiceTestCase {
	private ProfileService profileService;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_profiles.xml");
		profileService = (ProfileService) applicationContext.getBean("profileService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(ProfileServiceImplTest.class);
	}


	public void testFind() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		List<Profile> find = profileService.find(search);
		assertEquals(3, find.size());
	}

	public void testCount() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		Long count = profileService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		Page page = new Page();
		List<Profile> page2 = profileService.page(search, page);
		assertEquals(3, page2.size());
	}

	public void testGet() {
		Profile profile = profileService.get(Integer.valueOf(102));
		assertNotNull(profile);
		assertEquals(Integer.valueOf(102), profile.getPersonId());
		assertEquals("language_102", profile.getLanguage());
		assertEquals("country_102", profile.getCountry());
	}

	public void testInsert() {
		Profile record = new Profile();
		record.setPersonId(Integer.valueOf(108));
		record.setLanguage("language");
		record.setCountry("country");
		profileService.insert(record);
	}

	public void testUpdate() {
		Profile record = new Profile();
		record.setId(Integer.valueOf(103));
		record.setPersonId(Integer.valueOf(103));
		record.setLanguage("language-updated");
		record.setCountry("country-updated");
		profileService.update(record, true);
	}

	public void testDelete() {
		profileService.delete(Integer.valueOf(101));
	}

}
