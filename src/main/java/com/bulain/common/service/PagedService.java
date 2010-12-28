package com.bulain.common.service;

import java.util.List;

import com.bulain.common.page.Page;
import com.bulain.common.page.Search;

public interface PagedService<S extends Search,T> extends BasicService<T>{
	List<T> find(S search);
    Long count(S search);
    List<T> page(S search, Page page);
}
