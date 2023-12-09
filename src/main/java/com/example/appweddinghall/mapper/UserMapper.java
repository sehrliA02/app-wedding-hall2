package com.example.appweddinghall.mapper;

import com.example.appweddinghall.entity.User;
import com.example.appweddinghall.payload.RegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserFromRegisterDTO(RegisterDTO registerDTO);
}
