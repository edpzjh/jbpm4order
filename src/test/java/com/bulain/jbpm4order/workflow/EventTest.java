package com.bulain.jbpm4order.workflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.junit.Test;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.bulain.jbpm4order.test.JbpmTestCase;

public class EventTest extends JbpmTestCase {
    private String deploymentId;

    @BeforeTransaction
    public void setUp() throws Exception {
        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/event.jpdl.xml").deploy();
        identityService.createUser("user1", "user1", "user1");
    }

    @AfterTransaction
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);
        identityService.deleteUser("user1");
    }

    @Test
    public void testEvent() {
        String userId = "user1";

        Map<String, Object> variables = new HashMap<String, Object>();
        ProcessInstance processInstance = executionService.startProcessInstanceByKey("event", variables);
        String pid = processInstance.getId();

        List<Task> listTask = taskService.findGroupTasks(userId);
        assertEquals(1, listTask.size());

        String taskId = listTask.get(0).getId();
        taskService.takeTask(taskId, userId);

        taskService.completeTask(taskId);

        executionService.signalExecutionById(pid);

        Date endTime = historyService.createHistoryProcessInstanceQuery().processInstanceId(pid).uniqueResult()
                .getEndTime();
        assertNotNull(endTime);
    }
}
