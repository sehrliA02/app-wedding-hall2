package com.example.appweddinghall.config;

import com.example.appweddinghall.entity.Role;
import com.example.appweddinghall.entity.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class PropertyMapper {
    User user;
    Role role;
}
