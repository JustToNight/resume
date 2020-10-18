package com.swjd.controller;


import com.swjd.bean.Admin;
import com.swjd.bean.Student;
import com.swjd.service.AdminService;
import com.swjd.util.R;
import com.swjd.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
    @PostMapping("/login")
    public R login(@RequestBody LoginVo vo) {
        Admin admin = adminService.login(vo);
        return admin == null ? R.error().put("msg", "登录失败") : R.ok().put("data", admin);
    }

    //根据角色查询用户
    @GetMapping("/list_by_auth")
    public Object listByAuth(String auth) {
        List<Student> users = adminService.listByAuth(auth);
        return users == null ? R.error() : R.ok().put("data", users);
    }

    //添加用户()
    @RequestMapping("/add_user")
    public Object addUser(@RequestBody Student student) {
        Student result = adminService.addUser(student);
        return result == null ? R.error() : R.ok().put("data", result);
    }
    //删除用户()
    //修改用户()

}

