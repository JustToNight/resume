package com.swjd.controller;


import com.swjd.bean.Company;
import com.swjd.common.Constant;
import com.swjd.service.CompanyService;
import com.swjd.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    /**
     * 查询所有已开启招聘的公司
     *
     * @return
     */
    @GetMapping("/getAlreadyAll")
    public List<Company> getAll() {
        List<Company> allCompany = companyService.getAllCompany();
        return allCompany;
    }

    /**
     * 增加企业
     */
    @PostMapping("/addCompany")
    public Object addCompany(Company company) {

        companyService.addCompany(company);
        return company;
    }

    /**
     * 根据Id查一个企业
     *
     * @param id
     * @return
     */
    @GetMapping("/selectByIdCompany/{id}")
    public Object selectByIdCompany(@PathVariable("id") Integer id) {
        Company company = companyService.getByIdCompany(id);
        return company;
    }

    /**
     * 修改企业信息
     */
    @PostMapping("/updateCompany")
    public Object updateCompany(@RequestBody Company company) {

/*        company.setName("哇哈哈");
        company.setStatus(Constant.CompanyStatus.START.getCode());
        company.setAddr("中国");
        company.setCompanyDesc("好公司");*/
        companyService.updateCompany(company);
        return company;
    }

    /**
     * 根据Id删除企业
     * @return
     */
    @PostMapping("/delCompany/{id}")
    public R delCompany(@PathVariable("id") Integer id) {
        int size = companyService.delByIdCompany(id);
        return size>0?R.ok("删除成功"):R.error("删除被拒绝");
    }


}

