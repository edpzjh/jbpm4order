package com.bulain.jbpm4order.pojo;

import java.io.Serializable;

public class Master implements Serializable{
	private static final long serialVersionUID = 999925334992927320L;

	public static final Master DEFUALT_MASTER = new Master(null,"");
	
	private Integer key;
	private String value;
	
	public Master(){};
	
	public Master(Integer key, String value){
		this.key = key;
		this.value = value;
	}
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
