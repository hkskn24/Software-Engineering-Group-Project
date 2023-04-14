package main.java;

public class Config {
    private static String username;
    private static String userType;

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        Config.userType = userType;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Config.username = username;
    }
}
