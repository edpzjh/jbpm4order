package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.jbpm4order.dao.MasterMapper;
import com.bulain.jbpm4order.pojo.Master;

public class MasterServiceImpl implements MasterService{
	private static final String DEFAULT_VALUE = "";
	private static final Master DEFAULT_MASTER = new Master(null, DEFAULT_VALUE);
	
	private MasterMapper masterMapper;
	
	public String getValue4Group(Integer id){
		Master master = masterMapper.selectMaster4Group(id);
		if(master!=null)return master.getValue();
		return DEFAULT_VALUE;
	}
    public List<Master> findMaster4Group(){
    	List<Master> list = masterMapper.selectList4Group();
    	list.add(0, DEFAULT_MASTER);
    	return list;
    }
    public String getValue4Person(Integer id){
    	Master master = masterMapper.selectMaster4Person(id);
		if(master!=null)return master.getValue();
		return DEFAULT_VALUE;
    }
    public List<Master> findMaster4Person(){
    	List<Master> list = masterMapper.selectList4Person();
    	list.add(0, DEFAULT_MASTER);
    	return list;
    }

    public void setMasterMapper(MasterMapper masterMapper) {
		this.masterMapper = masterMapper;
	}
}
