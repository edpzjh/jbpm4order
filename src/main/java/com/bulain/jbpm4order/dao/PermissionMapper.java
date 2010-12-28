package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.PermissionSearch;

public interface PermissionMapper extends PagedMapper<PermissionSearch, Permission>{
	List<Permission> findPermissionByGroupId(Integer groupId);
    List<Permission> findPermissionByNoGroupId(Integer groupId);
}