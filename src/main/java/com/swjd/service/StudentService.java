package com.swjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swjd.bean.Student;
import com.swjd.util.R;
import com.swjd.vo.AddStuVo;
import com.swjd.vo.UpdStuPwdVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 凌空
 * @since 2020-10-18
 */
public interface StudentService extends IService<Student> {

    //分页查询
    List<Student> getAllStu(String page, String count);

    //    添加学生
    R insert(AddStuVo student);

    //根据id查询学生
    R getbyAccount(String account);

    //删除某个学生
    int delById(String id);

    //修改密码
    R UpdStuPwd(UpdStuPwdVo updStuPwdVo);

    List<Student> selByName(String fullName);
}
