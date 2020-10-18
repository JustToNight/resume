package com.swjd.controller;


import com.swjd.bean.Recruit;
import com.swjd.service.RecruitService;
import com.swjd.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    // 查询所有招聘信息(分页)
    @GetMapping("/getAllRecruit")
    public R getAllRecruit() {
        List<Recruit> allRecruit = recruitService.getAllRecruit();
        return allRecruit!=null?R.ok().put("data",allRecruit):R.error("查询所有招聘信息失败");
    }

    // 添加招聘信息
    @PostMapping("/addRecruit")
    public R addRecruit(Recruit recruit) {

        if (recruit.getId() == null) {
            return R.error("传入数据不能为空");
        }
        int size = recruitService.addRecruit(recruit);
        return size>0?R.ok("添加招聘信息成功"):R.error("添加招聘信息失败");
    }

    // 根据岗位名查询招聘信息
    @GetMapping("/getByNameRecruit/{name}")
    public R getByNameRecruit(@PathVariable("name") String name) {
        if (name == null) {
            return R.error("传入的值不能为空");
        }
        List<Recruit> recruits = recruitService.selectByNameRecruit(name);
        return recruits!=null?R.ok().put("data",recruits):R.error("根据名字查询招聘信息失败");
    }

    // 修改招聘信息
    @PostMapping("/updateRecruit")
    public R updateRecruit(Recruit recruit) {
        if (recruit.getId() == null) {
            return R.error("传入的实体类不能为空");
        }
        int size = recruitService.updateRecruit(recruit);
        return size>0?R.ok():R.error("修改招聘信息失败");
    }

    // 删除招聘信息
    @GetMapping("/delRecruit/{id}")
    public R delRecruit(@PathVariable("id") Integer id) {
        if (id == null) {
            return R.error("传入的ID不能为空");
        }
        int size = recruitService.delRecruit(id);

        return size>0?R.ok():R.error("删除招聘信息失败");
    }

}

