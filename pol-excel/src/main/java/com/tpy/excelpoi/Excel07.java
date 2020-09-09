package com.tpy.excelpoi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class Excel07 {

    @Test
    public void mian() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("姓名");

        cell = row.createCell(1);
        cell.setCellValue("年龄");

        cell = row.createCell(2);
        cell.setCellValue("时间");


        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("张三");

        cell = row.createCell(1);
        cell.setCellValue("24");

        cell = row.createCell(2);
        String s = new DateTime().toString("YY-MM-dd HH:mm:ss");
        cell.setCellValue(s);

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\IdeaWork\\SpringBoot\\pol-excel\\excel07.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("Excel 03创建成功");
    }

    /**
     * 写入慢，存储数量没有上限
     * @throws IOException
     */
    @Test
    public void readerFileMian() throws IOException {

        long start = System.currentTimeMillis();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生");

        for(int i = 0; i < 50000; i++){
            Row row = sheet.createRow(i);
            for(int j = 0; j < 10; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\IdeaWork\\SpringBoot\\pol-excel\\excel03.xlsx");

        System.out.println("Excel 07 测试文件 创建成功");

        workbook.write(fileOutputStream);
        fileOutputStream.close();

        long end = System.currentTimeMillis();
        System.out.println((double)(end-start)/1000);
    }

    /**
     * 使用SXSS解决XSS速度慢的问题，主要创建一个临时表存储数据
     * @throws IOException
     */
    @Test
    public void writerFileMianS() throws IOException {

        long start = System.currentTimeMillis();

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生");

        for(int i = 0; i < 50000; i++){
            Row row = sheet.createRow(i);
            for(int j = 0; j < 10; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\IdeaWork\\SpringBoot\\pol-excel\\excel03.xlsx");

        System.out.println("SXSS Excel 07 测试文件 创建成功");

        workbook.write(fileOutputStream);
        fileOutputStream.close();

        //清理临时表
        ((SXSSFWorkbook) workbook).dispose();

        long end = System.currentTimeMillis();
        System.out.println((double)(end-start)/1000);
    }
}
