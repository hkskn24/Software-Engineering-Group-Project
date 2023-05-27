package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.Config;
import main.java.Entity.Achievement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AchievementData {
    private static AchievementData instance = null;
    public ArrayList<Achievement> achievements;

    private AchievementData() {
        getAchievements();
    }

    public static AchievementData getInstance() {
        if (instance == null) {
            instance = new AchievementData();
        }

        return instance;
    }

    private void getAchievements() {
        String username = Config.getUsername();
        String jsonStr = null;
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/students/" + username + "/achievements.json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            jsonStr = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            // 处理读取文件时的异常
            e.printStackTrace();
        }

        achievements = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Achievement>>() {
        }.getType());
    }
}
