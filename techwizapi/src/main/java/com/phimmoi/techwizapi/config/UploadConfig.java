package com.phimmoi.techwizapi.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "db3xspvbn",
                "api_key", "241562772911969",
                "api_secret", "JllOKldAz9mL_HbDLm6v6GpaVO0",
                "secure", true
        ));
    }
}
