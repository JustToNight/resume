package com.swjd.resume;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swjd.bean.Student;
import com.swjd.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ResumeApplicationTests {

    @Resource
    private StudentMapper studentMapper;

    @Test
    void contextLoads() {
        Page<Student> studentPage = new Page<>(1,2);
        List<Student> records = studentMapper.selectPage(studentPage, null).getRecords();
        System.out.println(records);
    }

}
