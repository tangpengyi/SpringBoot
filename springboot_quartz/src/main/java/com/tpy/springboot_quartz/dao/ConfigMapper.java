package com.tpy.springboot_quartz.dao;

import org.springframework.stereotype.Component;

@Component
public class ConfigMapper {

    public String findOne(int id){
        return "1/10 * * * * ? *";
    }

}
