package com.example.appweddinghall.payload;

public record TokenDTO(String access,
                       String refresh) {
    public static TokenDTO instance(String access, String refresh) {
        return new TokenDTO(access, refresh);
    }
}
