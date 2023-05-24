package main.java.Pages.LecturerPages;

import main.java.Controller.StudentController;
import main.java.Entity.Module;
import main.java.Entity.Student;
import main.java.Pages.MyPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.List;

public class AssignGradesPage extends MyPage {
    private JTable table;
    private final StudentController studentController;
    private final String code;

    public AssignGradesPage(Module module) {
        this.studentController = new StudentController();
        setTitle("Assign Grades");

        code = module.getCode();

        JPanel panel = new JPanel(new BorderLayout());
        setContentPane(panel);

        panel.add(setupControlPanel(panel), BorderLayout.SOUTH);
    }

    private JPanel setupControlPanel(JPanel panel) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        String[] columnNames = {"ID", "Name", "Grades"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        List<Student> students = studentController.getStudentList(code);
        for (Student student : students) {
            Object[] row = new Object[3];
            row[0] = student.getID();
            row[1] = student.getName();
            row[2] = student.getGrades() == -1 ? "-" : Integer.toString(student.getGrades());
            model.addRow(row);
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitGrades());
        buttonPanel.add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new CompletedModulePage();
            dispose();
        });
        buttonPanel.add(backButton);
        return buttonPanel;
    }

    private void submitGrades() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        boolean isSuccess = true;
        int editingRow = table.getEditingRow();
        int editingColumn = table.getEditingColumn();

        if (editingRow != -1 && editingColumn != -1) {
            TableCellEditor cellEditor = table.getCellEditor();
            cellEditor.stopCellEditing();
            Object editedValue = cellEditor.getCellEditorValue();
            model.setValueAt(editedValue, editingRow, editingColumn);
        }

        for (int i = 0; i < rowCount; i++) {
            String ID = (String) model.getValueAt(i, 0);
            String gradesStr = (String) model.getValueAt(i, 2);
            int grades = gradesStr.equals("-") ? -1 : Integer.parseInt(gradesStr);
            isSuccess = isSuccess & studentController.updateGrades(code, ID, grades);
        }

        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "Submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Faild to submit grades.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
