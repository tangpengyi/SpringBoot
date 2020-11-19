package com.tpy.excelpoi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.*;

public class Excel03 {

    @Test
    public void mian() throws IOException {
        Workbook workbook = new HSSFWorkbook();
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

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\IdeaWork\\SpringBoot\\pol-excel\\excel03.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("Excel 03创建成功");
    }

    //写入速度快，存储数量有上限
    @Test
    public void writerFileMian() throws IOException {

        long start = System.currentTimeMillis();

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生");

        for(int i = 0; i < 50000; i++){
            Row row = sheet.createRow(i);
            for(int j = 0; j < 10; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(j);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\IdeaWork\\SpringBoot\\pol-excel\\excel03.xls");

        System.out.println("Excel 03 测试文件 创建成功");

        workbook.write(fileOutputStream);
        fileOutputStream.close();

        long end = System.currentTimeMillis();
        System.out.println((double)(end-start)/1000);
    }

    //读取数据
    @Test
    public void readerFile() throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\学生.xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        //获取标题内容
        Row titilRow = sheet.getRow(0);
        if(titilRow != null){
            int cellCount = titilRow.getPhysicalNumberOfCells();
            for(int cellNum = 0; cellNum < cellCount; cellNum++){
                Cell cell = titilRow.getCell(cellNum);
                if(cell != null){
                    int cellType = cell.getCellType();
                    System.out.println(cellType);
                    String titil = cell.getStringCellValue();
                    System.out.print(titil+"|");
                }
            }
            System.out.println();
        }

        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if(rowData != null){
                //获取列数量
                int cellCount = titilRow.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    Cell cell = rowData.getCell(cellNum);
                    if(cell != null){
                        int cellType = cell.getCellType();
                        String cellValue = "";

                        switch (cellType){
                            case Cell.CELL_TYPE_STRING://String
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_BOOLEAN://布尔
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_BLANK://空
                                break;
                            case Cell.CELL_TYPE_NUMERIC://数字
                                if(DateUtil.isCellDateFormatted(cell)){
                                    cellValue = new DateTime(cell.getDateCellValue()).toString("yyyy-MM-dd");
                                }else{
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cellValue = cell.toString();
                                }
                                break;
                            case Cell.CELL_TYPE_ERROR:
                                System.out.println("类型出错");
                                break;
                        }
                        System.out.println(cellValue);
                    }
                }
            }


        }


        inputStream.close();
    }
}
