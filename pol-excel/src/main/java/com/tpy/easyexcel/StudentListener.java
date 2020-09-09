package com.tpy.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tpy.controller.StudentDao;
import com.tpy.rntity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListener extends AnalysisEventListener<Student> {

    private StudentDao studentDao;

    public StudentListener(){
    }

    public StudentListener(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    List<Student> list = new ArrayList<Student>();

    //读取数据会执行如下方法
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println(student.toString());
        list.add(student);
        if(list.size() > 5){
            //保存数据库
            System.out.println(list.toString());
            list.clear();
        }
    }

    //所有数据读取完成，执行该方法
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        System.out.println(list.toString());

        //保存数据库
        System.out.println("保存数据库");
    }
}
