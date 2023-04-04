package main.java.Pages;

import main.java.Data;
import main.java.Entity.Module;
import main.java.Controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GradesPage extends JFrame {

    private JTextField nameTextField;
    private JTextField codeTextField;
    private JTextField creditsTextField;
    private JTextField hoursTextField;
    private JTextField semesterTextField;
    private JTextField typeTextField;
    private JTextField gradeTextField;
    private TableRowSorter<DefaultTableModel> sorter;

    public static void main(String[] args) {
        new GradesPage();
    }

    public GradesPage(){
       initializeUIComponents();
    }

    private void initializeUIComponents() {
        setupFrame();

        JTable table = setupTable();

        // update and delete
        JPanel actionPanel = setupActionPanel(table);

        // filter
        JPanel filterPanel = setupFilterPanel(table, sorter);

        setupCombinedPanel(actionPanel, filterPanel);
    }

    private void setupFrame() {
        setTitle("Module Information");
        getContentPane().setBackground(new Color(250, 250, 250));
        setBounds(450, 250, 1500, 900);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JTable setupTable() {
        // get data from Data class
        ArrayList<Module> modules = Data.getInstance().modules;

        // create table
        DefaultTableModel tableModel = createTableModel(modules);
        JTable table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        setupTableBehavior(table, tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);

        return table;
    }

    private DefaultTableModel createTableModel(ArrayList<Module> modules) {
        String[] columnNames = {"Name","Code","Credit","Hours","Semester","Type","Grades"};   //列名
        String[][] tableValues = new String[modules.size()][Module.getAllAttributes().length];
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            tableValues[i] = new String[] {module.getName(), module.getCode(),
                    String.valueOf(module.getCredits()), String.valueOf(module.getHours()),
                    String.valueOf(module.getSemester()), module.getType(), String.valueOf(module.getGrades())
            };
        }

        return new DefaultTableModel(tableValues, columnNames);
    }

    private void setupTableBehavior(JTable table, DefaultTableModel tableModel) {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                Object name = tableModel.getValueAt(selectedRow, 0);
                Object code = tableModel.getValueAt(selectedRow, 1);
                Object credits = tableModel.getValueAt(selectedRow, 2);
                Object hours = tableModel.getValueAt(selectedRow, 3);
                Object semester = tableModel.getValueAt(selectedRow, 4);
                Object type = tableModel.getValueAt(selectedRow, 5);
                Object grades = tableModel.getValueAt(selectedRow, 6);

                nameTextField.setText(name.toString());
                codeTextField.setText(code.toString());
                creditsTextField.setText(credits.toString());
                hoursTextField.setText(hours.toString());
                semesterTextField.setText(semester.toString());
                typeTextField.setText(type.toString());
                gradeTextField.setText(grades.toString());
            }
        });
    }

    private JPanel setupActionPanel(JTable table) {
        JPanel panel = new JPanel();

        setupTextFields(panel);

        setupActionButtons(panel, table);

        return panel;
    }

    private void setupTextFields(JPanel panel) {
        panel.add(new JLabel("name: "));
        nameTextField = new JTextField("name");
        panel.add(nameTextField);

        panel.add(new JLabel("code: "));
        codeTextField = new JTextField("code");
        panel.add(codeTextField);

        panel.add(new JLabel("credit: "));
        creditsTextField = new JTextField("credit");
        panel.add(creditsTextField);

        panel.add(new JLabel("hours: "));
        hoursTextField = new JTextField("hours");
        panel.add(hoursTextField);

        panel.add(new JLabel("semester: "));
        semesterTextField = new JTextField("semester");
        panel.add(semesterTextField);

        panel.add(new JLabel("type: "));
        typeTextField = new JTextField("type");
        panel.add(typeTextField);

        panel.add(new JLabel("grade: "));
        gradeTextField = new JTextField("grade");
        panel.add(gradeTextField);
    }

    private void setupActionButtons(JPanel panel, JTable table) {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(createAddButtonListener(table));
        panel.add(addButton);

        JButton updateButton = new JButton("Update");
        addButton.addActionListener(createUpdateButtonListener(table));
        panel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(createDeleteButtonListener(table));
        panel.add(deleteButton);
    }

    private ActionListener createAddButtonListener(JTable table) {
        return e -> {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            String[] rowValues = {nameTextField.getText(), codeTextField.getText(), codeTextField.getText(),
                    hoursTextField.getText(), semesterTextField.getText(), typeTextField.getText(),
            };
            tableModel.addRow(rowValues);
        };
    }

    private ActionListener createUpdateButtonListener(JTable table) {
        return e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.setValueAt(nameTextField.getText(), selectedRow, 0);
                tableModel.setValueAt(codeTextField.getText(), selectedRow, 1);
                tableModel.setValueAt(creditsTextField.getText(), selectedRow, 2);
                tableModel.setValueAt(hoursTextField.getText(), selectedRow, 3);
                tableModel.setValueAt(semesterTextField.getText(), selectedRow, 4);
                tableModel.setValueAt(typeTextField.getText(), selectedRow, 5);
                tableModel.setValueAt(gradeTextField.getText(), selectedRow, 6);
            }
        };
    }

    private ActionListener createDeleteButtonListener(JTable table) {
        return e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.removeRow(selectedRow);
            }
        };
    }

    private JPanel setupFilterPanel(JTable table, TableRowSorter<DefaultTableModel> sorter) {
        JPanel filterPanel = new JPanel();

        String[] semesterOptions = {"All", "1", "2", "3", "4", "5", "6", "7", "8"};
        JComboBox<String> semesterComboBox = new JComboBox<>(semesterOptions);
        filterPanel.add(new JLabel("Filter by semester: "));
        filterPanel.add(semesterComboBox);

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(ModuleController.createFilterActionListener(table, sorter, semesterComboBox));
        filterPanel.add(filterButton);

        return filterPanel;
    }

    private void setupCombinedPanel(JPanel actionPanel, JPanel filterPanel) {
        JPanel combinedPanel = new JPanel(new GridBagLayout());
        getContentPane().add(combinedPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        combinedPanel.add(filterPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0;

        combinedPanel.add(actionPanel, gbc);

        combinedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}