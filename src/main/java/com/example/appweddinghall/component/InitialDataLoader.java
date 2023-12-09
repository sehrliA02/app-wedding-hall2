package com.example.appweddinghall.component;

import com.example.appweddinghall.config.PropertyMapper;
import com.example.appweddinghall.entity.Permission;
import com.example.appweddinghall.entity.Role;
import com.example.appweddinghall.entity.User;
import com.example.appweddinghall.enums.PermissionEnum;
import com.example.appweddinghall.repository.PermissionRepository;
import com.example.appweddinghall.repository.RoleRepository;
import com.example.appweddinghall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class InitialDataLoader implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final PropertyMapper propertyMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (!"create".equals(ddl))
            return;

        List<Permission> permissionList = Arrays.stream(PermissionEnum.values()).map(Permission::new).toList();
        List<Permission> permissions = permissionRepository.saveAll(permissionList);

        Role superAdminRole = propertyMapper.getRole();
        superAdminRole.setPermissions(permissions);

        roleRepository.save(superAdminRole);

        User user = propertyMapper.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setRoles(List.of(superAdminRole));

        userRepository.save(user);
    }
}
