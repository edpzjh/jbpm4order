package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.pojo.LoginSearch;

public interface LoginMapper extends PagedMapper<Login, LoginSearch> {
    Long countLoginByGroupId(Long groupId);
    List<Login> findLoginByGroupId(Long groupId);
    List<Login> findLoginByNoGroupId(Long groupId);
    List<Login> findLoginByLoginNames(String[] loginName);
}