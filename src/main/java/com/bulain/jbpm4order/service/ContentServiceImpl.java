package com.bulain.jbpm4order.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.exception.NotFoundException;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.ContentMapper;
import com.bulain.jbpm4order.model.Content;
import com.bulain.jbpm4order.pojo.ContentSearch;

public class ContentServiceImpl extends PagedServiceImpl<Content, ContentSearch> implements ContentService {
    private ContentMapper contentMapper;

    @Override
    protected PagedMapper<Content, ContentSearch> getPagedMapper() {
        return contentMapper;
    }

    public Content getWithoutBLOBs(Long id) {
        Content selectByPrimaryKeyWithoutBLOBs = contentMapper.selectByPrimaryKeyWithoutBLOBs(id);
        if (selectByPrimaryKeyWithoutBLOBs == null) {
            throw new NotFoundException();
        }
        return selectByPrimaryKeyWithoutBLOBs;
    }

    public void setContentMapper(ContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }
}
