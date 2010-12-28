package com.bulain.common.cache;

import java.util.Collection;
import java.util.Map;

public interface CacheService {
	Object get(Class<?> clz, Integer id);
	Object get(Class<?> clz, String key);
	
	Map<String,Object> getByIds(Class<?> clz, Collection<Integer> ids);
	Map<String,Object> getByKeys(Class<?> clz, Collection<String> keys);
	
	boolean set(Class<?> clz, Integer id, Object obj);
	boolean set(Class<?> clz, Integer id, Object obj, int expire);
	boolean set(Class<?> clz, String key, Object obj);
	boolean set(Class<?> clz, String key, Object obj, int expire);
	
	boolean add(Class<?> clz, Integer id, Object obj);
	boolean add(Class<?> clz, Integer id, Object obj, int expire);
	boolean add(Class<?> clz, String key, Object obj);
	boolean add(Class<?> clz, String key, Object obj, int expire);
	
	boolean replace(Class<?> clz, Integer id, Object obj);
	boolean replace(Class<?> clz, Integer id, Object obj, int expire);
	boolean replace(Class<?> clz, String key, Object obj);
	boolean replace(Class<?> clz, String key, Object obj, int expire);
	
	boolean delete(Class<?> clz, Integer id);
	boolean delete(Class<?> clz, String key);
	
	void flushAll();
	void shutdown();
}
