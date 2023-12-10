package com.example.appweddinghall.entity;

import com.example.appweddinghall.entity.template.AbsAuditingEntity;
import com.example.appweddinghall.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends AbsAuditingEntity {

    // security

    @Column(nullable = false, unique = true)
    String phone;

    @Column(nullable = false)
    String password;

    boolean enabled;

    @ManyToMany
    List<Role> roles;



    // Employee And Customer

    String firstname;

    String lastname;

    @OneToOne
    Address address; // not null for employee

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    String extraContact;

    @OneToOne
    Attachment attachment;

}