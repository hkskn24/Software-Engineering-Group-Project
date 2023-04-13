package main.java.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.Config;
import main.java.Entity.Module;
import java.util.ArrayList;
import java.util.Map;


public class ModuleData {
    private static ModuleData instance = null;
    public ArrayList<Module> modules;

    private ModuleData() {
        getModuleByCodes();
    }

    private void getModuleByCodes() {
        String username = Config.getUsername();
        String jsonStr = null;
        try {
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/data/students/" + username + "/modules.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> moduleCodes = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<String>>(){}.getType());
        modules = new ArrayList<>();

        if (moduleCodes != null) {
            for (String code : moduleCodes) {
                modules.add(loadModuleByCode(code));
            }
        }
    }

    private Module loadModuleByCode(String code) {
        String jsonStr = null;
        try {
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/data/modules/" + code + "/info.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(jsonStr, Module.class);
    }

    public Module findModuleByName(String moduleName) {
        String jsonStr = null;
        try {
            jsonStr = new String(Files.readAllBytes(Paths.get("src/main.resources/data/modules/index.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Map<String, String>> nameToCodeList = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Map<String, String>>>(){}.getType());

        if (nameToCodeList != null) {
            for (Map<String, String> entry : nameToCodeList) {
                if (entry.get("name").equals(moduleName)) {
                    String code = entry.get("code");
                    return loadModuleByCode(code);
                }
            }
        }

        return null;
    }

    public static ModuleData getInstance() {
        if (instance == null) {
            instance = new ModuleData();
        }

        return instance;
    }
}
