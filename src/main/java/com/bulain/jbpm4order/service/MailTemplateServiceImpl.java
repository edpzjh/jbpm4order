package com.bulain.jbpm4order.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.exception.NotFoundException;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.MailTemplateMapper;
import com.bulain.jbpm4order.model.MailTemplate;
import com.bulain.jbpm4order.pojo.MailTemplateSearch;

public class MailTemplateServiceImpl extends PagedServiceImpl<MailTemplate, MailTemplateSearch>
        implements
            MailTemplateService {
    private MailTemplateMapper mailTemplateMapper;

    @Override
    protected PagedMapper<MailTemplate, MailTemplateSearch> getPagedMapper() {
        return mailTemplateMapper;
    }

    public MailTemplate getWithoutBLOBs(Long id) {
        MailTemplate selectByPrimaryKeyWithoutBLOBs = mailTemplateMapper.selectByPrimaryKeyWithoutBLOBs(id);
        if (selectByPrimaryKeyWithoutBLOBs == null) {
            throw new NotFoundException();
        }
        return selectByPrimaryKeyWithoutBLOBs;
    }

    public void setMailTemplateMapper(MailTemplateMapper mailTemplateMapper) {
        this.mailTemplateMapper = mailTemplateMapper;
    }

}
