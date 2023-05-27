package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.Config;
import main.java.Entity.Assessment;
import main.java.Entity.Module;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ModuleData extends Data {
    private static ModuleData instance = null;
    public ArrayList<Module> modules;

    public ModuleData() {
        getUserModules();
    }

    public static ModuleData getInstance() {
        if (instance == null) {
            instance = new ModuleData();
        }

        return instance;
    }

    private void getUserModules() {
        String username = Config.getUsername();
        String userType = Config.getUserType();
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/" + userType + "/" + username + "/modules.json");
        List<String> moduleCodes = readJsonToList(inputStream, new TypeToken<ArrayList<String>>() {
        }.getType());
        modules = new ArrayList<>();

        if (moduleCodes != null) {
            for (String code : moduleCodes) {
                modules.add(loadModuleByCode(code));
            }
        }
    }

    private Module loadModuleByCode(String code) {
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/modules/" + code + "/info.json");
        if (inputStream != null) {
            return new Gson().fromJson(new InputStreamReader(inputStream), Module.class);
        }
        return null;
    }

    public boolean isCodeUnique(String newCode) {
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/modules/index.json");
        String jsonStr = readFileToString(inputStream);

        ArrayList<Map<String, String>> nameToCode = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType());

        if (nameToCode != null) {
            for (Map<String, String> entry : nameToCode) {
                String code = entry.get("code");

                if (Objects.equals(code, newCode)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Module> searchFromAllModules(String term) {
        List<Module> result = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/modules/index.json");
        String jsonStr = readFileToString(inputStream);
        ArrayList<Map<String, String>> nameToCode = new Gson().fromJson(jsonStr, new TypeToken<ArrayList<Map<String, String>>>() {
        }.getType());

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

    public void createModuleFolder(Path modulePath) {
        try {
            Files.createDirectories(modulePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveModuleInfo(Module module) {
        String code = module.getCode();
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/modules");
        String path = String.valueOf(inputStream);
        Path infoPath = Paths.get(path, code, "info.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(infoPath.toString())) {
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
        Type indexType = new TypeToken<List<Map<String, String>>>() {
        }.getType();
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

    public void addModuleToUser(Module module, InputStream inputStream) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        List<String> list;
        try (Reader reader = new InputStreamReader(inputStream)) {
            list = gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.add(module.getCode());
        try (FileWriter writer = new FileWriter(String.valueOf(inputStream))) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Module> getOngoingModules() {
        List<Module> ongoingModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getStatus().equalsIgnoreCase("ongoing")) {
                ongoingModules.add(module);
            }
        }
        return ongoingModules;
    }

    public List<Module> getCompletedModules() {
        List<Module> completedModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getStatus().equalsIgnoreCase("completed")) {
                completedModules.add(module);
            }
        }
        return completedModules;
    }


    public void updateModules() {
        getUserModules();
    }

    public void updateModuleStatus(Module module) {
        module.setStatus("completed");
        saveModuleInfo(module);
    }

    public void addAssessment(Assessment assessment) {
        List<Assessment> assessments = loadAssessments();
        assessments.add(assessment);
        saveAssessments(assessments);
    }

    public List<Assessment> loadAssessments() {
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/assessments.json");
        Type type = new TypeToken<List<Assessment>>() {
        }.getType();

        List<String> codes = new ArrayList<>();
        for (Module module : modules) {
            codes.add(module.getCode());
        }

        List<Assessment> assessments = readJsonToList(inputStream, type);
        List<Assessment> studentAssessments = new ArrayList<>();
        for (Assessment assessment : assessments) {
            if (codes.contains(assessment.getCode())) {
                studentAssessments.add(assessment);
            }
        }

        return studentAssessments;
    }

    private void saveAssessments(List<Assessment> assessments) {
        InputStream inputStream = getClass().getResourceAsStream("/main/resources/data/assessments.json");
        saveJsonToFile(inputStream, assessments);
    }
}
