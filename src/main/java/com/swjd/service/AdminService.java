package com.swjd.service;

import com.swjd.bean.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.bean.Student;
import com.swjd.util.R;
import com.swjd.vo.LoginVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
public interface AdminService extends IService<Admin> {
    //登录
    Admin login(LoginVo vo);

    //根据auth查用户
    List<Student> listByAuth(String auth);

    //添加用户
    Student addUser(Student student);

    Integer delUser(String account);

    Integer addStudentBatch(MultipartFile file);
}
