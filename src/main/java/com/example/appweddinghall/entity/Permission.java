package com.example.appweddinghall.entity;

import com.example.appweddinghall.enums.PermissionEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@RequiredArgsConstructor
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    PermissionEnum name;

    public Permission(PermissionEnum name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}
