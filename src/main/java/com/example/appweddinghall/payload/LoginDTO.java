package com.example.appweddinghall.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.example.appweddinghall.utils.AppConstant.MAX_PSW_SIZE;
import static com.example.appweddinghall.utils.AppConstant.MIN_PSW_SIZE;

public record LoginDTO(@NotBlank String phone,
                       @NotBlank @Size(min = MIN_PSW_SIZE, max = MAX_PSW_SIZE) String password) {

}
