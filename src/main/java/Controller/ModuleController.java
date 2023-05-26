package main.java.Controller;

import main.java.Config;
import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller about the modules
 *
 * @author : Yunxin Wang
 * @version : v4.3
 */
public class ModuleController {
    /**
     * Create the filters of grades page
     *
     * @param sorter           sorter of the modules
     * @param semesterComboBox comboBox to choose semesters
     * @param typeComboBox     comboBox to choose types of modules
     * @return {@link ActionListener}
     */
    public static ActionListener createFilterActionListener(TableRowSorter<DefaultTableModel> sorter, JComboBox<String> semesterComboBox, JComboBox<String> typeComboBox) {
        return e -> {
            String selectedSemester = (String) semesterComboBox.getSelectedItem();
            String selectedType = (String) typeComboBox.getSelectedItem();
            List<RowFilter<Object, Object>> filters = Arrays.asList(filterBySemester(selectedSemester), filterByType(selectedType));
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.andFilter(filters);
            sorter.setRowFilter(combinedFilter);
        };
    }

    /**
     * Filter the modules by semester
     *
     * @param semester semesters
     * @return RowFilter
     */
    public static RowFilter<Object, Object> filterBySemester(String semester) {
        if (semester == null || semester.equals("All")) {
            return RowFilter.regexFilter(".*");
        } else {
            return RowFilter.regexFilter("^" + semester + "$", 4);
        }
    }

    /**
     * Filter the achievements by type
     *
     * @param type types
     * @return RowFilter
     */
    public static RowFilter<Object, Object> filterByType(String type) {
        if (type == null || type.equals("All")) {
            return RowFilter.regexFilter(".*");
        } else {
            return RowFilter.regexFilter("^" + type + "$", 5);
        }
    }

    /**
     * Search module by name from modules the user has joined
     *
     * @param modules user's modules
     * @param term    search term
     * @return module list
     */
    public static List<Module> searchMoules(List<Module> modules, String term) {
        List<Module> result = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCode().toLowerCase().contains(term.toLowerCase()) ||
                    module.getName().toLowerCase().contains(term.toLowerCase())) {
                result.add(module);
            }
        }
        return result;
    }

    /**
     * Add new modules
     *
     * @param module   new module
     * @param username the name of the user
     */
    public static void addModule(Module module, String username) {
        String code = module.getCode();
        String path = "src/main/resources/data";

        Path modulesPath = Paths.get(path, "modules", code);
        Path gradesPath = Paths.get(path, "modules", code, "grades.json");
        Path indexPath = Paths.get(path, "modules", "index.json");
        Path lecturerPath = Paths.get(path, "lecturers", username, "modules.json");

        ModuleData moduleData = ModuleData.getInstance();
        moduleData.createModuleFolder(modulesPath);
        moduleData.saveModuleInfo(module);
        moduleData.initializeGradesJson(gradesPath);
        moduleData.addModuleToIndex(module, indexPath);
        moduleData.addModuleToUser(module, lecturerPath);
    }

    /**
     * Search module by name from all modules
     *
     * @param term search term
     * @return module list
     */
    public static List<Module> searchAllModules(String term) {
        ModuleData moduleData = ModuleData.getInstance();
        return moduleData.searchFromAllModules(term);
    }

    /**
     * Add new lecturer to a module
     *
     * @param module the module user want to join
     */
    public static void joinModule(Module module) {
        String username = Config.getUsername();
        Path lecturerPath = Paths.get("src/main/resources/data/lecturers", username, "modules.json");
        ModuleData moduleData = ModuleData.getInstance();

        // add module to lecturer's index
        moduleData.addModuleToUser(module, lecturerPath);

        // add lecturer to module info
        module.setLecturer(module.getLecturer() + ", " + username);
        ModuleData.getInstance().saveModuleInfo(module);

        ModuleData.getInstance().updateModules();
    }

    /**
     * Check whether the code is unique
     *
     * @param code code of new module
     * @return boolean
     */
    public static boolean isCodeUnique(String code) {
        return ModuleData.getInstance().isCodeUnique(code);
    }
}
