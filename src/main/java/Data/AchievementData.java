package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.Config;
import main.java.Entity.Achievement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Operations about the achievement data
 *
 * @author : Yunxin Wang
 * @version : v4.3
 */
public class AchievementData {
    /**
     * achievement lst
     */
    private static AchievementData instance = null;
    public ArrayList<Achievement> achievements;

    /**
     * Constructor of achievement data
     */
    private AchievementData() {
        getAchievements();
    }

    /**
     * @return {@link AchievementData}
     */
    public static AchievementData getInstance() {
        if (instance == null) {
            instance = new AchievementData();
        }

        return instance;
    }

    /**
     * Get achievement instance
     */
    private void getAchievements() {
        String username = Config.getUsername();
        String jsonStr;
        try {
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/data/students/" + username + "/achievements.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        achievements = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Achievement>>() {
        }.getType());
    }
}
