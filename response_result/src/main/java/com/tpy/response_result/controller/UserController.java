package com.tpy.response_result.controller;

import com.tpy.response_result.annototion.ResponseResult;
import com.tpy.response_result.entity.DemoModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseResult
public class UserController {

    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable("msg") String msg){
        return "hello world";
    }


    @RequestMapping("/demo2")
    public String demo2(@RequestBody @Valid DemoModel demo){
        System.out.println("测试");
        return "成功";
    }
}
