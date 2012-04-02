package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.PermissionSearch;

public interface PermissionMapper extends PagedMapper<Permission, PermissionSearch> {
    List<Permission> findPermissionByGroupId(Long groupId);
    List<Permission> findPermissionByNoGroupId(Long groupId);
}