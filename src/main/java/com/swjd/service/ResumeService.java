package com.swjd.service;

import com.swjd.bean.Resume;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.util.R;
import com.swjd.vo.AuditVo;

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
}
