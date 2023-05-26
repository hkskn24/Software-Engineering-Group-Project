package main.java.Pages.LecturerPages;

import main.java.Controller.StudentController;
import main.java.Entity.Module;
import main.java.Entity.Student;
import main.java.Pages.MyPage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Page shows student list with add and delete operations
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class StudentListPage extends MyPage {
    private final StudentController studentController;
    private final String code;
    private final JList<String> studentList;
    private final DefaultListModel<String> listModel;

    /**
     * @param module selected module
     */
    public StudentListPage(Module module) {
        this.studentController = new StudentController();
        setTitle("Student List");

        code = module.getCode();

        JPanel panel = new JPanel(new BorderLayout());
        setContentPane(panel);

        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        updateStudentList();
        panel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        panel.add(setupControlPanel(module), BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * @param module selected module
     * @return {@link JPanel}
     */
    private JPanel setupControlPanel(Module module) {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        controlPanel.add(setupAddPanel(), FlowLayout.LEFT);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(e -> {
            String selectedValue = studentList.getSelectedValue();
            if (selectedValue != null && !selectedValue.isEmpty()) {
                String[] parts = selectedValue.split(" ");
                String ID = parts[1];

                boolean result = studentController.deleteStudent(code, ID);
                if (result) {
                    updateStudentList();
                    JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong!");
                }

            }
        });

        controlPanel.add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new ModuleInfoPage(module);
        });
        controlPanel.add(backButton);
        return controlPanel;
    }

    /**
     * @return {@link JPanel}
     */
    private JPanel setupAddPanel() {
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel addLabel = new JLabel("Student ID: ");
        JTextField addField = new JTextField(10);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {
            String ID = addField.getText().trim();
            if (!ID.isEmpty()) {
                int result = studentController.addStudent(code, ID);
                switch (result) {
                    case 0 -> JOptionPane.showMessageDialog(null, "Student not found.");
                    case 1 -> JOptionPane.showMessageDialog(null, "Student already added.");
                    case 2 -> JOptionPane.showMessageDialog(null, "Student added successfully.");
                }
                addField.setText("");
                updateStudentList();
            }
        });

        addPanel.add(addLabel);
        addPanel.add(addField);
        addPanel.add(addButton);

        return addPanel;
    }

    /**
     * refresh the student list
     */
    private void updateStudentList() {
        List<Student> students = studentController.getStudentList(code);
        listModel.clear();
        for (Student student : students) {
            String name = student.getName();
            String ID = student.getID();
            listModel.addElement(name + " " + ID);
        }
    }
}
