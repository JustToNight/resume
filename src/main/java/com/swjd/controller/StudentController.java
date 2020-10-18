package com.swjd.controller;


import com.swjd.bean.Student;
import com.swjd.service.StudentService;
import com.swjd.util.R;
import com.swjd.vo.AddStuVo;
import com.swjd.vo.UpdStuPwdVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
//@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     *
     * @param current 页数
     * @param count 每页显示的数量
     * @return 所有用户 json
     */

    @ResponseBody
    @RequestMapping("/getAllStu")
    public String getAllStu(String current,String count){
        List<Student> allStu = studentService.getAllStu(current,count);
        return allStu.toString();
    }

    //添加一个学生
    @ResponseBody
    @RequestMapping("/addStu")
    public R addStu(AddStuVo vo){
        R insert = studentService.insert(vo);
        return insert;
    }
//根据账号删除一个学生
    @ResponseBody
    @GetMapping("/delStu")
    public R delStu(String account){
        int i = studentService.delById(account);
        if (i>0){
           return R.ok().put("status",300);
        }else {
           return R.error(500,"删除失败");
        }
    }

    //查找一个学生
    @ResponseBody
    @RequestMapping("/getStuById")
    public R addStu(String account){
        R student = studentService.getbyAccount(account);
        return student;
    }

    //密码修改
    @ResponseBody
    @GetMapping("/updPwd")
    public R updPwd(UpdStuPwdVo updStuPwdVo){
        R ok= studentService.UpdStuPwd(updStuPwdVo);
        return ok;
    }
    //根据名称模糊查找
    @ResponseBody
    @GetMapping("/selByName")
    public R updPwd(String fullName){
        List<Student> students= studentService.selByName(fullName);
        return R.ok().put("students",students);
    }
}

