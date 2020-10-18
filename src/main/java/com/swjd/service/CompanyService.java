package com.swjd.service;

import com.swjd.bean.Company;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
public interface CompanyService extends IService<Company> {

    List<Company> getAll();
}
