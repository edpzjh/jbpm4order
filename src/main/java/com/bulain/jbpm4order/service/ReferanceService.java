package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.service.PagedService;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.model.ReferanceBean;
import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.pojo.ReferanceSearch;

public interface ReferanceService extends PagedService<Referance, ReferanceSearch> {
    void insert(ReferanceBean referanceBean);

    String getText(String name, String code, String lang);
    String getText(String name, String code, String lang, String catagory);

    List<Item> findItem(String name, String lang);
    List<Item> findItem(String name, String lang, String catagory);
}
