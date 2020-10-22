package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swjd.bean.Company;
import com.swjd.bean.Recruit;
import com.swjd.mapper.CompanyMapper;
import com.swjd.mapper.RecruitMapper;
import com.swjd.service.RecruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.util.PageUtil;
import com.swjd.vo.CompanyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RecruitServiceImpl extends ServiceImpl<RecruitMapper, Recruit> implements RecruitService {

    @Resource
    private RecruitMapper recruitMapper;

    @Resource
    private CompanyMapper companyMapper;

    // 抽取条件构造器
    private QueryWrapper<Recruit> comQW = new QueryWrapper<>();

    /**
     * 查询招聘信息
     * @return
     */
    @Override
    public IPage<Recruit> getAllRecruit(Long page, Long limit) {
        List<Recruit> recruits = recruitMapper.selectList(null);
        for (Recruit e : recruits) {
            Company company = companyMapper.selectById(e.getCompanyId());
            e.setCompanyList(company);
        }
        List list = PageUtil.startPage(recruits, page.intValue(), limit.intValue());
        Page<Recruit> recruitPage = new Page<>();
        recruitPage.setRecords(list);
        recruitPage.setTotal(recruits.size());
        return recruitPage;

    }

    /**
     * 添加招聘信息
     * @param recruit
     * @return
     */
    @Override
    public int addRecruit(Recruit recruit) {
        return recruitMapper.insert(recruit);
    }

    /**
     * 根据岗位名查询招聘信息
     * @param positions
     * @return
     */
    @Override
    public IPage<Recruit> selectByNameRecruit(String positions,Long page, Long limit) {
        // 抽取条件构造器
       QueryWrapper<Recruit> comQW1 = new QueryWrapper<>();
        List<Recruit> recruits = recruitMapper.selectList(comQW1.like(true,"positions",positions));
        for (Recruit e : recruits) {
            Company company = companyMapper.selectById(e.getCompanyId());
            e.setCompanyList(company);
        }
        List list = PageUtil.startPage(recruits, page.intValue(), limit.intValue());
        Page<Recruit> recruitPage = new Page<>();
        recruitPage.setRecords(list);
        recruitPage.setTotal(recruits.size());
        return recruitPage;
    }

    /**
     * 修改招聘信息
     * @param recruit
     * @return
     */
    @Override
    public int updateRecruit(Recruit recruit) {
        int size = recruitMapper.updateById(recruit);
        return size;
    }

    /**
     * 删除招聘信息
     * @param id
     * @return
     */
    @Override
    public int delRecruit(Integer id) {
        return recruitMapper.deleteById(id);
    }
}
