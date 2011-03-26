package com.bulain.jbpm4order.service;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Profile;
import com.bulain.jbpm4order.pojo.ProfileSearch;

public class ProfileServiceImplTest extends ServiceTestCase {
    @Autowired
	private ProfileService profileService;
	
    @BeforeTransaction
	public void setUpDB() throws Exception {
		super.setUpDB("test-data/init_profiles.xml");
	}

    @AfterTransaction
	public void tearDownDB() throws Exception {
		super.tearDownDB();
	}
	
    @Test
	public void testFind() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		List<Profile> find = profileService.find(search);
		assertEquals(3, find.size());
	}

    @Test
	public void testCount() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		Long count = profileService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

    @Test
	public void testPage() {
		ProfileSearch search = new ProfileSearch();
		search.setPersonId(Integer.valueOf(105));
		search.setLanguage("language_page");
		search.setCountry("country_page");
		Page page = new Page();
		List<Profile> page2 = profileService.page(search, page);
		assertEquals(3, page2.size());
	}

    @Test
	public void testGet() {
		Profile profile = profileService.get(Integer.valueOf(102));
		assertNotNull(profile);
		assertEquals(Integer.valueOf(102), profile.getPersonId());
		assertEquals("language_102", profile.getLanguage());
		assertEquals("country_102", profile.getCountry());
	}

    @Test
	public void testInsert() {
		Profile record = new Profile();
		record.setPersonId(Integer.valueOf(108));
		record.setLanguage("language");
		record.setCountry("country");
		profileService.insert(record);
	}

    @Test
	public void testUpdate() {
		Profile record = new Profile();
		record.setId(Integer.valueOf(103));
		record.setPersonId(Integer.valueOf(103));
		record.setLanguage("language-updated");
		record.setCountry("country-updated");
		profileService.update(record, true);
	}

    @Test
	public void testDelete() {
		profileService.delete(Integer.valueOf(101));
	}

}
