package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public interface GroupService extends PagedService<GroupSearch, Group>{
    //GroupLogin
    List<Group> findGroupByLoginId(Integer loginId);
    List<Group> findGroupByNoLoginId(Integer loginId);
    void updateGroupLogin(Integer groupId, List<Integer> listLoginId);
    
    //GroupPermission
    List<Permission> findPermissionByGroupId(Integer groupId);
    List<Permission> findPermissionByNoGroupId(Integer groupId);
    void updateGroupPermission(Integer groupId, List<String> listPermission);
}