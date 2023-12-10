package com.example.appweddinghall.service;

import com.example.appweddinghall.entity.SendPhoneNumber;
import com.example.appweddinghall.exception.VerifyException;
import com.example.appweddinghall.payload.MyTokenDTO;
import com.example.appweddinghall.payload.SmsDTO;
import com.example.appweddinghall.utils.MyToken;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SmsService implements SenderService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final RedisTemplate<String, String> redisTemplate;
    private final CodeGenerateServiceImpl codeGenerateService;
    private final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Value("${token.email}")
    public String tokenEmail;

    @Value("${token.password}")
    public String tokenPassword;

    @Value("${token.urls.token-url}")
    private String tokenUrl;

    @Value("${token.base-url}")
    private String baseUrl;

    @Value("${token.urls.sms-send}")
    private String smsUrl;

    @Override
    public void send(String from, String to, String body) {

    }

    @Override
    public void send(String to) {
        SendPhoneNumber sendPhoneNumber = new SendPhoneNumber(to, codeGenerateService.generate(), "4546", "https://example.com");
        sendPhoneNumber(sendPhoneNumber);
    }

    public String getToken() {
        ResponseEntity<MyToken> response = restTemplate.exchange(
                baseUrl + tokenUrl,
                HttpMethod.POST,
                new HttpEntity<>(new MyTokenDTO(tokenEmail, tokenPassword)),
                MyToken.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            MyToken myToken = response.getBody();
            assert myToken != null;
            LOGGER.info(myToken.toString());
            redisTemplate.opsForValue().set("my-token", myToken.myTokenData.token());
            return myToken.myTokenData.token();
        } else {
            return null;
        }
    }

    public void sendPhoneNumber(SendPhoneNumber sendMessage) {
        String token = redisTemplate.opsForValue().get("my-token");
        if (token == null) {
            token = getToken();
            redisTemplate.opsForValue().set("my-token", token, 25, TimeUnit.DAYS);
        }
        LOGGER.info("Token is " + token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);
        httpHeaders.setBearerAuth(token);
        HttpEntity<SendPhoneNumber> request = new HttpEntity<>(sendMessage, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    baseUrl + smsUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                LOGGER.info("Response: " + response.getBody());
                redisTemplate.opsForValue().set(sendMessage.getMobile_phone(), sendMessage.getMessage(), 5, TimeUnit.MINUTES);

            } else {
                LOGGER.info("Failed to send notification");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMessageMatching(SmsDTO smsDTO) throws VerifyException {
        String code = redisTemplate.opsForValue().get(smsDTO.getTo()); //code
        // 998765432 1234 -- set  1234
        if (code == null) {
            throw new VerifyException("Time out");
        }
        return smsDTO.getPassword().equals(code);
    }
}
