package com.tpy.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.tpy.rntity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelDemo {

    public List getdate(){
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setTitil("标题");
            student.setNumberTitil("数字标题");
            list.add(student);
        }
        return list;
    }

    @Test
    public void simpleWriter(){
        String fileName = "D:\\IdeaWork\\SpringBoot\\pol-excel\\student03.xls";
        EasyExcel.write(fileName,Student.class).sheet("模块").doWrite(getdate());
    }

    @Test
    public void simpleRead() {
        String fileName = "D:\\IdeaWork\\SpringBoot\\pol-excel\\student03.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Student.class, new StudentListener()).sheet().doRead();
    }

}
