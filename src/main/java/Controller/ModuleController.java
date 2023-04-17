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

public class ModuleController {
    public static ActionListener createFilterActionListener(TableRowSorter<DefaultTableModel> sorter, JComboBox<String> semesterComboBox, JComboBox<String> typeComboBox) {
        return e -> {
            String selectedSemester = (String) semesterComboBox.getSelectedItem();
            String selectedType = (String) typeComboBox.getSelectedItem();
            List<RowFilter<Object, Object>> filters = Arrays.asList(filterBySemester(selectedSemester), filterByType(selectedType));
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.andFilter(filters);
            sorter.setRowFilter(combinedFilter);
        };
    }

    public static RowFilter<Object, Object> filterBySemester(String semester) {
        if (semester == null || semester.equals("All")) {
            return RowFilter.regexFilter(".*");
        } else {
            return RowFilter.regexFilter("^" + semester + "$", 4);
        }
    }

    public static RowFilter<Object, Object> filterByType(String type) {
        if (type == null || type.equals("All")) {
            return RowFilter.regexFilter(".*");
        } else {
            return RowFilter.regexFilter("^" + type + "$", 5);
        }
    }

    public static void sortTable(JTable table, int columnIndex, boolean ascending) {
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(columnIndex, ascending ? SortOrder.ASCENDING : SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }

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
        moduleData.addModuleToUser(module, username, lecturerPath);
    }

    public static List<Module> searchAllModules(String term) {
        ModuleData moduleData = ModuleData.getInstance();
        return moduleData.searchFromAllModules(term);
    }

    public static void joinModule(Module module) {
        String username = Config.getUsername();
        Path lecturerPath = Paths.get("src/main/resources/data/lecturers", username, "modules.json");
        ModuleData moduleData = ModuleData.getInstance();

        // add module to lecturer's index
        moduleData.addModuleToUser(module, username, lecturerPath);

        // add lecturer to module info
        module.setLecturer(module.getLecturer() + ", " + username);
        ModuleData.getInstance().saveModuleInfo(module);

        ModuleData.getInstance().updateModules();
    }
}
