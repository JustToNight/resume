package com.swjd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.bean.Resume;
import com.swjd.bean.ResumeDesc;
import com.swjd.mapper.ResumeMapper;
import com.swjd.service.ResumeDescService;
import com.swjd.service.ResumeService;
import com.swjd.vo.AuditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
