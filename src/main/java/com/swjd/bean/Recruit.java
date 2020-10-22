package com.swjd.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_recruit")
public class Recruit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 招聘的岗位
     */
    private String positions;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 招聘时间
     */
    private String time;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 招聘人数
     */
    private Integer peopleNum;

    /**
     * 招聘描述
     */
    private String recruitDesc;

    /**
     * 公司文档地址
     */
    private String url;

    /**
     * 面试地点
     */
    private String place;

    /**
     * 面试时间
     */
    private String interviewTime;

}
