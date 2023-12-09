package com.example.appweddinghall.payload;

import com.example.appweddinghall.enums.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.example.appweddinghall.utils.AppConstant.MAX_PSW_SIZE;
import static com.example.appweddinghall.utils.AppConstant.MIN_PSW_SIZE;

public record RegisterDTO(@NotBlank String phone,
                          @NotBlank @Size(min = MIN_PSW_SIZE, max = MAX_PSW_SIZE) String password,
                          @NotBlank @Size(min = MIN_PSW_SIZE, max = MAX_PSW_SIZE) String prePassword,
                          @NotBlank String firstName,
                          String lastName,
                          @NotNull GenderEnum genderEnum) {
}
