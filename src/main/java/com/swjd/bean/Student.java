package com.swjd.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Objects;

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
@TableName("tb_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @TableId(value = "account", type = IdType.ID_WORKER)
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 班级
     */
    private String className;


    /**
     * 讲师
     */
    private String teacherAccount;

    /**
     * 权限标识符(a:管理员,b:就业老师,c:讲师,d:学生)
     */

    private String auth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return account.equals(student.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }
}
