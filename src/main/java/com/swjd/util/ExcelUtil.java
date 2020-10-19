package com.swjd.util;

import com.swjd.bean.Student;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Climb.Xu
 * @date 2020/10/18 20:51
 */
public class ExcelUtil {
    public static List<Student> readExcel(File file) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            //1:创建workbook
            Workbook workbook = Workbook.getWorkbook(file);
            //2:获取第一个工作表sheet
            Sheet sheet = workbook.getSheet(0);
            //4.自己注意行列关系
            for (int i = 1; i < sheet.getRows(); i++) {
                Student student = new Student();
                student.setAccount(sheet.getCell(0, i).getContents());
                student.setPassword(sheet.getCell(1, i).getContents());
                student.setFullName(sheet.getCell(2, i).getContents());
                student.setClassName(sheet.getCell(3, i).getContents());
                student.setTeacherAccount(sheet.getCell(4, i).getContents());
                student.setAuth(sheet.getCell(5, i).getContents());
                students.add(student);
            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}

