package main.java.Pages.StudentPages;

import main.java.Data.ModuleData;
import main.java.Entity.Assessment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.List;

public class AssessmentPage extends JFrame {
    private final JTable table;

    public AssessmentPage() {
        setTitle("Student Assessments");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
        });
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(backButton, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        Assessments();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(sorter);
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = table.getTableHeader().columnAtPoint(e.getPoint());
                sorter.setComparator(column, Comparator.comparing(String::valueOf));
            }
        });

        setVisible(true);
    }

    private void Assessments() {
        ModuleData moduleData = new ModuleData();
        List<Assessment> assessments = moduleData.loadAssessments();

        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Name");
        model.addColumn("Module");
        model.addColumn("Code");
        model.addColumn("Type");
        model.addColumn("Date");
        model.addColumn("Duration");

        for (Assessment assessment : assessments) {
            String name = assessment.getName();
            String moduleName = assessment.getModuleName();
            String code = assessment.getCode();
            String date = assessment.getDate();
            String typeValue = assessment.getType();
            int duration = assessment.getDuration();

            model.addRow(new Object[]{name, moduleName, code, typeValue, date, duration});
        }

        table.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AssessmentPage::new);
    }
}
