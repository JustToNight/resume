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

    /**
     * 查询所有已开启招聘的公司
     * @return
     */
    List<Company> getAllCompany();

    /**
     * 添加一个企业
     * @return
     */
    int addCompany(Company company);

    /**
     * 根据Id查询企业
     * @param id
     * @return
     */
    Company getByIdCompany(Integer id);

    /**
     * 修改企业信息
     * @param company
     * @return
     */
    int updateCompany(Company company);

    /**
     * 根据Id删除企业
     * @return
     */
    int delByIdCompany(Integer id);

}
