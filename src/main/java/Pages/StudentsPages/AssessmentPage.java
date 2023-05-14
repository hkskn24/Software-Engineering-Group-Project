package main.java.Pages.StudentsPages;

import com.formdev.flatlaf.FlatDarculaLaf;

import main.java.Data.ModuleData;
import main.java.Entity.Assessment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;

public class AssessmentPage extends JFrame {
    private JTable table;
    private JButton backButton;

    public AssessmentPage() {
        setTitle("Student Assessments");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        Assessments();

        // Enable sorting on column click
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(sorter);
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getTableHeader().columnAtPoint(e.getPoint());
                sorter.setComparator(column, new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        return String.valueOf(o1).compareTo(String.valueOf(o2));
                    }
                });
            }
        });

        setVisible(true);
    }

    private void Assessments() {
        ModuleData moduleData = new ModuleData();
        List<Assessment> assessments = moduleData.loadAssessments();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Code");
        model.addColumn("Type");
        model.addColumn("Duration");

        for (Assessment assessment : assessments) {
            String name = assessment.getName();
            String code = assessment.getCode();
            String typeValue = assessment.getType();
            int duration = assessment.getDuration();

            model.addRow(new Object[]{name, code, typeValue, duration});
        }

        table.setModel(model);
    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        SwingUtilities.invokeLater(() -> new AssessmentPage());
    }
}
