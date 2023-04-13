package main.java.Pages;

import main.java.Controller.AchievementController;
import main.java.Controller.ModuleController;
import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class GradePage extends JFrame {
    private TableRowSorter<DefaultTableModel> sorter;

    public GradePage() {
        setTitle("TransfiguringGrades");
        getContentPane().setBackground(new Color(250, 250, 250));
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);

        JTable table = setupTable();

        // filter
        JPanel filterPanel = setupFilterPanel(sorter);

        // sort
        JPanel sortPanel = setupSortPanel(table);

        setupCombinedPanel(filterPanel, sortPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (Frame.getFrames().length == 1) {
                    System.exit(0);
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GradePage();
    }

    private JTable setupTable() {
        // get data from ModuleData class
        ArrayList<Module> modules = ModuleData.getInstance().modules;

        // create table
        DefaultTableModel tableModel = createTableModel(modules);
        JTable table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        //setupTableBehavior(table, tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setVisible(true);

        return table;
    }

    private DefaultTableModel createTableModel(ArrayList<Module> modules) {
        String[] columnNames = {"Name", "Code", "Credit", "Hours", "Semester", "Type", "Grades"};   //列名
        String[][] tableValues = new String[modules.size()][Module.getGradesAttributes().length];
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            tableValues[i] = new String[]{module.getName(), module.getCode(),
                    String.valueOf(module.getCredits()), String.valueOf(module.getHours()),
                    String.valueOf(module.getSemester()), module.getType(), "-"
            };
        }

        return new DefaultTableModel(tableValues, columnNames);
    }

    private JPanel setupFilterPanel(TableRowSorter<DefaultTableModel> sorter) {
        JPanel filterPanel = new JPanel();

        String[] semesterOptions = {"All", "1", "2", "3", "4", "5", "6", "7", "8"};
        JComboBox<String> semesterComboBox = new JComboBox<>(semesterOptions);
        filterPanel.add(new JLabel("Filter by semester: "));
        filterPanel.add(semesterComboBox);

        String[] typeOptions = {"All", "Elective", "Compulsory"};
        JComboBox<String> typeComboBox = new JComboBox<>(typeOptions);
        filterPanel.add(new JLabel("Filter by type: "));
        filterPanel.add(typeComboBox);

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(ModuleController.createFilterActionListener(sorter, semesterComboBox, typeComboBox));
        filterPanel.add(filterButton);

        return filterPanel;
    }

    private JPanel setupSortPanel(JTable table) {
        JPanel sortPanel = new JPanel();

        String[] sortByOptions = {"Grades", "Semester"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortByOptions);
        sortPanel.add(sortComboBox);

        JCheckBox ascendingBox = new JCheckBox("Ascending");
        sortPanel.add(ascendingBox);

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> {
            String selectedSortBy = (String) sortComboBox.getSelectedItem();
            int columnIndex = "Grades".equals(selectedSortBy) ? 6 : 4;
            AchievementController.sortTable(table, columnIndex, ascendingBox.isSelected());
        });
        sortPanel.add(sortButton);

        return sortPanel;
    }

    private void setupCombinedPanel(JPanel filterPanel, JPanel sortPanel) {
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

        combinedPanel.add(sortPanel, gbc);

        addBackButton(combinedPanel, gbc);

        combinedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private void addBackButton(JPanel contentPanel, GridBagConstraints gbc) {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton GPAButton = new JButton("view GPA");
        GPAButton.addActionListener(new GPAActionListener());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });

        bottomPanel.add(GPAButton, BorderLayout.WEST);
        bottomPanel.add(backButton, BorderLayout.EAST);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        contentPanel.add(bottomPanel, gbc);
    }

    private class GPAActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                GPAPage p = new GPAPage();
                p.averageGPA();
                p.totalGPA();
                p.postGPA();
                p.perGPA();
                setVisible(true);
                dispose();
            });
        }
    }
}