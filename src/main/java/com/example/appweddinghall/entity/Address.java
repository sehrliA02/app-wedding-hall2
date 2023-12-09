package com.example.appweddinghall.entity;

import com.example.appweddinghall.entity.template.AbsUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Address extends AbsUUIDEntity {
    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String street;

    @Column(nullable = false)
    String home;
}
