package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.pojo.LoginSearch;

public interface LoginService extends PagedService<LoginSearch, Login> {
    // GroupLogin
    Long countLoginByGroupId(Integer groupId);
    List<Login> findLoginByGroupId(Integer groupId);
    List<Login> findLoginByNoInGroupId(Integer groupId);
    void updateGroupLogin(Integer loginId, List<Integer> listGroupId);

    Login getLogin(String loginName, String password);
    List<String> findPermission(Integer loginId);
}
