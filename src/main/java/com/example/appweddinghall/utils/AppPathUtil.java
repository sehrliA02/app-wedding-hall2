package com.example.appweddinghall.utils;

public interface AppPathUtil {
    interface Version {
        String VERSION_1 = "/v1";
        String VERSION_2 = "/v2";
        String VERSION_3 = "/v3";
    }

    String VERSION = Version.VERSION_1;
    String MAIN = "/api";
    String BASE = MAIN + VERSION;
}
