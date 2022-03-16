package com.example.springbooteasyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.springbooteasyexcel.entity.Student;
import com.example.springbooteasyexcel.service.StudentService;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chrisxu
 * @create 2022-03-16 12:24
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
@Component
@Scope("prototype")
public class WebStudentListener extends AnalysisEventListener<Student> {
    @Autowired
    private StudentService studentService;
    List<Student> students = new ArrayList<>();

    /**
     * 模拟分批保存数据库
     *
     * @param student
     * @param analysisContext
     */
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        students.add(student);
        if (students.size() % 5 == 0) {
            studentService.readExcel(students);
            students.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
