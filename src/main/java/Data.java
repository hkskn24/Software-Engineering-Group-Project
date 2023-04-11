package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.Entity.Achievement;
import main.java.Entity.Module;

import java.util.ArrayList;


public class Data {
    private static Data instance = null;
    public ArrayList<Module> modules;
    public ArrayList<Achievement> achievements;


    private Data() {
        getModules();
        getAchievements();
    }

    private void getModules() {
        String username = Config.getUsername();
        String jsonStr = null;
        try {
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/students/" + username + "/module.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        modules = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Module>>(){}.getType());
    }

    private void getAchievements() {
        String username = Config.getUsername();
        String jsonStr = null;
        try {
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/students/" + username + "/achievement.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        achievements = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Achievement>>(){}.getType());
    }

    public static Data getInstance() {
        if(instance == null) {
            instance = new Data();
        }
        return instance;
    }
}
