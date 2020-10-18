package com.swjd.controller;


import com.swjd.bean.Company;
import com.swjd.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping("/sll")
    public List<Company> grtAll() {
        List<Company> companies = companyMapper.selectList(null);
        return companies;
    }

}

