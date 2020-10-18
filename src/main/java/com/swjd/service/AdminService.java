package com.swjd.service;

import com.swjd.bean.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.vo.LoginVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
public interface AdminService extends IService<Admin> {
    //登录
    Boolean login(LoginVo vo);

}
