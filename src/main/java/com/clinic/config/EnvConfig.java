package com.clinic.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv;
    private static EnvConfig instance;

    static {
        dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        setSystemProperties();
    }

    private EnvConfig() {
    }

    public static EnvConfig getInstance() {
        if (instance == null) {
            instance = new EnvConfig();
        }
        return instance;
    }

    private static void setSystemProperties() {
        System.setProperty("DB_HOST", getEnv("DB_HOST", "localhost"));
        System.setProperty("DB_PORT", getEnv("DB_PORT", "3306"));
        System.setProperty("DB_NAME", getEnv("DB_NAME", "mydb"));
        System.setProperty("DB_USER", getEnv("DB_USER", "root"));
        System.setProperty("DB_PASSWORD", getEnv("DB_PASSWORD", ""));
    }

    public static String getEnv(String key) {
        String value = dotenv.get(key);
        if (value == null) {
            throw new IllegalStateException("Required environment variable not found: " + key);
        }
        return value;
    }

    public static String getEnv(String key, String defaultValue) {
        String value = dotenv.get(key);
        return value != null ? value : defaultValue;
    }

    public static int getEnvAsInt(String key, int defaultValue) {
        String value = dotenv.get(key);
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            System.err.println("Warning: Invalid integer value for " + key + ", using default: " + defaultValue);
            return defaultValue;
        }
    }

    public static boolean getEnvAsBoolean(String key, boolean defaultValue) {
        String value = dotenv.get(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static String getDbHost() {
        return getEnv("DB_HOST", "localhost");
    }

    public static int getDbPort() {
        return getEnvAsInt("DB_PORT", 3306);
    }

    public static String getDbName() {
        return getEnv("DB_NAME", "mydb");
    }

    public static String getDbUser() {
        return getEnv("DB_USER", "root");
    }

    public static String getDbPassword() {
        return getEnv("DB_PASSWORD", "");
    }

    public static String getDbUrl() {
        return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                getDbHost(),
                getDbPort(),
                getDbName());
    }

    public static int getAppPort() {
        return getEnvAsInt("APP_PORT", 7000);
    }

    public static String getAppEnvironment() {
        return getEnv("APP_ENV", "development");
    }

    public static boolean validateRequiredEnvVars() {
        String[] requiredVars = {"DB_HOST", "DB_PORT", "DB_NAME", "DB_USER"};
        boolean allPresent = true;

        for (String var : requiredVars) {
            if (dotenv.get(var) == null) {
                System.err.println("Missing required environment variable: " + var);
                allPresent = false;
            }
        }

        return allPresent;
    }

    public static void printConfiguration() {
        System.out.println("=== Environment Configuration ===");
        System.out.println("DB_HOST: " + getDbHost());
        System.out.println("DB_PORT: " + getDbPort());
        System.out.println("DB_NAME: " + getDbName());
        System.out.println("DB_USER: " + getDbUser());
        System.out.println("DB_PASSWORD: " + maskPassword(getDbPassword()));
        System.out.println("APP_PORT: " + getAppPort());
        System.out.println("APP_ENV: " + getAppEnvironment());
        System.out.println("================================\n");
    }

    private static String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "<empty>";
        }
        return "****" + password.substring(Math.max(0, password.length() - 2));
    }
}
