package com.swjd.controller;


import com.swjd.bean.Resume;
import com.swjd.service.ResumeService;
import com.swjd.util.R;
import com.swjd.vo.AddResumeVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Resource
    private ResumeService resumeService ;
    @ResponseBody
    @GetMapping("/upResume")
    public R upResume(AddResumeVo addResumeVo){

        int i =resumeService.upResume(addResumeVo);
        if (i>0){
            return R.ok();
        } else {
            return  R.error(500,"上传失败");
        }
    }
}

