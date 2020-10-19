package com.swjd.service.impl;

import com.swjd.bean.Resume;
import com.swjd.mapper.ResumeMapper;
import com.swjd.service.ResumeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.util.FileUtils;
import com.swjd.vo.AddResumeVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {

    @Resource
    private ResumeMapper resumeMapper;
    @Value("${web.upload-path}")
    private String path ;
    @Override
    public int upResume(AddResumeVo resume) {
        //1定义要上传文件 的存放路径
        String localPath=path;
        //2获得文件名字
        String fileName=resume.getFile().getOriginalFilename();
        //2上传失败提示
        String warning="";
        String newFileName = FileUtils.upload(resume.getFile(), localPath, fileName);
        if(newFileName != null){
            //上传成功
            Resume res = new Resume();
            res.setStudentAccount(resume.getStudentAccount());
            res.setCompanyId(resume.getCompanyId());
            res.setName(fileName);
            res.setStatus("0");
            res.setUrl(newFileName);
            res.setPositions(resume.getPositions());
            res.setTime(new Date());
            int insert = resumeMapper.insert(res);
            return insert;
        }else{
            return 0;
        }

    }
}
