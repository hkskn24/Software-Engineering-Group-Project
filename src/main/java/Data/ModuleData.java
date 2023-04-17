package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.Config;
import main.java.Entity.Module;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    public void createModuleFolder(Path modulePath) {
        try {
            Files.createDirectories(modulePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeModuleInfo(Module module, Path infoPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(infoPath.toString())){
            gson.toJson(module, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeGradesJson(Path gradesPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(gradesPath.toString())) {
            gson.toJson(new ArrayList<>(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addModuleToIndex(Module module, Path indexPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type indexType = new TypeToken<List<Map<String, String>>>(){}.getType();
        List<Map<String, String>> index;
        try (Reader reader = new FileReader(indexPath.toString())) {
            index = gson.fromJson(reader, indexType);
            if (index == null) {
                index = new ArrayList<>();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> newIndex = new LinkedHashMap<>();
        newIndex.put("name", module.getName());
        newIndex.put("code", module.getCode());
        index.add(newIndex);

        try (FileWriter writer = new FileWriter(indexPath.toString())) {
            gson.toJson(index, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addModuleToUser(Module module, String username, Path userPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> list;
        try (Reader reader = new FileReader(userPath.toString())) {
            list = gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.add(module.getCode());
        try (FileWriter writer = new FileWriter(userPath.toString())) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateModules() {
        getUserModules();
    }

    public static ModuleData getInstance() {
        if (instance == null) {
            instance = new ModuleData();
        }

        return instance;
    }
}
