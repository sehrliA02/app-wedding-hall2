package com.example.appweddinghall;

import com.example.appweddinghall.config.PropertyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {PropertyMapper.class})
public class AppWeddingHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWeddingHallApplication.class, args);
    }

}
