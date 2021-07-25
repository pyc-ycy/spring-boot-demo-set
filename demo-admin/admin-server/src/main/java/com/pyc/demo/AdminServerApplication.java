package com.pyc.demo;
/*
 * @product IntelliJ IDEA
 * @project spring-boot-demo-set
 * @file AdminServerApplication
 * @pack com.pyc.demo
 * @date 2021/7/25
 * @time 14:38
 * @author 御承扬
 * @E-mail 2923616405@qq.com
 **/

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 彭友聪
 * @date 2021/7/25
 */
@EnableAdminServer
@SpringBootApplication
public class AdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
