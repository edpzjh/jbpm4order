package com.bulain.common.service;


public interface BasicService<T> {
	T get(Integer id);
	void insert(T record);
	void update(T record, boolean forced);
	void delete(Integer id);
}
