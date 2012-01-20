package com.bulain.jbpm4order.service;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.MailTemplate;
import com.bulain.jbpm4order.pojo.MailTemplateSearch;

public interface MailTemplateService extends PagedService<MailTemplate, MailTemplateSearch> {
    MailTemplate getWithoutBLOBs(Integer id);
}
