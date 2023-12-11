package com.example.appweddinghall.controller;

import com.example.appweddinghall.exception.VerifyException;
import com.example.appweddinghall.payload.SmsDTO;
import com.example.appweddinghall.service.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final SmsService smsService;

    @PostMapping(value = "/send-notification")
    public ResponseEntity<String> sendNotification() {
        if (smsService.getToken() != null) {
            return ResponseEntity.ok("Notification sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to send notification");
        }
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<String> verify(@RequestBody @Valid SmsDTO smsDTO){
        try {
            if (smsService.isMessageMatching(smsDTO)) {
                return ResponseEntity.ok("Verification successful");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Verification failed");
            }
        } catch (VerifyException e) {
            throw new RuntimeException(e);
        }
    }
}