package com.swjd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Climb.Xu
 * @date 2020/10/19 16:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeUploadVo {
    private String positions; //岗位
    private String studentAccount; //学生账号
    private Integer companyId; //公司id
}
