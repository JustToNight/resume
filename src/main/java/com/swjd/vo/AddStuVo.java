package com.swjd.vo;

import lombok.Data;

@Data
public class AddStuVo {
    private String account;

    private String password;

    private String fullName;

    private String className;

    private String teacherAccount;

    private String auth;
}
