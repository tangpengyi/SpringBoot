package com.tpy.springboot_quartz.controller;

import com.tpy.springboot_quartz.service.ScheduleRefreshService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private ScheduleRefreshService scheduleRefreshService;

    @GetMapping("hello")
    public String hello(){
        try {
            scheduleRefreshService.scheduleUpdateCronTrigger();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "失败";
        }
        return "成功";
    }
}
