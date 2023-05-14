package main.java.Pages;

import main.java.Controller.StudentController;
import main.java.Entity.Module;
import main.java.Entity.Student;
import main.java.Pages.LecturerPages.MyPage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentListPage extends MyPage {
    private StudentController studentController;
    private String code;
    private JList<String> studentList;
    private DefaultListModel<String> listModel;

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

    private JPanel setupControlPanel(Module module) {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        controlPanel.add(setupAddPanel());

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(e -> {
            String ID = studentList.getSelectedValue();
            if (ID != null && !ID.isEmpty()) {
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
            new Lec_ModuleInfoPage(module);
        });
        controlPanel.add(backButton);
        return controlPanel;
    }

    private JPanel setupAddPanel() {
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel addLabel = new JLabel("Student ID: ");
        JTextField addField = new JTextField(10);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {
            String ID = addButton.getText().trim();
            if (!ID.isEmpty()) {
                int result = studentController.addStudent(code, ID);
                switch (result) {
                    case 0 -> JOptionPane.showMessageDialog(null, "Student not found.");
                    case 1 -> JOptionPane.showMessageDialog(null, "Student already added.");
                    case 2 -> JOptionPane.showMessageDialog(null, "Student added successfully.");
                }
                addField.setText("");
            }
        });

        addPanel.add(addLabel);
        addPanel.add(addField);
        addPanel.add(addButton);

        return addPanel;
    }

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
