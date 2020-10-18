package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swjd.bean.Admin;
import com.swjd.mapper.AdminMapper;
import com.swjd.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.vo.LoginVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Boolean login(LoginVo vo) {
        List<Admin> accounts = this.baseMapper.selectList(new QueryWrapper<Admin>().eq("account", vo.getAccount()));
        if (accounts == null) {
            return false;
        }
        Admin admin = accounts.get(0);
        if (admin.getPassword().equals(vo.getPassword())) {
            return true;
        }
        return false;
    }
}
