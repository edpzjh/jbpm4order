package com.bulain.jbpm4order.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;

import com.bulain.jbpm4order.pojo.Item;
import com.bulain.jbpm4order.test.JbpmTestCase;

public class SpringTest extends JbpmTestCase {
	private String deploymentId;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(SpringTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setUpDB("data/init_referances.xml");
		setUpJbpm();
		deploymentId = repositoryService.createDeployment()
				.addResourceFromClasspath("com/bulain/jbpm4order/workflow/spring.jpdl.xml")
				.deploy();
	}

	protected void tearDown() throws Exception {
		repositoryService.deleteDeploymentCascade(deploymentId);
		tearDownDB();
		tearDownJbpm();
		super.tearDown();
	}
	
	@SuppressWarnings("unchecked")
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
