package com.bulain.jbpm4order.service;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.GroupLoginMapper;
import com.bulain.jbpm4order.dao.GroupMapper;
import com.bulain.jbpm4order.dao.GroupPermissionMapper;
import com.bulain.jbpm4order.dao.LoginMapper;
import com.bulain.jbpm4order.dao.PermissionMapper;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.model.GroupLogin;
import com.bulain.jbpm4order.model.GroupPermission;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.GroupSearch;

public class GroupServiceImpl extends PagedServiceImpl<GroupSearch, Group> implements GroupService {
    private GroupMapper groupMapper;
    private GroupLoginMapper groupLoginMapper;
    private GroupPermissionMapper groupPermissionMapper;
    private PermissionMapper permissionMapper;
    private LoginMapper loginMapper;

    @Override
    protected PagedMapper<GroupSearch, Group> getPagedMapper() {
        return groupMapper;
    }

    // GroupLogin
    public List<Group> findGroupByLoginId(Integer loginId) {
        return groupMapper.findGroupByLoginId(loginId);
    }
    public List<Group> findGroupByNoLoginId(Integer loginId) {
        return groupMapper.findGroupByNoLoginId(loginId);
    }
    public void updateGroupLogin(Integer groupId, List<Integer> listLoginId) {
        GroupSearch search = new GroupSearch();
        search.setGroupId(groupId);
        search.setListLoginId(listLoginId);
        groupLoginMapper.deleteGroupLoginByNotInLoginId(search);

        List<Login> listLogin = loginMapper.findLoginByGroupId(groupId);
        List<Integer> listId = new ArrayList<Integer>();
        for (Login l : listLogin) {
            listId.add(l.getId());
        }

        for (Integer loginId : listLoginId) {
            if (listId.contains(loginId)) {
                continue;
            }
            GroupLogin gl = new GroupLogin();
            gl.setLoginId(loginId);
            gl.setGroupId(groupId);
            groupLoginMapper.insert(gl);
        }
    }

    // GroupPermission
    public List<Permission> findPermissionByGroupId(Integer groupId) {
        return permissionMapper.findPermissionByGroupId(groupId);
    }
    public List<Permission> findPermissionByNoGroupId(Integer groupId) {
        return permissionMapper.findPermissionByNoGroupId(groupId);
    }

    public void updateGroupPermission(Integer groupId, List<String> listPermission) {
        GroupSearch search = new GroupSearch();
        search.setGroupId(groupId);
        search.setListPermission(listPermission);
        groupPermissionMapper.deleteGroupPermissionByNotInPermission(search);

        List<Permission> listTemp = permissionMapper.findPermissionByGroupId(groupId);
        List<String> listStr = new ArrayList<String>();
        for (Permission gp : listTemp) {
            listStr.add(gp.getPermission());
        }

        for (String permission : listPermission) {
            if (listStr.contains(permission)) {
                continue;
            }
            GroupPermission gp = new GroupPermission();
            gp.setGroupId(groupId);
            gp.setPermission(permission);
            groupPermissionMapper.insert(gp);
        }
    }

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public void setGroupLoginMapper(GroupLoginMapper groupLoginMapper) {
        this.groupLoginMapper = groupLoginMapper;
    }

    public void setGroupPermissionMapper(GroupPermissionMapper groupPermissionMapper) {
        this.groupPermissionMapper = groupPermissionMapper;
    }

    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
}