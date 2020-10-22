package com.swjd.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swjd.bean.Admin;
import com.swjd.bean.Student;
import com.swjd.service.AdminService;
import com.swjd.util.R;
import com.swjd.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public R listByAuth(String auth, Long page, Long limit) {
        IPage<Student> iPage = adminService.listByAuth(auth, page, limit);
        List<Student> users = iPage.getRecords();
        return iPage == null ? R.error() : R.ok().put("data", users).put("total", iPage.getTotal());
    }

    //添加用户 
    @PostMapping("/add_user")
    public R addUser(@RequestBody Student student) {
        Student result = adminService.addUser(student);
        return result == null ? R.error() : R.ok().put("data", result);
    }

    //批量添加学生
    @PostMapping("/add_user_batch")
    public R addStudentBatch(@RequestParam("file") MultipartFile file) {
        Integer result = adminService.addStudentBatch(file);
        return result == null ? R.error() : R.ok().put("msg", "添加学生" + result + "个");
    }

    //删除用户
    @GetMapping("/del_user/{account}")
    public R delUser(@PathVariable("account") String account) {
        Integer result = adminService.delUser(account);
        return result == null ? R.error() : R.ok();
    }

//    //修改用户()
//    @PostMapping("/change")

}

