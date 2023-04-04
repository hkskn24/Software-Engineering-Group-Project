package main.java.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeController {
    public static ActionListener createFilterActionListener(JTable table, TableRowSorter<DefaultTableModel> sorter, JComboBox<String> semesterComboBox) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSemester = (String) semesterComboBox.getSelectedItem();
                filterBySemester(table, sorter, selectedSemester);
            }
        };
    }

    public static void filterBySemester(JTable table, TableRowSorter<DefaultTableModel> sorter, String semester) {
        if (semester.equals("All")) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("^" + semester + "$", 4));
        }
    }
}
