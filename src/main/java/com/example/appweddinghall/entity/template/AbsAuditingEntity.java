package com.example.appweddinghall.entity.template;

import com.example.appweddinghall.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbsAuditingEntity extends AbsTimeEntity {

    @CreatedBy
    @ManyToOne
    User createdBy;

    @LastModifiedBy
    @ManyToOne
    User updatedBy;

}
