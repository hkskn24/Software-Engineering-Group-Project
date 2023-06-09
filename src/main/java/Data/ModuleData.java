package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.Config;
import main.java.Entity.Assessment;
import main.java.Entity.Module;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * Operations about the achievement data
 *
 * @author : Yunxin Wang
 * @version : v4.3
 */
public class ModuleData extends Data {
    private static ModuleData instance = null;
    /**
     * module list
     */
    public ArrayList<Module> modules;

    /**
     * Get module data
     */
    public ModuleData() {
        getUserModules();
    }

    /**
     * @return {@link ModuleData}
     */
    public static ModuleData getInstance() {
        if (instance == null) {
            instance = new ModuleData();
        }

        return instance;
    }

    /**
     * Get the module the user joined
     */
    private void getUserModules() {
        String username = Config.getUsername();
        String userType = Config.getUserType();
        List<String> moduleCodes = readJsonToList("src/main/resources/data/" + userType + "/" + username + "/modules.json", new TypeToken<ArrayList<String>>() {
        }.getType());
        modules = new ArrayList<>();

        if (moduleCodes != null) {
            for (String code : moduleCodes) {
                modules.add(loadModuleByCode(code));
            }
        }
    }

    /**
     * @param code module code
     * @return {@link Module}
     */
    private Module loadModuleByCode(String code) {
        String jsonStr = readFileToString("src/main/resources/data/modules/" + code + "/info.json");
        return new Gson().fromJson(jsonStr, Module.class);
    }

    /**
     * @param newCode new module code
     * @return boolean
     */
    public boolean isCodeUnique(String newCode) {
        String jsonStr = readFileToString("src/main/resources/data/modules/index.json");
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

    /**
     * @param term search term
     * @return module list
     */
    public List<Module> searchFromAllModules(String term) {
        List<Module> result = new ArrayList<>();
        String jsonStr = readFileToString("src/main/resources/data/modules/index.json");
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

    /**
     * @param modulePath module file path
     */
    public void createModuleFolder(Path modulePath) {
        try {
            Files.createDirectories(modulePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param module module object
     */
    public void saveModuleInfo(Module module) {
        String code = module.getCode();
        String path = "src/main/resources/data/modules";
        Path infoPath = Paths.get(path, code, "info.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(infoPath.toString())) {
            gson.toJson(module, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param gradesPath grades file path
     */
    public void initializeGradesJson(Path gradesPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(gradesPath.toString())) {
            gson.toJson(new ArrayList<>(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param module new module
     * @param indexPath index file path
     */
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

    /**
     * @param module new module
     * @param userPath user file path
     */
    public void addModuleToUser(Module module, Path userPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
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

    /**
     * @return module list
     */
    public List<Module> getOngoingModules() {
        List<Module> ongoingModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getStatus().equalsIgnoreCase("ongoing")) {
                ongoingModules.add(module);
            }
        }
        return ongoingModules;
    }

    /**
     * @return module list
     */
    public List<Module> getCompletedModules() {
        List<Module> completedModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getStatus().equalsIgnoreCase("completed")) {
                completedModules.add(module);
            }
        }
        return completedModules;
    }


    /**
     *
     */
    public void updateModules() {
        getUserModules();
    }

    /**
     * @param module module
     */
    public void updateModuleStatus(Module module) {
        module.setStatus("completed");
        saveModuleInfo(module);
    }

    /**
     * @param assessment new assessment
     */
    public void addAssessment(Assessment assessment) {
        List<Assessment> assessments = loadAssessments();
        assessments.add(assessment);
        saveAssessments(assessments);
    }

    /**
     * @return assessment list
     */
    public List<Assessment> loadAssessments() {
        String filePath = "src/main/resources/data/assessments.json";
        Type type = new TypeToken<List<Assessment>>() {}.getType();

        List<String> codes = new ArrayList<>();
        for (Module module : modules) {
            codes.add(module.getCode());
        }

        List<Assessment> assessments = readJsonToList(filePath, type);
        List<Assessment> studentAssessments = new ArrayList<>();
        for (Assessment assessment : assessments) {
            if (codes.contains(assessment.getCode())) {
                studentAssessments.add(assessment);
            }
        }

        return studentAssessments;
    }

    /**
     * @param assessments assessments list
     */
    private void saveAssessments(List<Assessment> assessments) {
        String filePath = "src/main/resources/data/assessments.json";
        saveJsonToFile(filePath, assessments);
    }
}
