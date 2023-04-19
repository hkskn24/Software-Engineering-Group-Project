package main.java;

public class Config {
    private static String username;
    private static String userType;
    private static String userId;
    private static String userEmail;

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

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        Config.userEmail = userEmail;
    }
}
