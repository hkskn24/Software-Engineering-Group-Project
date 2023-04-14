package main.java.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.Config;
import main.java.Entity.Module;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ModuleData {
    private static ModuleData instance = null;
    public ArrayList<Module> modules;

    private ModuleData() {
        getUserModules();
    }

    private void getUserModules() {
        String username = Config.getUsername();
        String userType = Config.getUserType();
        List<String> moduleCodes = readJsonToList("src/main/resources/data/" + userType + "/" + username + "/modules.json", new TypeToken<ArrayList<String>>(){}.getType());
        modules = new ArrayList<>();

        if (moduleCodes != null) {
            for (String code : moduleCodes) {
                modules.add(loadModuleByCode(code));
            }
        }
    }

    private Module loadModuleByCode(String code) {
        String jsonStr = readFileToString("src/main/resources/data/modules/" + code + "/info.json");
        return new Gson().fromJson(jsonStr, Module.class);
    }

    public List<Module> searchFromAllModules(String term) {
        List<Module> result = new ArrayList<>();
        String jsonStr = readFileToString("src/main/resources/data/modules/index.json");
        ArrayList<Map<String, String>> nameToCode = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Map<String, String>>>(){}.getType());

        if (nameToCode != null) {
            for (Map<String, String> entry : nameToCode) {
                String name = entry.get("name");
                String code = entry.get("code");

                if (name.toLowerCase().contains(term.toLowerCase())) {
                    Module module = loadModuleByCode(code);
                    result.add(module);
                }
            }
        }

        return result;
    }

    private String readFileToString(String filePath) {
        String file = null;

        try {
            file = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private <T> T readJsonToList(String filePath, Type type) {
        String jsonStr = readFileToString(filePath);
        return new Gson().fromJson(jsonStr, type);
    }

    public static ModuleData getInstance() {
        if (instance == null) {
            instance = new ModuleData();
        }

        return instance;
    }
}
