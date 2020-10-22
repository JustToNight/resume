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
 * @since 2020-10- 18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司描述
     */
    private String companyDesc;

    /**
     * 公司地址
     */
    private String addr;

    /**
     * 公司状态
     */
    private Integer status;

    /**
     * 公司文档地址
     */
    private String url;


}
