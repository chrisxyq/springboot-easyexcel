package com.example.springbooteasyexcel.controller;

/**
 * @author chrisxu
 * @create 2022-03-16 12:11
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.springbooteasyexcel.entity.Student;
import com.example.springbooteasyexcel.listener.WebStudentListener;
import com.example.springbooteasyexcel.utils.DataGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private WebStudentListener webStudentListener;

    /**
     * 文件上传
     *
     * @param uploadExcel
     * @return
     */
    @RequestMapping("read")
    @ResponseBody
    public String readExcel(MultipartFile uploadExcel) {
        try {
            ExcelReaderBuilder readWorkBook = EasyExcel.read(uploadExcel.getInputStream(), Student.class, webStudentListener);
            readWorkBook.sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 读取上传文件行数
     * @param uploadExcel
     * @return
     */
    @RequestMapping("read1")
    @ResponseBody
    public String readExcel1(MultipartFile uploadExcel) {
        try {
            ExcelReader excelReader = EasyExcelFactory.read(uploadExcel.getInputStream()).build().read(new ReadSheet(0));
            int row = excelReader.analysisContext().readRowHolder().getRowIndex();
            System.out.println(row);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 文件下载
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("write")
    @ResponseBody
    public String writeExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        String fileName = URLEncoder.encode("下载excel", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");


        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        sheet.doWrite(DataGetter.initData());
        return "success";
    }

    /**
     * 根据模板文件下载
     * 多条数据写入
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("writetemplate")
    @ResponseBody
    public String writeExcelTemplate(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        String fileName = URLEncoder.encode("模板下载excel", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");


        ServletOutputStream outputStream = response.getOutputStream();
        String template = "template.xlsx";
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        List<Student> students = DataGetter.initData();
        sheet.doFill(students);
        return "success";
    }

    /**
     * 单条数据写入
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("writetemplate1")
    @ResponseBody
    public String writeExcelTemplate1(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        String fileName = URLEncoder.encode("模板下载excel", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");


        ServletOutputStream outputStream = response.getOutputStream();
        String template = "singleTemplate.xlsx";
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        List<Student> students = DataGetter.initData();
        sheet.doFill(new ArrayList<>());
        return "success";
    }

    /**
     * 单条数据写入
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("writetemplate2")
    @ResponseBody
    public String writeExcelTemplate2(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //防止中文乱码
        String fileName = URLEncoder.encode("模板下载excel", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");


        ServletOutputStream outputStream = response.getOutputStream();
        String template = "singleTemplate.xlsx";
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class).withTemplate(template);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        List<Student> students = DataGetter.initData();
        sheet.doFill(students.get(0));
        return "success";
    }

}
