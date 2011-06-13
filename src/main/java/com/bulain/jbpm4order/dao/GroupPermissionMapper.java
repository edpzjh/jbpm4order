package com.bulain.jbpm4order.dao;

import com.bulain.common.dao.BasicMapper;
import com.bulain.jbpm4order.model.GroupPermission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public interface GroupPermissionMapper extends BasicMapper<GroupPermission> {
    int deleteGroupPermissionByGroupId(Integer groupId);
    int deleteGroupPermissionByNotInPermission(GroupSearch search);
}