package com.bulain.jbpm4order.dao;

import com.bulain.common.dao.BasicMapper;
import com.bulain.jbpm4order.model.GroupLogin;
import com.bulain.jbpm4order.pojo.GroupSearch;
import com.bulain.jbpm4order.pojo.LoginSearch;

public interface GroupLoginMapper extends BasicMapper<GroupLogin> {
    int deleteGroupLoginByLoginId(Long loginId);
    int deleteGroupLoginByNotInLoginId(GroupSearch search);

    int deleteGroupLoginByGroupId(Long groupId);
    int deleteGroupLoginByNotInGroupId(LoginSearch search);

    int deleteGroupLogin(LoginSearch search);
}