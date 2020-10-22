package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.bean.Admin;
import com.swjd.bean.Student;
import com.swjd.mapper.AdminMapper;
import com.swjd.service.AdminService;
import com.swjd.service.StudentService;
import com.swjd.util.ExcelUtil;
import com.swjd.util.FileUtils;
import com.swjd.util.PageUtil;
import com.swjd.vo.LoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private StudentService studentService;

    @Override
    public Admin login(LoginVo vo) {
        List<Admin> accounts = this.baseMapper.selectList(new QueryWrapper<Admin>().eq("account", vo.getAccount()));
        if (accounts == null || accounts.size() == 0) {
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
    public IPage<Student> listByAuth(String auth, Long page, Long limit) {
        if (auth == null) {
            List<Admin> admins = this.baseMapper.selectList(null);
            //所有admin
            List<Student> adminToStudent = admins.stream().map(i -> {
                Student student = new Student();
                BeanUtils.copyProperties(i, student);
                return student;
            }).collect(Collectors.toList());
            //所有学生
            List<Student> students = studentService.list(null);
            adminToStudent.addAll(students);
            List list = PageUtil.startPage(adminToStudent, page.intValue(), limit.intValue());
            Page<Student> page1 = new Page<>();
            page1.setRecords(list);
            page1.setTotal(adminToStudent.size());
            return page1;
        }
        if (!Arrays.asList("a", "b", "c", "d").contains(auth)) {
            return null;
        }
        if (auth.equals("d")) {
            return studentService.page(new Page<>(page, limit), null);
        }
        IPage<Admin> iPage = this.baseMapper.selectPage(new Page<>(page, limit), new QueryWrapper<Admin>().eq("auth", auth));
        List<Student> collect = iPage.getRecords().stream().map(i -> {
            Student student = new Student();
            BeanUtils.copyProperties(i, student);
            return student;
        }).collect(Collectors.toList());
        Page<Student> page3 = new Page<>();
        page3.setRecords(collect);
        page3.setTotal(iPage.getTotal());
        return page3;
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
        if (this.baseMapper.selectList(new QueryWrapper<Admin>().eq("account", student.getAccount())).size() > 0) {
            return null;
        }
        if (studentService.list(new QueryWrapper<Student>().eq("account", student.getAccount())).size() > 0) {
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

    @Override
    public Integer delUser(String account) {
        Admin admin = this.baseMapper.selectOne(new QueryWrapper<Admin>().eq("account", account));
        if (admin != null) {
            return this.baseMapper.deleteById(account);
        }
        Student student = studentService.getOne(new QueryWrapper<Student>().eq("account", account));
        if (student != null) {
            studentService.removeById(account);
            return 1;
        }
        return null;
    }

    @Override
    public Integer addStudentBatch(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String newFileName = FileUtils.upload(file, path, fileName);
        if (newFileName == null) {
            return null;
        }
        List<Student> data = ExcelUtil.readExcel(new File(path + newFileName));
        List<Student> list = studentService.list(null);
        //过滤已经存在的account
        List<Student> students = data.stream().filter(i -> !list.contains(i)).collect(Collectors.toList());
        data.forEach(System.out::println);
        students.forEach(System.out::println);
        return studentService.saveBatch(students) ? students.size() : 0;
    }
}
