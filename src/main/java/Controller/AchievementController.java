package main.java.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Controller about the achievement page
 *
 * @author : Yunxin Wang
 * @version : v4.3
 */
public class AchievementController {
    /**
     * Create the filter of achievements
     *
     * @param sorter           sorter of the achievements
     * @param semesterComboBox comboBox to choose semesters
     * @param typeComboBox     comboBox to choose types of achievements
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
     * Filter the achievements by semester
     *
     * @param semester semesters
     * @return RowFilter
     */
    public static RowFilter<Object, Object> filterBySemester(String semester) {
        if (semester == null || semester.equals("All")) {
            return RowFilter.regexFilter(".*");
        } else {
            return RowFilter.regexFilter("^" + semester + "$", 2);
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
            return RowFilter.regexFilter("^" + type + "$", 1);
        }
    }

    /**
     * Sort the achievement table
     *
     * @param table       achievement table
     * @param columnIndex index of the columns
     * @param ascending   to choose whether ascending or not
     */
    public static void sortTable(JTable table, int columnIndex, boolean ascending) {
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(columnIndex, ascending ? SortOrder.ASCENDING : SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }
}
