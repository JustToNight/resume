package com.swjd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.bean.Resume;
import com.swjd.bean.ResumeDesc;
import com.swjd.bean.Student;
import com.swjd.common.Constant;
import com.swjd.mapper.ResumeMapper;
import com.swjd.service.ResumeDescService;
import com.swjd.service.ResumeService;
import com.swjd.service.StudentService;
import com.swjd.util.FileUtils;
import com.swjd.vo.AuditVo;
import com.swjd.vo.ResumeUploadVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {
    @Autowired
    private ResumeDescService resumeDescService;
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private StudentService studentService;

    //3打回 1讲师审核通过 2就业老师审核通过
    @Override
    public String audit(AuditVo vo) {
        if (vo == null) {
            return null;
        }
        if (vo.getResumeId() == null) {
            return null;
        }
        if (vo.getStatus() == null) {
            return null;
        }
        if (vo.getStatus() != 1 && vo.getStatus() != 3 && vo.getStatus() != 2) {
            return null;
        }
        Resume resume = this.baseMapper.selectById(vo.getResumeId());
        if (resume == null) {
            return null;
        }
        //打回
        if (vo.getStatus() == 3) {
            //修改简历状态
            Resume resume1 = new Resume();
            resume1.setId(vo.getResumeId());
            resume1.setStatus(3);
            this.baseMapper.updateById(resume1);
            //新建打回原因
            ResumeDesc resumeDesc = new ResumeDesc();
            resumeDesc.setContent(vo.getContent());
            resumeDesc.setResumeId(vo.getResumeId());
            resumeDescService.save(resumeDesc);
            return "简历已打回";
        }
        //讲师审核通过
        if (vo.getStatus() == 1 || vo.getStatus() == 2) {
            //修改简历状态
            Resume resume1 = new Resume();
            resume1.setId(vo.getResumeId());
            resume1.setStatus(vo.getStatus());
            this.baseMapper.updateById(resume1);
        }
        return null;
    }

    @Override
    public void downloadResumeId(Integer resumeId, HttpServletResponse response) {
        Resume resume = this.baseMapper.selectById(resumeId);
        String url = resume.getUrl();
        File file = new File(url);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        String filename = null;
        try {
            filename = new String(file.getName().getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/octet-stream");// 二进制流，不知道下载文件的类型
        response.setHeader("content-type", "application/octet-stream");// 让服务器告诉浏览器它发送的数据属于什么文件类型
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名（这个信息头会告诉浏览器这个文件的名字和类型）\
        OutputStream outputStream = null;// 文件输出流
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtils.download(file, outputStream);
    }

    @Override
    public Resume upload(MultipartFile file, ResumeUploadVo vo) {
        Student student = studentService.getById(vo.getStudentAccount());
        if (student==null){
            return null;
        }
        String fileName = file.getOriginalFilename();
        String newFileName = FileUtils.upload(file, path, fileName);
        if (newFileName == null) {
            return null;
        }
        Resume resume = new Resume();
        BeanUtils.copyProperties(vo, resume);
        resume.setStatus(Constant.ResumeStatus.AUDITBYC.getCode());
        resume.setTime(new Date());
        resume.setUrl(path + newFileName);
        this.baseMapper.insert(resume);
        return resume;
    }
}
