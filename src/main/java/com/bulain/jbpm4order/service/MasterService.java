package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.jbpm4order.pojo.Master;

public interface MasterService {
    String getValue4Group(Long id);
    List<Master> findMaster4Group();
    String getValue4Person(Long id);
    List<Master> findMaster4Person();
}
