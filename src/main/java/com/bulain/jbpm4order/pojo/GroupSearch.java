package com.bulain.jbpm4order.pojo;

import java.util.List;

import com.bulain.common.page.Search;

public class GroupSearch extends Search {
    private static final long serialVersionUID = 8241714401190805669L;

    private String name;

    private Integer loginId;
    private Integer groupId;
    private List<Integer> listLoginId;
    private List<String> listPermission;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Integer> getListLoginId() {
        return listLoginId;
    }
    public void setListLoginId(List<Integer> listLoginId) {
        this.listLoginId = listLoginId;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public List<String> getListPermission() {
        return listPermission;
    }
    public void setListPermission(List<String> listPermission) {
        this.listPermission = listPermission;
    }
    public Integer getLoginId() {
        return loginId;
    }
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

}
