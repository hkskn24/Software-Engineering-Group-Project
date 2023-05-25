package main.java.Pages.StudentPages;

import main.java.Data.ModuleData;
import main.java.Entity.Assessment;
import main.java.Pages.MyPage;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class AssessmentPage extends MyPage {
    private final JTable table;

    public AssessmentPage() {
        setTitle("Student Assessments");

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



        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            private final Color color3Days = new Color(85, 114, 241, 75); // Dark blue for 3 days
            private final Color color7Days = new Color(124, 146, 244, 75); // Medium blue for 7 days
            private final Color color10Days = new Color(161, 177, 247, 75); // Light blue for 10 days
            private final Color colorMoreThan10Days = new Color(218, 224, 252, 75); // Very light blue for more than 10 days

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Get the date of the current row
                String dateString = (String) table.getModel().getValueAt(row, 4); // Change the index if your date column is not at index 4
                LocalDate date = LocalDate.parse(dateString, formatter);
                long days = ChronoUnit.DAYS.between(LocalDate.now(), date);

                // Set the background color depending on the number of days
                if (days <= 3) {
                    setBackground(color3Days);
                } else if (days <= 7) {
                    setBackground(color7Days);
                } else if (days <= 10) {
                    setBackground(color10Days);
                } else {
                    setBackground(colorMoreThan10Days);
                }

                return this;
            }
        });


        setVisible(true);
    }

    private void Assessments() {
        ModuleData moduleData = new ModuleData();
        List<Assessment> assessments = moduleData.loadAssessments();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

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
            LocalDate localDate = LocalDate.parse(date, formatter);
            if (localDate.isBefore(LocalDate.now())) continue;
            String typeValue = assessment.getType();
            int duration = assessment.getDuration();

            model.addRow(new Object[]{name, moduleName, code, typeValue, date, duration});
        }

        table.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AssessmentPage::new);
    }
    
    //TDD用
    public JTable getTable() {
        return table;
    }
}
