package com.swjd.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author Climb.Xu
 * @date 2020/10/19 14:51
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 置文件大小限制 ,超出此大小页面会抛出异常信息
        factory.setMaxFileSize(DataSize.parse("20MB")); //KB,MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("20MB"));
        // 设置文件临时文件夹路径
        // factory.setLocation("E://test//");
        // 如果文件大于这个值，将以文件的形式存储，如果小于这个值文件将存储在内存中，默认为0
        // factory.setMaxRequestSize(0);
        return factory.createMultipartConfig();
    }
}

