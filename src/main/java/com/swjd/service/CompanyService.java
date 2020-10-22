package com.swjd.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swjd.bean.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.util.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
     * 查询所有公司
     * @param page
     * @param limit
     * @return
     */
    IPage<Company> getAllCompany(Long page,Long limit);


    /**
     * 查询所有已开启招聘的公司
     * @return
     */
    List<Company> getAlreadyAll();

    /**
     * 查询所有未开启招聘的公司
     * @return
     */
    IPage<Company> getNotAll(Long page, Long limit);

    /**
     * 添加一个企业
     * @return
     */
    Integer addCompany(Company company);

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

    void downloadById(Integer companyId, HttpServletResponse response);
}
