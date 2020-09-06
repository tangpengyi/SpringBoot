package com.tpy.springboot_quartz.job;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableScheduling
public class ScheduledTask2 {

    private static Log log = LogFactory.getLog(ScheduledTask2.class);

    public void say(){
        log.info("二号任务");
    }

}
