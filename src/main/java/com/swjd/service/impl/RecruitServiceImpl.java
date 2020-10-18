package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swjd.bean.Company;
import com.swjd.bean.Recruit;
import com.swjd.mapper.RecruitMapper;
import com.swjd.service.RecruitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    // 抽取条件构造器
    private QueryWrapper<Recruit> comQW = new QueryWrapper<>();

    /**
     * 查询招聘信息
     * @return
     */
    @Override
    public List<Recruit> getAllRecruit() {
        Page<Recruit> recruitPage = new Page<>(1, 5);
        return recruitMapper.selectPage(recruitPage, null).getRecords();
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
     * @param name
     * @return
     */
    @Override
    public List<Recruit> selectByNameRecruit(String name) {
        if (name == null) {
            return null;
        }
        Page<Recruit> recruitPage = new Page<>(1, 5);
        return recruitMapper.selectPage(recruitPage, comQW.eq("name", name)).getRecords();
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
