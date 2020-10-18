package com.swjd.controller;


import com.swjd.bean.Admin;
import com.swjd.bean.Student;
import com.swjd.common.Constant;
import com.swjd.service.AdminService;
import com.swjd.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    //登录
    @RequestMapping("/login")
    public Object login(@RequestBody LoginVo vo) {
        return adminService.login(vo);
    }

    //根据角色查询用户
    @GetMapping("/list_by_auth")
    public Object listByAuth(@PathVariable("auth") String auth) {
        System.out.println(auth);
         List<Student> users = adminService.listByAuth(auth);
        return users;
    }

    //添加用户()
    @RequestMapping("/add_user")
    public Object addUser(@RequestBody Student student) {
        Student result = adminService.addUser(student);
        return result;
    }
    //删除用户()
    //修改用户()

}

