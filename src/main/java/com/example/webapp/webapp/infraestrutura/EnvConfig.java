package com.example.webapp.webapp.infraestrutura;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String get(String key) {
        String value = dotenv.get(key);

        if (value == null) {
            value = System.getenv(key);
        }

        return value;
    }
}