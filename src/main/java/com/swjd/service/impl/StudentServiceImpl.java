package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.bean.Student;
import com.swjd.mapper.StudentMapper;
import com.swjd.service.StudentService;
import com.swjd.util.R;
import com.swjd.vo.AddStuVo;
import com.swjd.vo.UpdStuPwdVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStu(String page, String count) {
        if (page==null||"".equals(page)){
            page="1";
        }
        if (count==null||"".equals(count)){
            count="5";
        }
        Page<Student> studentPage = new Page<>(Integer.valueOf(page), Integer.valueOf(count));
        List<Student> stus = studentMapper.selectPage(studentPage, null).getRecords();
        return stus;
    }

    @Override
    public R insert(AddStuVo student) {
        Student extend = studentMapper.selectById(Integer.valueOf(student.getAccount()));
        if (extend != null) {
            return R.error(300, "该账号已存在");
        }
        String password = student.getPassword();
        if (password == null || "".equals(password)) {
            password = "12346";
        }
        String name = student.getFullName();
        if (name == null || "".equals(name)) {
            return R.error(500, "姓名不能为空");
        }
        String className = student.getClassName();
        if (className == null || "".equals(className)) {
            return R.error(500, "班级不能为空");
        }
        String teacherAccount = student.getTeacherAccount();
        if (teacherAccount == null || "".equals(teacherAccount)) {
            return R.error(500, "讲师不能为空");
        }
        Student stu= new Student();
        stu.setAccount(student.getAccount());
        stu.setPassword(student.getPassword());
        stu.setFullName(student.getFullName());
        stu.setTeacherAccount(student.getTeacherAccount());
        stu.setClassName(student.getClassName());
        stu.setAuth("d");
        int insert = studentMapper.insert(stu);
        if (insert>0){
            return R.ok().put("status", 300);
        } else {
            return R.error(500, "新增失败");
        }
    }

    @Override
    public R getbyAccount(String account) {
        if (account==null||"".equals(account)){
           return R.error(500,"账号不能为空");
        }
        Student student = studentMapper.selectById(account);
        return R.ok().put("student",student);
    }

    @Override
    public int delById(String id) {
        int i = studentMapper.deleteById(id);
        return i;
    }

    @Override
    public R UpdStuPwd(UpdStuPwdVo updStuPwdVo) {
        String account = updStuPwdVo.getAccount();
        if(account==null||"".equals(account)){
            return R.error(500,"账号不能为空");
        }
        String password = updStuPwdVo.getPassword();
        if(password==null||"".equals(password)){
            return R.error(500,"密码不能为空");
        }

        Student student = new Student();
        student.setAccount(updStuPwdVo.getAccount());
        student.setPassword(updStuPwdVo.getPassword());
        int i = studentMapper.updateById(student);
        if (i>0){
            return R.ok().put("status",300);
        }else {
            return R.error(500,"修改失败");
        }
    }

    @Override
    public List<Student> selByName(String fullName) {
        if (fullName==null){
            fullName="";
        }
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.like("full_name",fullName);
        return studentMapper.selectList(studentQueryWrapper);
    }

}
