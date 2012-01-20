package com.bulain.jbpm4order.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.GroupLoginMapper;
import com.bulain.jbpm4order.dao.GroupMapper;
import com.bulain.jbpm4order.dao.LoginMapper;
import com.bulain.jbpm4order.dao.PermissionMapper;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.model.GroupLogin;
import com.bulain.jbpm4order.model.Login;
import com.bulain.jbpm4order.model.Permission;
import com.bulain.jbpm4order.pojo.LoginSearch;

public class LoginServiceImpl extends PagedServiceImpl<Login, LoginSearch> implements LoginService {
    private LoginMapper loginMapper;
    private GroupLoginMapper groupLoginMapper;
    private PermissionMapper permissionMapper;
    private GroupMapper groupMapper;

    @Override
    protected PagedMapper<Login, LoginSearch> getPagedMapper() {
        return loginMapper;
    }

    // GroupLogin
    public Long countLoginByGroupId(Integer groupId) {
        return loginMapper.countLoginByGroupId(groupId);
    }
    public List<Login> findLoginByGroupId(Integer groupId) {
        return loginMapper.findLoginByGroupId(groupId);
    }
    public List<Login> findLoginByNoInGroupId(Integer groupId) {
        return loginMapper.findLoginByNoGroupId(groupId);
    }
    public void updateGroupLogin(Integer loginId, List<Integer> listGroupId) {
        LoginSearch search = new LoginSearch();
        search.setLoginId(loginId);
        search.setListGroupId(listGroupId);
        groupLoginMapper.deleteGroupLoginByNotInGroupId(search);

        List<Group> listGroup = groupMapper.findGroupByLoginId(loginId);
        List<Integer> listId = new ArrayList<Integer>();
        for (Group group : listGroup) {
            listId.add(group.getId());
        }

        for (Integer groupId : listGroupId) {
            if (listId.contains(groupId)) {
                continue;
            }
            GroupLogin gl = new GroupLogin();
            gl.setLoginId(loginId);
            gl.setGroupId(groupId);
            groupLoginMapper.insert(gl);
        }
    }

    public Login getLogin(String loginName, String password) {
        LoginSearch search = new LoginSearch();
        search.setLoginName(loginName);
        List<Login> list = loginMapper.find(search);
        if (list.size() > 0) {
            Login login = list.get(0);
            if (login.getHashedPassword().equals(password)) {
                return login;
            }
        }
        return null;
    }

    public List<String> findPermission(Integer loginId) {
        Set<String> setPermission = new HashSet<String>();
        List<Group> listGroup = groupMapper.findGroupByLoginId(loginId);
        for (Group grp : listGroup) {
            List<Permission> listPermission = permissionMapper.findPermissionByGroupId(grp.getId());
            for (Permission permission : listPermission) {
                setPermission.add(permission.getPermission());
            }
        }
        return new ArrayList<String>(setPermission);
    }

    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public void setGroupLoginMapper(GroupLoginMapper groupLoginMapper) {
        this.groupLoginMapper = groupLoginMapper;
    }

    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

}
