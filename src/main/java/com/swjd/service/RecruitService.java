package com.swjd.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swjd.bean.Company;
import com.swjd.bean.Recruit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.vo.CompanyVo;
import org.springframework.web.multipart.MultipartFile;

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
    IPage<Recruit> getAllRecruit(Long page, Long limit);

    /**
     * 添加招聘信息
     * @param recruit
     * @return
     */
    Integer addRecruit(MultipartFile file, Recruit recruit);

    /**
     * 根据岗位名查询招聘信息
     * @param positions
     * @return
     */
    IPage<Recruit> selectByNameRecruit(String positions,Long page, Long limit);

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
