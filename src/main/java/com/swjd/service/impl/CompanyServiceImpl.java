package com.swjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swjd.bean.Company;
import com.swjd.bean.Recruit;
import com.swjd.common.Constant;
import com.swjd.mapper.CompanyMapper;
import com.swjd.mapper.RecruitMapper;
import com.swjd.service.CompanyService;
import com.swjd.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Value("${web.upload-path}")
    private String path;
    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private RecruitMapper recruitMapper;

    // 提取条件构造器
    QueryWrapper<Company> comQW = new QueryWrapper<>();

    @Override
    public IPage<Company> getAllCompany(Long page, Long limit) {
        Page<Company> companyPage = new Page<>(page, limit);
        IPage<Company> companyIPage = companyMapper.selectPage(companyPage, null);
        if (companyIPage == null || companyIPage.getSize() == 0) {
            return null;
        }
        return companyIPage;
    }

    /**
     * 查询所有已开启招聘的公司
     *
     * @return
     */
    @Override
    public List<Company> getAlreadyAll() {
        List<Company> companies = companyMapper.selectList(comQW.eq("status", Constant.CompanyStatus.START.getCode()));
        if (companies == null || companies.size() == 0) {
            return null;
        }
        return companies;
    }

    /**
     * 查询所有未开启招聘的公司
     *
     * @return
     */
    @Override
    public IPage<Company> getNotAll(Long page, Long limit) {
        QueryWrapper<Company> comQW = new QueryWrapper<>();
        Page<Company> companyPage = new Page<>(page, limit);
        IPage<Company> companyIPage = companyMapper.selectPage(companyPage, comQW.eq("status", Constant.CompanyStatus.CREATE.getCode()));
        if (companyIPage == null || companyIPage.getSize() == 0) {
            return null;
        }
        return companyIPage;
    }

    /**
     * 添加一个企业
     *
     * @return
     */
    @Override
    public Integer addCompany(Company company) {
        int size = companyMapper.insert(company);
        return size;
    }

    /**
     * 根据Id查询企业
     *
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
     *
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
     *
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

    @Override
    public void downloadById(Integer companyId, HttpServletResponse response) {
        Company company = this.baseMapper.selectById(companyId);
        String url = company.getUrl();
        File file = null;
        try {
            file = new File(url);
        } catch (Exception e) {
            System.out.println("文件不存在");
            return;
        }
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        String filename = null;
        try {
            filename = new String(file.getName().getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/octet-stream");// 二进制流，不知道下载文件的类型
        response.setHeader("content-type", "application/octet-stream");// 让服务器告诉浏览器它发送的数据属于什么文件类型
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名（这个信息头会告诉浏览器这个文件的名字和类型）\
        OutputStream outputStream = null;// 文件输出流
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtils.download(file, outputStream);
    }


}
