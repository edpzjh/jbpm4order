package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.junit.Test;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.test.JbpmTestCase;

public class SpringTest extends JbpmTestCase {
	private String deploymentId;

	@BeforeTransaction
	public void setUp() throws Exception {
		deploymentId = repositoryService.createDeployment()
				.addResourceFromClasspath("com/bulain/jbpm4order/workflow/spring.jpdl.xml")
				.deploy();
	}

	@AfterTransaction
	public void tearDown() throws Exception {
		repositoryService.deleteDeploymentCascade(deploymentId);
	}

	@BeforeTransaction
	public void setUpDB() throws Exception {
	    super.setUpDB("data/init_referances.xml");
	}
	
	@AfterTransaction
	public void tearDownDB() throws Exception {
	    super.tearDownDB();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSpring(){
		Map<String, Object> variables = new HashMap<String, Object>(); 
		variables.put("name", "name");
		variables.put("lang", "lang");
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("spring", variables);
		String pid = processInstance.getId();
		
		List<Item> listItem = (List<Item>) executionService.getVariable(pid, "answer");
	    assertEquals(1, listItem.size());
	    
	    Execution execution = executionService.findExecutionById(pid);
	    assertEquals(true, execution.isActive("state1"));
	    
	    processInstance = executionService.signalExecutionById(pid);
	    assertEquals(true, processInstance.isEnded());
	}
}
