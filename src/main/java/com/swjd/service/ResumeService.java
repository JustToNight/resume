package com.swjd.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swjd.bean.Resume;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.util.R;
import com.swjd.vo.AuditVo;
import com.swjd.vo.ResumeUploadVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
public interface ResumeService extends IService<Resume> {

    String audit(AuditVo vo);

    void downloadResumeId(Integer resumeId, HttpServletResponse response);

    Resume upload(MultipartFile file, ResumeUploadVo vo);

    IPage<Resume> listByAuth(String account, Long page, Long limit);
}
