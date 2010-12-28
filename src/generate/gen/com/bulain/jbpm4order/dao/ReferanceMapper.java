package com.bulain.jbpm4order.dao;

import com.bulain.jbpm4order.model.Referance;

public interface ReferanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Referance record);

    int insertSelective(Referance record);

    Referance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Referance record);

    int updateByPrimaryKey(Referance record);
}