package com.example.appweddinghall.entity.template;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbsUUIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
}
