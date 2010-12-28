package com.bulain.jbpm4order.identity;

import org.jbpm.api.identity.User;

public class UserImpl implements User {
	protected String id;
	protected String givenName;
	protected String familyName;
	protected String businessEmail;

	public UserImpl(String id, String givenName, String familyName) {
	    this.id = id;
	    this.givenName = givenName;
	    this.familyName = familyName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
}
