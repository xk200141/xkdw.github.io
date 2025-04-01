package com.dwh.dpicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.dwh.dpicturebackend.mapper")
public class DPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DPictureBackendApplication.class, args);
    }

}
