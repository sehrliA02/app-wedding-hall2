package com.example.appweddinghall.payload;

import jakarta.validation.constraints.NotBlank;

public record PhoneDTO(String phone, @NotBlank String generatedCode) {
}
