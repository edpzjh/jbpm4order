package com.bulain.jbpm4order.dao;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.MailTemplate;
import com.bulain.jbpm4order.pojo.MailTemplateSearch;

public interface MailTemplateMapper extends PagedMapper<MailTemplateSearch, MailTemplate> {
    int updateByPrimaryKeyWithBLOBs(MailTemplate record);
    MailTemplate selectByPrimaryKeyWithoutBLOBs(Integer id);
}