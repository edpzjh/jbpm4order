package com.bulain.jbpm4order.dao;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.jbpm4order.model.Group;
import com.bulain.jbpm4order.pojo.GroupSearch;

public class GroupMapperTest extends ServiceTestCase {
    @Autowired
	private GroupMapper groupMapper;
	
    @BeforeTransaction
	public void setUp() throws Exception {
		super.setUpDB("test-data/init_groups.xml");
	}

    @AfterTransaction
	public void tearDown() throws Exception {
		super.tearDownDB();
	}
	
    @Test
	public void testFindGroupByLoginId() {
		List<Group> findGroupByLoginId = groupMapper.findGroupByLoginId(Integer.valueOf(105));
		assertEquals(3, findGroupByLoginId.size());
	}

    @Test
	public void testFindGroupByNoLoginId() {
		List<Group> findGroupByNoLoginId = groupMapper.findGroupByNoLoginId(Integer.valueOf(105));
		assertEquals(4, findGroupByNoLoginId.size());
	}

    @Test
	public void testDeleteByPrimaryKey() {
		int deleteByPrimaryKey = groupMapper.deleteByPrimaryKey(Integer.valueOf(101));
		assertEquals(1, deleteByPrimaryKey);
	}

    @Test
	public void testInsert() {
		Group record = new Group();
		record.setName("name");
		record.setNote("note");
		int insert = groupMapper.insert(record);
		assertEquals(1, insert);
	}

    @Test
	public void testInsertSelective() {
		Group record = new Group();
		record.setName("name");
		record.setNote("note");
		int insertSelective = groupMapper.insertSelective(record);
		assertEquals(1, insertSelective);
	}

    @Test
	public void testSelectByPrimaryKey() {
		Group select = groupMapper.selectByPrimaryKey(Integer.valueOf(102));
		assertEquals("name_102", select.getName());
		assertEquals("note_102", select.getNote());
	}

    @Test
	public void testUpdateByPrimaryKeySelective() {
		Group record = new Group();
		record.setId(Integer.valueOf(103));
		record.setName("name");
		record.setNote("note");
		int updateByPrimaryKeySelective = groupMapper.updateByPrimaryKeySelective(record);
		assertEquals(1, updateByPrimaryKeySelective);
	}

    @Test
	public void testUpdateByPrimaryKey() {
		Group record = new Group();
		record.setId(Integer.valueOf(103));
		record.setName("name");
		record.setNote("note");
		int updateByPrimaryKey = groupMapper.updateByPrimaryKey(record);
		assertEquals(1, updateByPrimaryKey);
	}

    @Test
	public void testFind() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		List<Group> find = groupMapper.find(search);
		assertEquals(3, find.size());
	}

    @Test
	public void testCount() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		Long count = groupMapper.count(search);
		assertEquals(Long.valueOf(3), count);
	}

    @Test
	public void testPage() {
		GroupSearch search = new GroupSearch();
		search.setName("name_page");
		Page page = new Page();
		page.setCount(10);
		search.setHigh(page.getHigh());
		search.setLow(page.getLow());
		List<Group> page2 = groupMapper.page(search);
		assertEquals(3, page2.size());
	}

}
