package com.bulain.jbpm4order.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.pojo.ReferanceSearch;

public interface ReferanceMapper extends PagedMapper<Referance, ReferanceSearch> {
    List<Item> selectListByExample(ReferanceSearch search);
    Item selectItemByExample(ReferanceSearch search);
}