package com.bulain.jbpm4order.service;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Person;
import com.bulain.jbpm4order.pojo.PersonSearch;

public class PersonServiceImplTest extends ServiceTestCase {
    @Autowired
	private PersonService personService;

    @BeforeTransaction    
	public void setUpDB() throws Exception {
		super.setUpDB("test-data/init_persons.xml");
	}

    @AfterTransaction
	public void tearDownDB() throws Exception {
		super.tearDownDB();
	}

    @Test
	public void testFind() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		List<Person> find = personService.find(search); 
		assertEquals(3, find.size());
	}

    @Test
	public void testCount() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Long count = personService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

    @Test
	public void testPage() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Page page = new Page();
		List<Person> page2 = personService.page(search, page);
		assertEquals(3, page2.size());
	}

    @Test
	public void testGet() {
		Person person = personService.get(Integer.valueOf(102));
		assertNotNull(person);
		assertEquals("first_name_102", person.getFirstName());
		assertEquals("last_name_102", person.getLastName());
	}

    @Test
	public void testInsert() {
		Person record = new Person();
		record.setFirstName("firstName");
		record.setLastName("lastName");
		personService.insert(record);
	}

    @Test
	public void testUpdate() {
		Person record = new Person();
		record.setId(Integer.valueOf(103));
		record.setFirstName("firstName-updated");
		record.setLastName("lastName-updated");
		personService.update(record, true);
	}

    @Test
	public void testDelete() {
		personService.delete(Integer.valueOf(101));
	}

}
