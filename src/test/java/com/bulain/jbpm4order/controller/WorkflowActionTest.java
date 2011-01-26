package com.bulain.jbpm4order.controller;

import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.bulain.jbpm4order.test.JbpmTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

public class WorkflowActionTest extends JbpmTestCase {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(WorkflowActionTest.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		super.setUpCleanJbpm();
		super.setUpDB("data/init_action.xml");
		super.setUpAction("admin", "admin");
	}

	protected void tearDown() throws Exception {
		super.tearDownAction();
		super.tearDownDB();
		super.tearDown();
	}

	public void testWorkflow() throws Exception {
		initServletMockObjects();
		ActionProxy proxy = getActionProxy("/workflow/deploy");
		WorkflowAction workflowAction = (WorkflowAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/workflow/list");
		workflowAction = (WorkflowAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		List<ProcessDefinition> listProcessDefinition = workflowAction.getListProcessDefinition();
		List<ProcessInstance> listProcessInstance = workflowAction.getListProcessInstance();
		List<Task> listTask = workflowAction.getListPersonTask();
		
		assertEquals(1, listProcessDefinition.size());
		assertEquals(0, listProcessInstance.size());
		assertEquals(0, listTask.size());
		
		String processDefinitionId = listProcessDefinition.get(0).getId();
		
		initServletMockObjects();
		request.setParameter("processDefinitionId", processDefinitionId);
		proxy = getActionProxy("/workflow/start");
		workflowAction = (WorkflowAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/workflow/list");
		workflowAction = (WorkflowAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		assertEquals(1, workflowAction.getListProcessInstance().size());
		String executionId = workflowAction.getListProcessInstance().get(0).getId();
		
		initServletMockObjects();
		request.setParameter("executionId", executionId);
		proxy = getActionProxy("/workflow/view");
		workflowAction = (WorkflowAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/workflow/list");
		workflowAction = (WorkflowAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		listProcessDefinition = workflowAction.getListProcessDefinition();
		listProcessInstance = workflowAction.getListProcessInstance();
		listTask = workflowAction.getListPersonTask();
		
		assertEquals(1, listProcessDefinition.size());
		assertEquals(1, listProcessInstance.size());
		assertEquals(1, listTask.size());
		
		String deploymentId = listProcessDefinition.get(0).getDeploymentId();
		
		initServletMockObjects();
		request.setParameter("deploymentId", deploymentId);
		proxy = getActionProxy("/workflow/destroy");
		workflowAction = (WorkflowAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		initServletMockObjects();
		proxy = getActionProxy("/workflow/list");
		workflowAction = (WorkflowAction) proxy.getAction();
		result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		
		listProcessDefinition = workflowAction.getListProcessDefinition();
		listProcessInstance = workflowAction.getListProcessInstance();
		listTask = workflowAction.getListPersonTask();
		
		assertEquals(0, listProcessDefinition.size());
		assertEquals(0, listProcessInstance.size());
		assertEquals(0, listTask.size());
	} 
}
