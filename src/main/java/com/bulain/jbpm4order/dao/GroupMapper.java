package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.pojo.GroupSearch;

public interface GroupMapper extends PagedMapper<Group, GroupSearch> {
    List<Group> findGroupByLoginId(Long loginId);
    List<Group> findGroupByNoLoginId(Long loginId);
}