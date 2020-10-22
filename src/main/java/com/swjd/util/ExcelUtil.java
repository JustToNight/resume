package com.swjd.util;

import com.swjd.bean.Student;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang3.StringUtils;

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
            for (int i = 1; i < getRightRows(sheet); i++) {
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

    private static int getRightRows(Sheet sheet) {
        int rsCols = sheet.getColumns(); //列数
        int rsRows = sheet.getRows(); //行数
        int nullCellNum;
        int afterRows = rsRows;
        for (int i = 1; i < rsRows; i++) { //统计行中为空的单元格数
            nullCellNum = 0;
            for (int j = 0; j < rsCols; j++) {
                String val = sheet.getCell(j, i).getContents();
                val = StringUtils.trimToEmpty(val);
                if (StringUtils.isBlank(val))
                    nullCellNum++;
            }
            if (nullCellNum >= rsCols) { //如果nullCellNum大于或等于总的列数
                afterRows--; //行数减一
            }
        }
        return afterRows;
    }
}

