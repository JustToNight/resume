package com.swjd.service;

import com.swjd.bean.Resume;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.vo.AddResumeVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
public interface ResumeService extends IService<Resume> {

    int upResume(AddResumeVo resume);

}
