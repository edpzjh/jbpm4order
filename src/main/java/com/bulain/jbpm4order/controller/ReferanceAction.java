package com.bulain.jbpm4order.controller;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.model.ReferanceBean;
import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.pojo.ItemConst;
import com.bulain.jbpm4order.pojo.ReferanceSearch;
import com.bulain.jbpm4order.pojo.ReferanceView;
import com.bulain.jbpm4order.service.ReferanceService;

public class ReferanceAction extends PageSupportActionSupport {
    private static final long serialVersionUID = -6586209271699101686L;

    private Long id;

    private ReferanceSearch search;
    private Referance referance;
    private ReferanceBean referanceBean;
    private List<ReferanceView> listReferance;

    private transient ReferanceService referanceService;

    private List<Item> listReferanceName;
    private List<Item> listReferanceLang;
    private List<Item> listReferanceCatagory;

    public String list() {
        search = (ReferanceSearch) getSearchFromSession(ReferanceSearch.class, search);
        page = getPageFromSession();

        List<Referance> list = referanceService.page(search, page);
        listReferance = formatList(list);

        putSearchToSession(ReferanceSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        referanceBean = new ReferanceBean();
        prepareEdit();
        return SUCCESS;
    }
    public String create() {
        referanceService.insert(referanceBean);
        return SUCCESS;
    }
    public String show() {
        referance = referanceService.get(id);
        referance = formatItem(referance);
        return SUCCESS;
    }
    public String edit() {
        referance = referanceService.get(id);
        prepareEdit();
        return SUCCESS;
    }
    public String update() {
        referanceService.update(referance, false);
        return SUCCESS;
    }
    public String destroy() {
        referanceService.delete(id);
        return SUCCESS;
    }

    public void validate() {
        super.validate();
        if (this.hasErrors()) {
            prepareEdit();
        }
    }
    public void prepareList() {
        listReferanceName = referanceService.findItem(ItemConst.NAME_REFERANCE, getLanguage());
        listReferanceLang = referanceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
        listReferanceCatagory = referanceService.findItem(ItemConst.NAME_CATAGORY, getLanguage());
    }
    public void prepareNewn() {
        prepareList();
    }
    public void prepareEdit() {
        prepareList();
    }

    protected List<ReferanceView> formatList(List<Referance> list) {
        List<ReferanceView> listView = new ArrayList<ReferanceView>();
        for (Referance ref : list) {
            listView.add(formatItem(ref));
        }
        return listView;
    }

    protected ReferanceView formatItem(Referance ref) {
        ReferanceView refv = new ReferanceView(ref);
        refv.setNameName(referanceService.getText(ItemConst.NAME_REFERANCE, refv.getName(), getLanguage()));
        refv.setLangName(referanceService.getText(ItemConst.NAME_LANGUAGE, refv.getLang(), getLanguage()));
        refv.setCatagoryName(referanceService.getText(ItemConst.NAME_CATAGORY, refv.getCatagory(), getLanguage()));
        return refv;
    }

    public ReferanceSearch getSearch() {
        return search;
    }

    public void setSearch(ReferanceSearch search) {
        this.search = search;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Referance getReferance() {
        return referance;
    }

    public void setReferance(Referance referance) {
        this.referance = referance;
    }

    public List<ReferanceView> getListReferance() {
        return listReferance;
    }

    public void setListReferance(List<ReferanceView> listReferance) {
        this.listReferance = listReferance;
    }

    public void setReferanceService(ReferanceService referanceService) {
        this.referanceService = referanceService;
    }

    public ReferanceBean getReferanceBean() {
        return referanceBean;
    }

    public void setReferanceBean(ReferanceBean referanceBean) {
        this.referanceBean = referanceBean;
    }

    public List<Item> getListReferanceName() {
        return listReferanceName;
    }

    public void setListReferanceName(List<Item> listReferanceName) {
        this.listReferanceName = listReferanceName;
    }

    public List<Item> getListReferanceLang() {
        return listReferanceLang;
    }

    public void setListReferanceLang(List<Item> listReferanceLang) {
        this.listReferanceLang = listReferanceLang;
    }

    public List<Item> getListReferanceCatagory() {
        return listReferanceCatagory;
    }

    public void setListReferanceCatagory(List<Item> listReferanceCatagory) {
        this.listReferanceCatagory = listReferanceCatagory;
    }

}
