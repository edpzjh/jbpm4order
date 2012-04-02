package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public interface GroupService extends PagedService<Group, GroupSearch> {
    // GroupLogin
    List<Group> findGroupByLoginId(Long loginId);
    List<Group> findGroupByNoLoginId(Long loginId);
    void updateGroupLogin(Long groupId, List<Long> listLoginId);

    // GroupPermission
    List<Permission> findPermissionByGroupId(Long groupId);
    List<Permission> findPermissionByNoGroupId(Long groupId);
    void updateGroupPermission(Long groupId, List<String> listPermission);
}