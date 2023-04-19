package main.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.Entity.Module;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Lec_Data {
    private static Lec_Data instance = null;
    public ArrayList<Module> modules;

    private Lec_Data() {
        getModules();
    }

    public static Lec_Data getInstance() {
        if (instance == null) {
            instance = new Lec_Data();
        }
        return instance;
    }

    private void getModules() {
        String username = Config.getUsername();
        String jsonStr = null;
        try {
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/data/lecturers/" + username + "/modules.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        modules = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Module>>() {
        }.getType());
    }
}
