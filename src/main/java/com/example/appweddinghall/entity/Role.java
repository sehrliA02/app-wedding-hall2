package com.example.appweddinghall.entity;

import com.example.appweddinghall.entity.template.AbsAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role extends AbsAuditingEntity {
    @Column(nullable = false, unique = true)
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Permission> permissions;
}
