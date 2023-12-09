package com.example.appweddinghall.service;

import com.example.appweddinghall.utils.AppConstant;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeGenerateServiceImpl implements CodeGenerateService {
    private final Random random = new Random();

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < AppConstant.CODE_LENGTH; i++) {
            sb.append(digit());
        }

        return sb.toString();
    }

    private int digit() {
        return random.nextInt(10);
    }
}
