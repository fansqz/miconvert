package com.fansos.miconvert;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// @SpringBootApplication
@SpringBootApplication
@MapperScan("com.fansos.miconvert.mapper")
public class MiconvertApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiconvertApplication.class, args);
    }

}
