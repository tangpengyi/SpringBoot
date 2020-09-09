package com.tpy.rntity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Student {

    @ExcelProperty("标题")
    private String titil;


    //不生成标题
    @ExcelIgnore
    private String numberTitil;

}
