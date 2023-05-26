package main.java;

/**
 * Save the id and name of the user
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class Config {
    private static String username;
    private static String userType;
    private static String userId;

    /**
     * @return {@link String}
     */
    public static String getUserType() {
        return userType;
    }

    /**
     * @param userType user type
     */
    public static void setUserType(String userType) {
        Config.userType = userType;
    }

    /**
     * @return {@link String}
     */
    public static String getUsername() {
        return username;
    }

    /**
     * @param username user name
     */
    public static void setUsername(String username) {
        Config.username = username;
    }

    /**
     * @return {@link String}
     */
    public static String getUserId() {
        return userId;
    }

    /**
     * @param userId user id
     */
    public static void setUserId(String userId) {
        Config.userId = userId;
    }
}
