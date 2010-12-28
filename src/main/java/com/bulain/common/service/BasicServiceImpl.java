package com.bulain.common.service;

import com.bulain.common.dao.BasicMapper;
import com.bulain.common.exception.NotFoundException;
import com.bulain.common.exception.ServiceException;


public abstract class BasicServiceImpl<T> implements BasicService<T>{
	protected abstract BasicMapper<T> getBasicMapper();
	
	public T get(Integer id){
		T selectByPrimaryKey = getBasicMapper().selectByPrimaryKey(id);
		if(selectByPrimaryKey==null) throw new NotFoundException();
		return selectByPrimaryKey;
	}
	public void insert(T record){
		int count = getBasicMapper().insert(record);
		if(count!=1)throw new ServiceException();
	}
	public void update(T record, boolean forced){
		int count = 0;
		if(forced){
			count = getBasicMapper().updateByPrimaryKey(record);
		}else{
			count = getBasicMapper().updateByPrimaryKeySelective(record);
		}
		if(count!=1)throw new ServiceException();
	}
	public void delete(Integer id){
		int count = getBasicMapper().deleteByPrimaryKey(id);
		if(count!=1) throw new ServiceException();
	}
}
