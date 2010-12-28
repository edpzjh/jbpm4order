package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.common.page.Page;
import com.bulain.jbpm4order.model.Person;
import com.bulain.jbpm4order.pojo.PersonSearch;

public class PersonServiceImplTest extends ServiceTestCase {
	private PersonService personService;

	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_persons.xml");
		personService = (PersonService) applicationContext.getBean("personService");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(PersonServiceImplTest.class);
	}

	public void testFind() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		List<Person> find = personService.find(search); 
		assertEquals(3, find.size());
	}

	public void testCount() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Long count = personService.count(search);
		assertEquals(Long.valueOf(3), count);
	}

	public void testPage() {
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Page page = new Page();
		List<Person> page2 = personService.page(search, page);
		assertEquals(3, page2.size());
	}

	public void testGet() {
		Person person = personService.get(Integer.valueOf(102));
		assertNotNull(person);
		assertEquals("first_name_102", person.getFirstName());
		assertEquals("last_name_102", person.getLastName());
	}

	public void testInsert() {
		Person record = new Person();
		record.setFirstName("firstName");
		record.setLastName("lastName");
		personService.insert(record);
	}

	public void testUpdate() {
		Person record = new Person();
		record.setId(Integer.valueOf(103));
		record.setFirstName("firstName-updated");
		record.setLastName("lastName-updated");
		personService.update(record, true);
	}

	public void testDelete() {
		personService.delete(Integer.valueOf(101));
	}

}
