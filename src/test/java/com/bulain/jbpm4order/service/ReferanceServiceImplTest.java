package com.bulain.jbpm4order.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.model.ReferanceBean;
import com.bulain.jbpm4order.pojo.Item;

@DataSet(file = "test-data/init_referances.xml")
public class ReferanceServiceImplTest extends ServiceTestCase {
    @Autowired
    private ReferanceService referanceService;

    @Test
    public void testDelete() {
        referanceService.delete(Long.valueOf(101));
    }

    @Test
    public void testGetLong() {
        Referance referance = referanceService.get(Long.valueOf(102));
        assertNotNull(referance);
        assertEquals("name_102", referance.getName());
        assertEquals("code_102", referance.getCode());
        assertEquals("text_102", referance.getText());
        assertEquals("lang_102", referance.getLang());
        assertEquals("catagory_102", referance.getCatagory());
    }

    @Test
    public void testInsertReferance() {
        Referance record = new Referance();
        record.setName("name");
        record.setCode("code");
        record.setLang("lang");
        record.setCatagory("catagory");
        record.setText("text");
        referanceService.insert(record);
    }

    @Test
    public void testInsertReferanceBean() {
        ReferanceBean referanceBean = new ReferanceBean();
        referanceBean.setName("name");
        referanceBean.setCode("code");
        referanceBean.setCatagory("catagory");
        referanceBean.setTextEN("textEN");
        referanceBean.setTextEN("textEN");
        referanceService.insert(referanceBean);
    }

    @Test
    public void testUpdateReferanceBoolean() {
        Referance record = new Referance();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setCode("code-updated");
        record.setLang("lang-updated");
        record.setCatagory("catagory-updated");
        record.setText("text-updated");
        referanceService.update(record, true);
    }

    @Test
    public void testGetTextStringStringString() {
        String text = referanceService.getText("name_102", "code_102", "lang_102");
        assertEquals("", text);
    }

    @Test
    public void testGetTextStringStringStringString() {
        String text = referanceService.getText("name_102", "code_102", "lang_102", "catagory_102");
        assertEquals("text_102", text);
    }

    @Test
    public void testFindItemStringString() {
        List<Item> findItem = referanceService.findItem("name_page", "lang_page");
        assertEquals(1, findItem.size());
    }

    @Test
    public void testFindItemStringStringString() {
        List<Item> findItem = referanceService.findItem("name_page", "lang_page", "catagory_page");
        assertEquals(4, findItem.size());
    }

}
