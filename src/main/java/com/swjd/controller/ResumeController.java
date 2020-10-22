package com.swjd.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swjd.bean.Resume;
import com.swjd.service.ResumeService;
import com.swjd.util.R;
import com.swjd.vo.AuditVo;
import com.swjd.vo.ResumeUploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    //简历下载
    @RequestMapping("/download/{resumeId}")
    public void downloadResumeId(HttpServletResponse response, @PathVariable("resumeId") Integer resumeId) {
        resumeService.downloadResumeId(resumeId, response);
    }

    //简历上传
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file,
                    @RequestParam("positions") String positions,
                    @RequestParam("studentAccount") String studentAccount,
                    @RequestParam("companyId") Integer companyId) {
        ResumeUploadVo vo = new ResumeUploadVo(positions, studentAccount, companyId);
        Resume resume = resumeService.upload(file, vo);
        return resume == null ? R.error() : R.ok().put("data", resume);
    }

    //根据角色查简历 分页
    @GetMapping("/list_by_account")
    public R listByAuth(String account, Long page, Long limit) {
        IPage<Resume> iPage = resumeService.listByAuth(account, page, limit);
        return iPage == null ? R.error() : R.ok().put("data", iPage.getRecords()).put("total", iPage.getTotal());
    }
}

