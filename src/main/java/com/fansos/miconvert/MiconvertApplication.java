package com.fansos.miconvert;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


// @ServletComponentScan(basePackages = "com.fansos.miconvert.filter")
@SpringBootApplication
@MapperScan("com.fansos.miconvert.mapper")
public class MiconvertApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiconvertApplication.class, args);
    }
    // /**
    //  * Spring Security权限认证框架 封装的密码加密工具类
    //  *
    //  * @return
    //  */
    // @Bean
    // public BCryptPasswordEncoder getBCryptPasswordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }
}
