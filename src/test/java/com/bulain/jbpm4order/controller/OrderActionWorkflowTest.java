package com.bulain.jbpm4order.controller;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Order;
import com.bulain.common.service.OrderService;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class OrderActionWorkflowTest extends ActionTestCase {
    private String processDefinitionId;
    private String deploymentId;

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ExecutionService executionService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        super.setUpAction("admin", "admin");

        deploymentId = repositoryService.createDeployment()
                .addResourceFromClasspath("com/bulain/jbpm4order/workflow/order.jpdl.xml").deploy();
        processDefinitionId = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId)
                .uniqueResult().getId();
        orderService = (OrderService) applicationContext.getBean("orderService");
    }

    @After
    public void tearDown() throws Exception {
        repositoryService.deleteDeploymentCascade(deploymentId);
        super.tearDownAction();
        super.tearDown();
    }

    protected String start() throws Exception {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", "bulain");
        ProcessInstance processInstance = executionService.startProcessInstanceById(processDefinitionId, variables);
        return processInstance.getId();

    }

    protected String task(String executionId) throws Exception {
        return taskService.createTaskQuery().executionId(executionId).uniqueResult().getId();
    }

    @Test
    public void testWorkflowApprove() throws Exception {
        String executionId = start();
        String taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        ActionProxy proxy = getActionProxy("/order/request");
        OrderAction orderAction = (OrderAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        String wfId = orderAction.getOrder().getWfId();

        initServletMockObjects();
        request.setParameter("order.name", "name");
        request.setParameter("order.note", "note");
        request.setParameter("order.wfId", wfId);
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Request");
        proxy = getActionProxy("/order/submitRequest");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        proxy = getActionProxy("/order/approval");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Long orderId = orderAction.getOrder().getId();

        initServletMockObjects();
        request.setParameter("order.id", Long.toString(orderId));
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Approve");
        proxy = getActionProxy("/order/submitApproval");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Order order = orderService.getByWfId(executionId);
        orderService.delete(order.getId());
    }

    @Test
    public void testWorkflowReject() throws Exception {
        String executionId = start();
        String taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        ActionProxy proxy = getActionProxy("/order/request");
        OrderAction orderAction = (OrderAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        String wfId = orderAction.getOrder().getWfId();

        initServletMockObjects();
        request.setParameter("order.name", "name");
        request.setParameter("order.note", "note");
        request.setParameter("order.wfId", wfId);
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Request");
        proxy = getActionProxy("/order/submitRequest");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        taskId = task(executionId);

        initServletMockObjects();
        request.addParameter("taskId", taskId);
        proxy = getActionProxy("/order/approval");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Long orderId = orderAction.getOrder().getId();

        initServletMockObjects();
        request.setParameter("order.id", Long.toString(orderId));
        request.setParameter("taskId", taskId);
        request.setParameter("submit", "Reject");
        proxy = getActionProxy("/order/submitApproval");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Order order = orderService.getByWfId(executionId);
        orderService.delete(order.getId());
    }
}
