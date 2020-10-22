package com.swjd.service;

import com.swjd.bean.Company;
import com.swjd.bean.Recruit;
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
public interface RecruitService extends IService<Recruit> {

    /**
     * 查询招聘信息
     * @return
     */
    List<Recruit> getAllRecruit();

    /**
     * 添加招聘信息
     * @param recruit
     * @return
     */
    int addRecruit(Recruit recruit);

    /**
     * 根据岗位名查询招聘信息
     * @param positions
     * @return
     */
    List<Recruit> selectByNameRecruit(String positions);

    /**
     * 修改招聘信息
     * @param recruit
     * @return
     */
    int updateRecruit(Recruit recruit);

    /**
     * 删除招聘信息
     * @param id
     * @return
     */
    int delRecruit(Integer id);

}
