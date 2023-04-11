package main.java;

public class Config {
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Config.username = username;
    }
}
