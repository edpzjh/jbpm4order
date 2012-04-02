package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.jbpm4order.pojo.Master;

public interface MasterMapper {
    List<Master> selectList4Group();
    Master selectMaster4Group(Long id);
    List<Master> selectList4Person();
    Master selectMaster4Person(Long id);
}
