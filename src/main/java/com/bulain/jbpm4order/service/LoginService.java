package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.pojo.LoginSearch;

public interface LoginService extends PagedService<Login, LoginSearch> {
    // GroupLogin
    Long countLoginByGroupId(Long groupId);
    List<Login> findLoginByGroupId(Long groupId);
    List<Login> findLoginByNoInGroupId(Long groupId);
    void updateGroupLogin(Long loginId, List<Long> listGroupId);

    Login getLogin(String loginName, String password);
    List<String> findPermission(Long loginId);
}
