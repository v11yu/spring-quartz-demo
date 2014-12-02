package org.v11.demo;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;
@Configuration
@ComponentScan
public class SpringConfig {
	@Bean
	MyBeans bean1(){
		return new MyBeans("Toby");
	}
	@Bean
	MethodInvokingJobDetailFactoryBean simpleJobDetail(){
		MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
		bean.setTargetObject(bean1());
		bean.setTargetMethod("printMessage");
		bean.setConcurrent(false);
		return bean;
	}
	@Bean
	SimpleTriggerFactoryBean simpleTrigger(){
		SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
		JobDetail job = simpleJobDetail().getObject();
		bean.setJobDetail(job);
		bean.setStartDelay(1000);
		bean.setRepeatInterval(2000);
		return bean;
	}

	@Bean
	SchedulerFactoryBean scheduler(){
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		JobDetail job = simpleJobDetail().getObject();
		Trigger trigger = simpleTrigger().getObject();
		bean.setJobDetails(job);
		bean.setTriggers(trigger);
		return bean;
	}
}