package com.swjd.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("tb_resume")
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 简历id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 简历名
     */
    private String name;

    /**
     * 简历地址
     */
    private String url;

    /**
     * 学生
     */
    private String studentAccount;

    /**
     * 岗位
     */
    private String positions;

    /**
     * 简历状态(0讲师审核中,1就业老师审核中,2审核通过,3打回)
     */
    private Integer status;

    /**
     * 上传时间
     */
    private Date time;

    /**
     * 公司id
     */
    private Integer companyId;


}
