package com.bulain.jbpm4order.pojo;

import java.util.List;

import com.bulain.common.page.Search;

public class LoginSearch extends Search {
    private static final long serialVersionUID = -8765991056341544073L;

    private Integer groupId;
    private String loginName;
    private String email;
    private String enabled;

    private Integer loginId;
    private List<Integer> listGroupId;

    private List<String> listLoginName;

    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEnabled() {
        return enabled;
    }
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public Integer getLoginId() {
        return loginId;
    }
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }
    public List<Integer> getListGroupId() {
        return listGroupId;
    }
    public void setListGroupId(List<Integer> listGroupId) {
        this.listGroupId = listGroupId;
    }
    public List<String> getListLoginName() {
        return listLoginName;
    }
    public void setListLoginName(List<String> listLoginName) {
        this.listLoginName = listLoginName;
    }
}
