package com.tpy.springboot_quartz.job;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableScheduling
public class ScheduledTask {

    private static Log log = LogFactory.getLog(ScheduledTask.class);

    public void sayHello(){
        log.info("一号任务");
    }
}