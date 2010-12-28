package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.pojo.GroupSearch;

public interface GroupMapper extends PagedMapper<GroupSearch, Group>{
    List<Group> findGroupByLoginId(Integer loginId);
    List<Group> findGroupByNoLoginId(Integer loginId);
}