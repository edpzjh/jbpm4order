package com.bulain.jbpm4order.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.ProfileMapper;
import com.bulain.jbpm4order.model.Profile;
import com.bulain.jbpm4order.pojo.ProfileSearch;

public class ProfileServiceImpl extends PagedServiceImpl<Profile, ProfileSearch> implements ProfileService {
    private ProfileMapper profileMapper;

    @Override
    protected PagedMapper<Profile, ProfileSearch> getPagedMapper() {
        return profileMapper;
    }

    public void setProfileMapper(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }
}
