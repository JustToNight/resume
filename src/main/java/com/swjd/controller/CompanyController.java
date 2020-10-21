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
     * 查询所有已开启招聘的公司(分页)
     * @return
     */
    @GetMapping("/getAlreadyAll")
    public R getAll() {
        List<Company> allCompany = companyService.getAlreadyAll();
        return allCompany!=null?R.ok().put("data",allCompany):R.error("查询失败");
    }

//    /**
//     * 查询所有未开启招聘的公司(分页)
//     *
//     * @return
//     */
//    @GetMapping("/getNotAll")
//    public R getNotAll() {
//        List<Company> allCompany = companyService.getNotAll();
//        return allCompany!=null?R.ok().put("data",allCompany):R.error("查询失败");
//    }


    /**
     * 添加企业
     * @param company
     * @return
     */
    @PostMapping("/addCompany")
    public R addCompany(@RequestBody Company company) {
        if (company.getName() == null) {
            return R.error("输入公司信息不能为空");
        }
        int size = companyService.addCompany(company);
        return size>0?R.ok("增加成功"):R.error("增加失败");
    }

    /**
     * 根据Id查一个企业
     *
     * @param id
     * @return
     */
    @GetMapping("/selectByIdCompany/{id}")
    public Object selectByIdCompany(@PathVariable("id") Integer id) {
        if (id == null) {
            return null;
        }
        Company company = companyService.getByIdCompany(id);
        return company!=null?R.ok().put("data",company):R.error("根据Id查企业失败");
    }

    /**
     * 修改企业信息
     */
    @PostMapping("/updateCompany")
    public R updateCompany(@RequestBody Company company) {
        if (company.getName() == null) {
            return null;
        }
        company.setId(9);
        company.setName("HX");
        company.setStatus(1);
        company.setAddr("地球");
        int size = companyService.updateCompany(company);
        return size>0?R.ok("修改成功"):R.error("修改失败");
    }

    /**
     * 根据Id删除企业
     * @return
     */
    @PostMapping("/delCompany/{id}")
    public R delCompany(@PathVariable("id") Integer id) {
        if (id == null) {
            return null;
        }
        int size = companyService.delByIdCompany(id);
        return size>0?R.ok("删除成功"):R.error("删除失败");
    }




}

