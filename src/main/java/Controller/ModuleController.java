package main.java.Controller;

import main.java.Entity.Module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
        sorter.setSortKeys(List.of(new RowSorter.SortKey(columnIndex, ascending ? SortOrder.ASCENDING : SortOrder.DESCENDING)));
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
}
