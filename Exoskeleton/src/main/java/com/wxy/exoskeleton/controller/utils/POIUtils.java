package com.wxy.exoskeleton.controller.utils;

import com.wxy.exoskeleton.model.AllInfo;
import com.wxy.exoskeleton.model.Info;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POIUtils {

    public static ResponseEntity<byte[]> AllInfo2Excel(List<AllInfo> list) {

        //1.创建一个Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建文档摘要
        workbook.createInformationProperties();
        //3.获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("数据信息");
        //文档管理员
        docInfo.setManager("wxy");
        //设置企业信息
        docInfo.setCompany("www.exoskeleton.org");
        //4.获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("详细信息表");
        //文档作者
        summInfo.setAuthor("wxy");
        //文档备注
        summInfo.setComments("本文档由 wxy 提供");
        //5.创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("详细信息表");
        //设置列宽
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
//        sheet.setColumnWidth(12, 16 * 256);
//        sheet.setColumnWidth(13, 14 * 256);
//        sheet.setColumnWidth(14, 14 * 256);
//        sheet.setColumnWidth(15, 12 * 256);
//        sheet.setColumnWidth(16, 8 * 256);
//        sheet.setColumnWidth(17, 20 * 256);
//        sheet.setColumnWidth(18, 20 * 256);
//        sheet.setColumnWidth(19, 15 * 256);
//        sheet.setColumnWidth(20, 8 * 256);
//        sheet.setColumnWidth(21, 25 * 256);
//        sheet.setColumnWidth(22, 14 * 256);
//        sheet.setColumnWidth(23, 15 * 256);
//        sheet.setColumnWidth(24, 15 * 256);
        //6.创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("姓名");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("性别");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("年龄");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("体重");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("腿长");
        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("胯宽");
        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("是否为病人");
        HSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("曲线类型");
        HSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("电话号码");
        HSSFCell c10 = r0.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("联系地址");
        HSSFCell c11 = r0.createCell(11);
        c11.setCellStyle(headerStyle);
        c11.setCellValue("录入时间");

        for (int i = 0; i < list.size(); i++) {
            AllInfo allInfo = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(allInfo.getId());
            row.createCell(1).setCellValue(allInfo.getName());
            row.createCell(2).setCellValue(allInfo.getGender());
            row.createCell(3).setCellValue(allInfo.getAge());
            row.createCell(4).setCellValue(allInfo.getWeight());
            row.createCell(5).setCellValue(allInfo.getHeight());
            row.createCell(6).setCellValue(allInfo.getLegLength());
            row.createCell(7).setCellValue(allInfo.getCrotchWidth());
            row.createCell(8).setCellValue(allInfo.getType());
            row.createCell(9).setCellValue(allInfo.getNumber());
            row.createCell(10).setCellValue(allInfo.getAddress());
            HSSFCell cell4 = row.createCell(11);
            cell4.setCellStyle(dateCellStyle);
            cell4.setCellValue(allInfo.getDate());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("详细信息表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }


    public static List<AllInfo> excel2AllInfo(MultipartFile file) {
        List<AllInfo> list = new ArrayList<>();
        AllInfo allInfo = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    allInfo = new AllInfo();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        allInfo.setName(cellValue);
                                        break;
                                    case 2:
                                        allInfo.setGender(cellValue);
                                        break;
                                    case 9:
                                        allInfo.setNumber(cellValue);
                                        break;
                                    case 10:
                                        allInfo.setAddress(cellValue);
                                        break;
                                }
                                break;


//                            case NUMERIC:
//                                switch (k) {
//                                    case 3:
//                                        allInfo.setAge(value);
//                                        break;
//                                    case 4:
//                                        allInfo.setWeight(value);
//                                        break;
//                                    case 5:
//                                        allInfo.setHeight(value);
//                                        break;
//                                    case 6:
//                                        allInfo.setLegLength(value);
//                                        break;
//                                    case 7:
//                                        allInfo.setCrotchWidth(value);
//                                        break;
//                                    case 8:
//                                        allInfo.setType(value);
//                                        break;
//                                }
//                                break;
                            default: {
                                switch (k) {
                                    case 11:
                                        allInfo.setDate(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(allInfo);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}

