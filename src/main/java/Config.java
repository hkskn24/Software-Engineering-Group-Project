package main.java;

public class Config {
    private static String username;
    private static String userType;
    private static String userId;

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

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        Config.userId = userId;
    }
}
