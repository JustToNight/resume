package com.swjd.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class AddResumeVo {
    private MultipartFile file;

    private String studentAccount;

    private String positions;

    private String status;

    private Integer companyId;
}
