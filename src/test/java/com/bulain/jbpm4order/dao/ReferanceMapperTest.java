package com.bulain.jbpm4order.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Referance;
import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.pojo.ReferanceSearch;

@DataSet(file = "test-data/init_referances.xml")
public class ReferanceMapperTest extends ServiceTestCase {
    @Autowired
    private ReferanceMapper referanceMapper;

    @Test
    public void testSelectListByExample() {
        ReferanceSearch search = new ReferanceSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        List<Item> selectListByExample = referanceMapper.selectListByExample(search);
        assertEquals(3, selectListByExample.size());
    }

    @Test
    public void testSelectItemByExample() {
        ReferanceSearch search = new ReferanceSearch();
        search.setName("name_102");
        search.setCode("code_102");
        search.setLang("lang_102");
        search.setCatagory("catagory_102");
        Item selectItemByExample = referanceMapper.selectItemByExample(search);
        assertNotNull(selectItemByExample);
    }

    @Test
    public void testFind() {
        ReferanceSearch search = new ReferanceSearch();
        search.setName("name_page");
        search.setCode("code_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        List<Referance> find = referanceMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        ReferanceSearch search = new ReferanceSearch();
        search.setName("name_page");
        search.setCode("code_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        Long count = referanceMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        ReferanceSearch search = new ReferanceSearch();
        search.setName("name_page");
        search.setCode("code_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        Page page = new Page();
        page.setCount(10);
        search.setHigh(page.getHigh());
        search.setLow(page.getLow());
        List<Referance> page2 = referanceMapper.page(search);
        assertEquals(3, page2.size());
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = referanceMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Referance record = new Referance();
        record.setName("name");
        record.setCode("code");
        record.setLang("lang");
        record.setCatagory("catagory");
        record.setText("text");
        int insert = referanceMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Referance record = new Referance();
        record.setName("name");
        record.setCode("code");
        record.setLang("lang");
        record.setCatagory("catagory");
        record.setText("text");
        int insertSelective = referanceMapper.insertSelective(record);
        assertEquals(1, insertSelective);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Referance selectByPrimaryKey = referanceMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);
        assertEquals("name_102", selectByPrimaryKey.getName());
        assertEquals("code_102", selectByPrimaryKey.getCode());
        assertEquals("text_102", selectByPrimaryKey.getText());
        assertEquals("lang_102", selectByPrimaryKey.getLang());
        assertEquals("catagory_102", selectByPrimaryKey.getCatagory());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Referance record = new Referance();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setCode("code-updated");
        record.setLang("lang-updated");
        record.setCatagory("catagory-updated");
        record.setText("text-updated");
        int updateByPrimaryKeySelective = referanceMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Referance record = new Referance();
        record.setId(Long.valueOf(104));
        record.setName("name-updated");
        record.setCode("code-updated");
        record.setLang("lang-updated");
        record.setCatagory("catagory-updated");
        record.setText("text-updated");
        int updateByPrimaryKey = referanceMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

}
