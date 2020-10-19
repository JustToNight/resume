package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swjd.bean.Company;
import com.swjd.bean.Recruit;
import com.swjd.common.Constant;
import com.swjd.mapper.CompanyMapper;
import com.swjd.mapper.RecruitMapper;
import com.swjd.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private RecruitMapper recruitMapper;

    // 提取条件构造器
    QueryWrapper<Company> comQW = new QueryWrapper<>();

    /**
     * 查询所有已开启招聘的公司
     * @return
     */
    @Override
    public List<Company> getAlreadyAll() {
        Page<Company> companyPage = new Page<>(1,5);
        IPage<Company> companyIPage = companyMapper.selectPage(companyPage, comQW.eq("status", Constant.CompanyStatus.START.getCode()));
        return companyIPage.getRecords();
    }

    /**
     * 查询所有未开启招聘的公司
     * @return
     */
    @Override
    public List<Company> getNotAll() {
        QueryWrapper<Company> comQW = new QueryWrapper<>();
        Page<Company> companyPage = new Page<>(1,5);
        IPage<Company> companyIPage = companyMapper.selectPage(companyPage, comQW.eq("status", Constant.CompanyStatus.CREATE.getCode()));
        return companyIPage.getRecords();
    }

    /**
     * 添加一个企业
     * @return
     */
    @Override
    public int addCompany(Company company) {
        int size = companyMapper.insert(company);
        return size;
    }

    /**
     * 根据Id查询企业
     * @param id
     * @return
     */
    @Override
    public Company getByIdCompany(Integer id) {
        Company company = companyMapper.selectById(id);
        return company;
    }

    /**
     * 修改企业信息
     * @param company
     * @return
     */
    @Override
    public int updateCompany(Company company) {
        int size = companyMapper.updateById(company);
        return size;
    }

    /**
     * 根据Id删除企业
     * @return
     */
    @Override
    public int delByIdCompany(Integer id) {

        QueryWrapper<Recruit> comQW = new QueryWrapper<>();

        // 判断企业下是否存在招聘需求
        recruitMapper.delete(comQW.eq("company_id", id));

       // 删除企业
        return companyMapper.deleteById(id);

    }


}
