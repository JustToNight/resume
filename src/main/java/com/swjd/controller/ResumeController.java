package com.swjd.controller;


import com.swjd.service.ResumeService;
import com.swjd.util.R;
import com.swjd.vo.AuditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    //审核简历 vo.status 3打回 1讲师审核通过 2就业老师审核通过
    @PostMapping("/audit")
    public R auditByC(@RequestBody AuditVo vo) {
        String msg = resumeService.audit(vo);
        return msg == null ? R.error() : R.ok().put("msg", msg);
    }
}

