package com.bulain.jbpm4order.service;

import java.util.List;

import com.bulain.common.cache.CacheService;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.jbpm4order.dao.ReferanceMapper;
import com.bulain.jbpm4order.model.Constants;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.model.ReferanceBean;
import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.pojo.ReferanceSearch;

public class ReferanceServiceImpl extends PagedServiceImpl<Referance, ReferanceSearch> implements ReferanceService {
    private static final String COMA = "__";

    private static final String DEFAULT_CATAGORY = "";
    private static final String DEFAULT_TEXT = "";

    private ReferanceMapper referanceMapper;
    private CacheService cacheService;

    @Override
    protected PagedMapper<Referance, ReferanceSearch> getPagedMapper() {
        return referanceMapper;
    }

    public Referance get(Long id) {
        Object object = cacheService.get(Referance.class, id);
        if (object == null) {
            object = super.get(id);
            cacheService.add(Referance.class, id, object);
        }
        return (Referance) object;
    }
    public void insert(Referance referance) {
        super.insert(referance);

        String key = referance.getName() + COMA + referance.getLang() + COMA + referance.getCatagory();
        cacheService.delete(Item.class, key);
    }
    public void insert(ReferanceBean referanceBean) {
        Referance beanEN = new Referance();
        Referance beanCN = new Referance();

        beanEN.setName(referanceBean.getName());
        beanEN.setCode(referanceBean.getCode());
        beanEN.setCatagory(referanceBean.getCatagory());
        beanEN.setLang(Constants.LANG_EN);
        beanEN.setText(referanceBean.getTextEN());

        beanCN.setName(referanceBean.getName());
        beanCN.setCode(referanceBean.getCode());
        beanCN.setCatagory(referanceBean.getCatagory());
        beanCN.setLang(Constants.LANG_ZH);
        beanCN.setText(referanceBean.getTextCN());

        super.insert(beanEN);
        super.insert(beanCN);

        String key = beanEN.getName() + COMA + beanEN.getLang() + COMA + beanEN.getCatagory();
        cacheService.delete(Item.class, key);

        key = beanCN.getName() + COMA + beanCN.getLang() + COMA + beanCN.getCatagory();
        cacheService.delete(Item.class, key);
    }

    public void update(Referance referance, boolean forced) {
        super.update(referance, forced);

        cacheService.delete(Referance.class, referance.getId());
        String key = referance.getName() + COMA + referance.getLang() + COMA + referance.getCatagory();
        cacheService.delete(Item.class, key);
        key = referance.getName() + COMA + referance.getCode() + COMA + referance.getLang() + COMA
                + referance.getCatagory();
        cacheService.delete(Item.class, key);
    }
    public void delete(Long id) {
        Referance referance = super.get(id);
        super.delete(id);

        cacheService.delete(Referance.class, id);
        String key = referance.getName() + COMA + referance.getLang() + COMA + referance.getCatagory();
        cacheService.delete(Item.class, key);
        key = referance.getName() + COMA + referance.getCode() + COMA + referance.getLang() + COMA
                + referance.getCatagory();
        cacheService.delete(Item.class, key);
    }

    public String getText(String name, String code, String lang) {
        return getText(name, code, lang, DEFAULT_CATAGORY);
    }
    public String getText(String name, String code, String lang, String catagory) {
        if (code == null || code.length() <= 0) {
            return DEFAULT_TEXT;
        }

        String key = name + COMA + code + COMA + lang + COMA + catagory;
        Item item = (Item) cacheService.get(Item.class, key);

        if (item == null) {
            ReferanceSearch search = new ReferanceSearch();
            search.setName(name);
            search.setCode(code);
            search.setLang(lang);
            search.setCatagory(catagory);
            item = referanceMapper.selectItemByExample(search);
            if (item != null) {
                cacheService.add(Item.class, key, item);
            }
        }

        if (item != null) {
            return item.getValue();
        }
        return DEFAULT_TEXT;
    }
    public List<Item> findItem(String name, String lang) {
        return findItem(name, lang, DEFAULT_CATAGORY);
    }

    public List<Item> findItem(String name, String lang, String catagory) {
        String key = name + COMA + lang + COMA + catagory;
        @SuppressWarnings("unchecked")
        List<Item> list = (List<Item>) cacheService.get(Item.class, key);

        if (list == null) {
            ReferanceSearch search = new ReferanceSearch();
            search.setName(name);
            search.setLang(lang);
            search.setCatagory(catagory);
            list = referanceMapper.selectListByExample(search);
            list.add(0, Item.DEFUALT_ITEM);
            cacheService.add(Item.class, key, list);
        }

        return list;
    }

    public void setReferanceMapper(ReferanceMapper referanceMapper) {
        this.referanceMapper = referanceMapper;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
}
