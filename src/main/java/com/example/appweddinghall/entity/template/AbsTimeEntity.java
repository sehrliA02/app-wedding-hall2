package com.example.appweddinghall.entity.template;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbsTimeEntity extends AbsUUIDEntity {
    @CreatedDate
    Timestamp createdAt;

    @LastModifiedDate
    Timestamp updatedAt;
}
