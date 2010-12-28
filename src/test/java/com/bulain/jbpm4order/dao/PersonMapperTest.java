package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.ServiceTestCase;
import com.bulain.jbpm4order.model.Person;
import com.bulain.jbpm4order.pojo.PersonSearch;

public class PersonMapperTest extends ServiceTestCase {
	private PersonMapper personMapper;
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpDB("test-data/init_persons.xml");
		personMapper = (PersonMapper) applicationContext.getBean("personMapper");
	}

	protected void tearDown() throws Exception {
		super.tearDownDB();
		super.tearDown();
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(PersonMapperTest.class);
	}
	
	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = personMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

	public void testInsert() {
		Person record = new Person();
		record.setFirstName("firstName");
		record.setLastName("lastName");
		int insert = personMapper.insert(record);
		assertEquals(1, insert);
	}

	public void testInsertSelective() {
		Person record = new Person();
		record.setFirstName("firstName");
		record.setLastName("lastName");
		int insert = personMapper.insertSelective(record);
		assertEquals(1, insert);
	}

	public void testSelectByPrimaryKey() {
		Person selectByPrimaryKey = personMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertNotNull(selectByPrimaryKey);
		assertEquals("first_name_102", selectByPrimaryKey.getFirstName());
		assertEquals("last_name_102", selectByPrimaryKey.getLastName());
	}

	public void testUpdateByPrimaryKeySelective() {
		Person record = new Person();
		record.setId(Integer.valueOf(103));
		record.setFirstName("firstName-updated");
		record.setLastName("lastName-updated");
		int updateByPrimaryKeySelective = personMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

	public void testUpdateByPrimaryKey() {
		Person record = new Person();
		record.setId(Integer.valueOf(104));
		record.setFirstName("firstName-updated");
		record.setLastName("lastName-updated");
		int updateByPrimaryKey = personMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

	public void testFind(){
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		List<Person> find = personMapper.find(search);
		assertEquals(3, find.size());
	}
	
	public void testShoudExecuteWhenNoParam(){
		PersonSearch search = new PersonSearch();
		List<Person> find = personMapper.find(search);
		assertEquals(7, find.size());
	}
	
	public void testShoudExecuteWhenFirstNameIsNull(){
		PersonSearch search = new PersonSearch();
		search.setLastName("last_name_page");
		List<Person> find = personMapper.find(search);
		assertEquals(3, find.size());
	}
	
	public void testShoudExecuteWhenFirstNameIsNullStr(){
		PersonSearch search = new PersonSearch();
		search.setFirstName("");
		search.setLastName("last_name_page");
		List<Person> find = personMapper.find(search);
		assertEquals(3, find.size());
	}
	
	public void testShoudExecuteWhenLastNameIsNull(){
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		List<Person> find = personMapper.find(search);
		assertEquals(3, find.size());
	}
	
	public void testShoudExecuteWhenLastNameIsNullStr(){
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		List<Person> find = personMapper.find(search);
		assertEquals(3, find.size());
	}
	
	public void testCount(){
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		Long count = personMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}
	
	public void testPage(){
		PersonSearch search = new PersonSearch();
		search.setFirstName("first_name_page");
		search.setLastName("last_name_page");
		search.setLow(0);
		search.setHigh(20);
		List<Person> listPerson = personMapper.page(search);
		assertEquals(3, listPerson.size());
	}

}
