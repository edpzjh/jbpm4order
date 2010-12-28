package com.bulain.common.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCacheServiceImpl implements CacheService{
	private static final String COMAID = "_id_";
	private static final String COMAKEY = "_key_";
	
	protected String parseCacheKey(Class<?> clz, Integer id){
		return clz.getName() + COMAID + id;
	}
	protected String parseCacheKey(Class<?> clz, String key){
		return clz.getName() + COMAKEY + key;
	}
	protected Collection<String> parseCacheKeyById(Class<?> clz, Collection<Integer> ids){
		List<String> list = new ArrayList<String>();
		for(Integer id : ids){
			list.add(clz.getName() + COMAID + id);
		}
		return list;
	}
	protected Collection<String> parseCacheKeyByKey(Class<?> clz, Collection<String> keys){
		List<String> list = new ArrayList<String>();
		for(String key : keys){
			list.add(clz.getName() + COMAID + key);
		}
		return list;
	}
}
