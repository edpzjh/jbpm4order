package com.bulain.jbpm4order.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class OrderTest extends JbpmTestCase {
	private String deploymentId;
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(OrderTest.class);
	}	  
	
	protected void setUp() throws Exception {
	    super.setUp();
	    setUpJbpm();
	    deploymentId = repositoryService.createDeployment()
	        .addResourceFromClasspath("com/bulain/jbpm4order/workflow/order.jpdl.xml")
	        .deploy();
	}
	
	protected void tearDown() throws Exception {
	    repositoryService.deleteDeploymentCascade(deploymentId);
	    tearDownJbpm();
	    super.tearDown();
	}
	
	public void testOrderApprove() {
	    Map<String, Object> variables = new HashMap<String, Object>(); 
	    variables.put("owner", "johndoe");
	    ProcessInstance processInstance = executionService.startProcessInstanceByKey("order", variables);
	    String pid = processInstance.getId();

	    List<Task> taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(1, taskList.size());
	    Task task = taskList.get(0);
	    assertEquals("request", task.getName());
	    assertEquals("johndoe", task.getAssignee());

	    taskService.completeTask(task.getId());
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(0, taskList.size());
	    
	    taskList = taskService.findPersonalTasks("bulain");
	    assertEquals(1, taskList.size());

	    task = taskList.get(0);
	    assertEquals("approval", task.getName());
	    assertEquals("bulain", task.getAssignee());

	    taskService.completeTask(task.getId(), "approve");
	    
	    processInstance = executionService.findProcessInstanceById(pid);
	    assertNull(processInstance);
	  }
	
	public void testOrderReject() {
	    Map<String, Object> variables = new HashMap<String, Object>(); 
	    variables.put("owner", "johndoe");
	    ProcessInstance processInstance = executionService.startProcessInstanceByKey("order", variables);
	    String pid = processInstance.getId();

	    List<Task> taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(1, taskList.size());
	    Task task = taskList.get(0);
	    assertEquals("request", task.getName());
	    assertEquals("johndoe", task.getAssignee());

	    taskService.completeTask(task.getId());
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(0, taskList.size());
	    
	    taskList = taskService.findPersonalTasks("bulain");
	    assertEquals(1, taskList.size());

	    task = taskList.get(0);
	    assertEquals("approval", task.getName());
	    assertEquals("bulain", task.getAssignee());

	    taskService.completeTask(task.getId(), "reject");
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(1, taskList.size());
	    task = taskList.get(0);
	    assertEquals("request", task.getName());
	    assertEquals("johndoe", task.getAssignee());
	    
	    taskService.completeTask(task.getId());
	    
	    taskList = taskService.findPersonalTasks("johndoe");
	    assertEquals(0, taskList.size());
	    
	    taskList = taskService.findPersonalTasks("bulain");
	    assertEquals(1, taskList.size());

	    task = taskList.get(0);
	    assertEquals("approval", task.getName());
	    assertEquals("bulain", task.getAssignee());

	    taskService.completeTask(task.getId(), "approve");
	    
	    processInstance = executionService.findProcessInstanceById(pid);
	    assertNull(processInstance);
	  }
}
