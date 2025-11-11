package com.tp7.flutter.security;

public interface SecParams {
    long EXP_TIME = 10 * 24 * 60 * 60 * 1000; // 10 jours
    String SECRET = "sarra_secret_key";
    String PREFIX = "Bearer ";
}
