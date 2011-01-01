package com.bulain.jbpm4order.controller;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.jbpm4order.model.Profile;
import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.pojo.ItemConst;
import com.bulain.jbpm4order.pojo.ProfileSearch;
import com.bulain.jbpm4order.pojo.ProfileView;
import com.bulain.jbpm4order.service.ProfileService;
import com.bulain.jbpm4order.service.ReferanceService;

public class ProfileAction extends PageSupportActionSupport{
	private static final long serialVersionUID = 239300271685223451L;

	private Integer id;
	
	private ProfileSearch search;
	private Profile profile;
	private List<ProfileView> listProfile;
	
	private transient ProfileService profileService;
	private transient ReferanceService referanceService;
	
	private List<Item> listReferanceLanguage;
	private List<Item> listReferanceCountry;
	
	public String list(){
		search = (ProfileSearch) getSearchFromSession(ProfileSearch.class, search);
		page = getPageFromSession();
		
		List<Profile> list = profileService.page(search, page);
		listProfile = formatList(list);
		
		putSearchToSession(ProfileSearch.class, search);
	    putPageToSession();
		
		return SUCCESS;
	}
	
	public String newn(){
		profile = new Profile();
		return SUCCESS;
	}
	public String create(){
		profileService.insert(profile);
		return SUCCESS;
	}
	public String show(){
		profile = profileService.get(id);
		profile = formatItem(profile);
		return SUCCESS;
	}
	public String edit(){
		profile = profileService.get(id);
		return SUCCESS;
	}
	public String update(){
		profileService.update(profile, false);
		return SUCCESS;
	}
	public String destroy(){
		profileService.delete(id);
		return SUCCESS;
	}
	
	public void prepareList() {
		listReferanceLanguage = referanceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
		listReferanceCountry = referanceService.findItem(ItemConst.NAME_COUNTRY, getLanguage());
	}
	public void prepareNewn(){
		prepareList();
	}
	public void prepareEdit(){
		prepareList();
	}
	
	protected List<ProfileView> formatList(List<Profile> list){
		List<ProfileView> listView = new ArrayList<ProfileView>();
		for(Profile ref : list){
			listView.add(formatItem(ref));
		}
		return listView;
	}
	
	protected ProfileView formatItem(Profile profile){
		ProfileView refv = new ProfileView(profile);
		refv.setLanguageName(referanceService.getText(ItemConst.NAME_LANGUAGE, refv.getLanguage(), getLanguage()));
		refv.setCountryName(referanceService.getText(ItemConst.NAME_COUNTRY, refv.getCountry(), getLanguage()));
		return refv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProfileSearch getSearch() {
		return search;
	}

	public void setSearch(ProfileSearch search) {
		this.search = search;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<ProfileView> getListProfile() {
		return listProfile;
	}

	public void setListProfile(List<ProfileView> listProfile) {
		this.listProfile = listProfile;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public List<Item> getListReferanceLanguage() {
		return listReferanceLanguage;
	}

	public void setListReferanceLanguage(List<Item> listReferanceLanguage) {
		this.listReferanceLanguage = listReferanceLanguage;
	}

	public List<Item> getListReferanceCountry() {
		return listReferanceCountry;
	}

	public void setListReferanceCountry(List<Item> listReferanceCountry) {
		this.listReferanceCountry = listReferanceCountry;
	}

	public void setReferanceService(ReferanceService referanceService) {
		this.referanceService = referanceService;
	}
	
}
