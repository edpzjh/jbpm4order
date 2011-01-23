package com.bulain.jbpm4order.integration.quartz;

import java.util.Iterator;
import java.util.Set;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.common.test.ServiceTestCase;

public class QuartzTest extends ServiceTestCase {
	private static final Logger LOG = LoggerFactory.getLogger(QuartzTest.class);

	private Scheduler scheduler; 
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(QuartzTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		scheduler = (Scheduler) applicationContext.getBean("scheduler");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SuppressWarnings("unchecked")
	public void testQuartz() throws SchedulerException{
		LOG.debug("testQuartz() - start");

		String[] jobGroupNames = scheduler.getJobGroupNames();
		for(String groupName : jobGroupNames){
			String[] jobNames = scheduler.getJobNames(groupName);
			
			for(String jobName : jobNames){
				JobDetail jobDetail = scheduler.getJobDetail(jobName, groupName);
				JobDataMap jobDataMap = jobDetail.getJobDataMap();
				System.out.printf("Job: %s\n" , jobName);
				Set<String> keySet = jobDataMap.keySet();
				Iterator<String> iterator = keySet.iterator();
				while(iterator.hasNext()){
					String key = (String)iterator.next();
					Object value = jobDataMap.get(key);
					System.out.printf("%s ==> %s\n", key, value);
				}
			}
		}
		
		String[] triggerGroupNames = scheduler.getTriggerGroupNames();
		for(String groupName : triggerGroupNames){
			String[] triggerNames = scheduler.getTriggerNames(groupName);
			
			for(String triggerName : triggerNames){
				Trigger trigger = scheduler.getTrigger(triggerName, groupName);
				JobDataMap jobDataMap = trigger.getJobDataMap();
				System.out.printf("Trigger: %s\n" , triggerName);
				Set<String> keySet = jobDataMap.keySet();
				Iterator<String> iterator = keySet.iterator();
				while(iterator.hasNext()){
					String key = (String)iterator.next();
					Object value = jobDataMap.get(key);
					System.out.printf("%s ==> %s\n", key, value);
				}
			}
		}
		
		LOG.debug("testQuartz() - end");
	}
}
