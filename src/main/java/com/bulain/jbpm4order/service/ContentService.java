package com.bulain.jbpm4order.service;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Content;
import com.bulain.jbpm4order.pojo.ContentSearch;

public interface ContentService extends PagedService<ContentSearch, Content>{
	Content getWithoutBLOBs(Integer id);
}
