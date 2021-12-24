package com.offcn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.offcn.mapper")
@SpringBootApplication
public class LayuidemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayuidemoApplication.class, args);
    }

}
