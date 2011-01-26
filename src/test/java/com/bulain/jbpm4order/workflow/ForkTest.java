package com.bulain.jbpm4order.workflow;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class ForkTest extends JbpmTestCase {
	String deploymentId;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ForkTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setUpJbpm();
		deploymentId = repositoryService.createDeployment()
				.addResourceFromClasspath("com/bulain/jbpm4order/workflow/fork.jpdl.xml")
				.deploy();
	}

	protected void tearDown() throws Exception {
		repositoryService.deleteDeploymentCascade(deploymentId);
		tearDownJbpm();
		super.tearDown();
	}
	
	public void testFork(){
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("fork");
		String pid = processInstance.getId();
		
		Execution state1 = processInstance.findActiveExecutionIn("state1");
		assertNotNull(state1);
		processInstance = executionService.signalExecutionById(pid);
		
		String state2 = processInstance.findActiveExecutionIn("state2").getId();
		String state3 = processInstance.findActiveExecutionIn("state3").getId();
		assertNotNull(state2);
		assertNotNull(state3);
		processInstance = executionService.signalExecutionById(state2);
		processInstance = executionService.signalExecutionById(state3);
		
		Execution state4 = processInstance.findActiveExecutionIn("state4");
		assertNotNull(state4);
		processInstance = executionService.signalExecutionById(pid);
		
	    assertEquals(true, processInstance.isEnded());
	}
}
