package com.example.appweddinghall;

import com.example.appweddinghall.config.PropertyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties(value = {PropertyMapper.class})
@EnableCaching
public class AppWeddingHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppWeddingHallApplication.class, args);
    }

}
