package com.bulain.jbpm4order.dao;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.Content;
import com.bulain.jbpm4order.pojo.ContentSearch;

public interface ContentMapper extends PagedMapper<Content, ContentSearch> {
    int updateByPrimaryKeyWithBLOBs(Content record);
    Content selectByPrimaryKeyWithoutBLOBs(Integer id);
}