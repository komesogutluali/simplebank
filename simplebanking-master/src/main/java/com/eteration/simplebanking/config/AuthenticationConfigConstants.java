package com.eteration.simplebanking.config;

public class AuthenticationConfigConstants {
    public static final String SECRET = "simple_bank";
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/user";
}
