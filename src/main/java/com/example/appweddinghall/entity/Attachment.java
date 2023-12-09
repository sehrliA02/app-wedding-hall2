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
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attachment extends AbsUUIDEntity {
    @Column(nullable = false)
    String originalName;

    long size;

    @Column(nullable = false)
    String contentType;

    @Column(nullable = false)
    String url;
}
