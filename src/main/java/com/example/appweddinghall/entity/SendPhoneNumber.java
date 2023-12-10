package com.example.appweddinghall.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendPhoneNumber {
    private String mobile_phone;
    private String message;
    private String from = "4546";
    private String callback_url;
}