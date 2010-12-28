package com.bulain.jbpm4order.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.PersonMapper;
import com.bulain.jbpm4order.model.Person;
import com.bulain.jbpm4order.pojo.PersonSearch;

public class PersonServiceImpl extends PagedServiceImpl<PersonSearch, Person> implements PersonService{
	private PersonMapper personMapper;
	
	@Override
	protected PagedMapper<PersonSearch, Person> getPagedMapper() {
		return personMapper;
	}

	public void setPersonMapper(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}
	
}
