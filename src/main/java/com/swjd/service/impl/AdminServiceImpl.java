package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.bean.Admin;
import com.swjd.bean.Student;
import com.swjd.mapper.AdminMapper;
import com.swjd.service.AdminService;
import com.swjd.service.StudentService;
import com.swjd.vo.LoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private StudentService studentService;

    @Override
    public Admin login(LoginVo vo) {
        List<Admin> accounts = this.baseMapper.selectList(new QueryWrapper<Admin>().eq("account", vo.getAccount()));
        if (accounts == null) {
            return null;
        }
        Admin admin = accounts.get(0);
        if (admin.getPassword().equals(vo.getPassword())) {
            return admin;
        }
        return null;
    }

    //根据auth获取用户
    @Override
    public List<Student> listByAuth(String auth){
        if (auth == null) {
            //所有admin
            List<Student> admins = this.baseMapper.selectList(null).stream().map(i -> {
                Student student = new Student();
                BeanUtils.copyProperties(i, student);
                return student;
            }).collect(Collectors.toList());
            //所有学生
            List<Student> students = studentService.list(null);
            admins.addAll(students);
            return admins;
        }
        if (!Arrays.asList("a", "b", "c", "d").contains(auth)) {
            return null;
        }
        if (auth.equals("d")) {
            return studentService.list(null);
        }
        return this.baseMapper.selectList(new QueryWrapper<Admin>().eq("auth", auth)).stream().map(i -> {
            Student student = new Student();
            BeanUtils.copyProperties(i, student);
            return student;
        }).collect(Collectors.toList());
    }

    @Override
    public Student addUser(Student student) {
        if (student == null) {
            return null;
        }
        if (student.getAuth() == null) {
            return null;
        }
        String auth = student.getAuth();
        if (!Arrays.asList("a", "b", "c", "d").contains(auth)) {
            return null;
        }
        if (auth.equals("d")) {
            if (studentService.save(student)) {
                return student;
            }
            return null;
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(student, admin);
        if (this.baseMapper.insert(admin) > 0) {
            return student;
        }
        return null;

    }
}
