package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.pojo.ReferanceView;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class ReferanceActionTest extends ActionTestCase {
    @Before
    public void setUp() throws Exception {
        super.setUp();
        super.setUpAction("admin", "admin");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDownAction();
        super.tearDown();
    }

    @Test
    public void testCURD() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/referance/new");
        ReferanceAction referanceAction = (ReferanceAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("referanceBean.name", "name");
        request.setParameter("referanceBean.code", "code");
        request.setParameter("referanceBean.textEN", "textEN");
        request.setParameter("referanceBean.textCN", "textCN");
        request.setParameter("referanceBean.catagory", "catagory");

        proxy = getActionProxy("/referance/create");
        referanceAction = (ReferanceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/referance/list");
        referanceAction = (ReferanceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<ReferanceView> listReferance = referanceAction.getListReferance();
        assertEquals(2, listReferance.size());
        Page page = referanceAction.getPage();
        assertEquals(1, page.getPage());

        Integer idEN = listReferance.get(0).getId();
        Integer idCN = listReferance.get(1).getId();

        initServletMockObjects();
        request.setParameter("id", Integer.toString(idEN));
        proxy = getActionProxy("/referance/edit");
        referanceAction = (ReferanceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Referance referance = referanceAction.getReferance();
        assertEquals("name", referance.getName());
        assertEquals("code", referance.getCode());
        assertEquals("textEN", referance.getText());
        assertEquals("en", referance.getLang());
        assertEquals("catagory", referance.getCatagory());

        initServletMockObjects();
        request.setParameter("referance.id", Integer.toString(idEN));
        request.setParameter("referance.name", "name-updated");
        request.setParameter("referance.code", "code-updated");
        request.setParameter("referance.text", "textEN-updated");
        request.setParameter("referance.lang", "en");
        request.setParameter("referance.catagory", "catagory-updated");
        proxy = getActionProxy("/referance/update");
        referanceAction = (ReferanceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Integer.toString(idEN));
        proxy = getActionProxy("/referance/show");
        referanceAction = (ReferanceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        referance = referanceAction.getReferance();
        assertEquals("name-updated", referance.getName());
        assertEquals("code-updated", referance.getCode());
        assertEquals("textEN-updated", referance.getText());
        assertEquals("en", referance.getLang());
        assertEquals("catagory-updated", referance.getCatagory());

        initServletMockObjects();
        request.setParameter("id", Integer.toString(idEN));
        proxy = getActionProxy("/referance/destroy");
        referanceAction = (ReferanceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Integer.toString(idCN));
        proxy = getActionProxy("/referance/destroy");
        referanceAction = (ReferanceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}
