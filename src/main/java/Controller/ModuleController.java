package main.java.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        Path infoPath = Paths.get(path, "modules", code, "info.json");
        Path gradesPath = Paths.get(path, "modules", code, "grades.json");
        Path indexPath = Paths.get(path, "modules", "index.json");
        Path lecturerPath = Paths.get(path, "lecturers", username, "modules.json");

        // create the module folder directory
        try {
            Files.createDirectories(modulesPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // write module information into info.json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(infoPath);
        try (FileWriter writer = new FileWriter(infoPath.toString())){
            gson.toJson(module, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // initial grades.json
        try (FileWriter writer = new FileWriter(gradesPath.toString())){
            gson.toJson(new ArrayList<>(), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // add module to index
        Type indexType = new TypeToken<List<Map<String, String>>>(){}.getType();
        List<Map<String, String>> index;
        try (Reader reader = new FileReader(indexPath.toString())){
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

        try (FileWriter writer = new FileWriter(indexPath.toString())){
            gson.toJson(index, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // add module to lecturer's modules.json
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> list;
        try (Reader reader = new FileReader(lecturerPath.toString())){
            list = gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.add(code);
        try (FileWriter writer = new FileWriter(lecturerPath.toString())){
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Module> searchAllModules(String term) {
        ModuleData moduleData = ModuleData.getInstance();
        return moduleData.searchFromAllModules(term);
    }
}
