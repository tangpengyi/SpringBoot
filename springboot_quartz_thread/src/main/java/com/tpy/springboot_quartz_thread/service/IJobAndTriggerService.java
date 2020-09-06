package com.tpy.springboot_quartz_thread.service;

import com.github.pagehelper.PageInfo;
import com.tpy.springboot_quartz_thread.entity.JobAndTrigger;

public interface IJobAndTriggerService {
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
