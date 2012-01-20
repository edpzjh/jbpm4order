package com.bulain.jbpm4order.service;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Authorize;
import com.bulain.jbpm4order.pojo.AuthorizeSearch;

public interface AuthorizeService extends PagedService<Authorize, AuthorizeSearch> {
    String getPermission(String controller, String action);
}
