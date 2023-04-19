package main.java.Pages.LecturerPages;

import main.java.Config;
import main.java.Controller.ModuleController;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddModulePage extends JFrame {
    private final JTextField nameField;
    private final JTextField codeField;
    private final JTextField creditsField;
    private final JTextField hoursField;
    private final JTextField semesterField;
    private final JTextField gradesField;
    private final JTextField typeField;
    private final JTextField lecturerField;
    private final JTextField summaryField;
    private final JTextField aimsField;
    private final JTextField syllabusField;
    private final JTextField readingListField;
    private final JButton submitButton;

    public AddModulePage() {

        setTitle("Add Module");
        setSize(1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = createLabeledTextField(contentPanel, "Name:");
        codeField = createLabeledTextField(contentPanel, "Code:");
        creditsField = createLabeledTextField(contentPanel, "Credits:");
        hoursField = createLabeledTextField(contentPanel, "Hours:");
        semesterField = createLabeledTextField(contentPanel, "Semester:");
        gradesField = createLabeledTextField(contentPanel, "Grades:");
        typeField = createLabeledTextField(contentPanel, "Type:");
        lecturerField = createLabeledTextField(contentPanel, "Lecturer:");
        summaryField = createLabeledTextField(contentPanel, "Summary");
        aimsField = createLabeledTextField(contentPanel, "Aims");
        syllabusField = createLabeledTextField(contentPanel, "Syllabus");
        readingListField = createLabeledTextField(contentPanel, "Reading List");

        submitButton = new JButton("Submit");
        addActionListener();
        contentPanel.add(submitButton);

        setContentPane(contentPanel);

        setVisible(true);
    }

    private JTextField createLabeledTextField(JPanel panel, String text) {
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(text);
        JTextField textField = new JTextField();

        subPanel.add(label);
        subPanel.add(textField);
        panel.add(subPanel);

        return textField;
    }

    private void addActionListener() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Module module = new Module();

                module.setName(nameField.getText().trim());
                module.setCode(codeField.getText().trim());
                module.setCredits(Integer.parseInt(creditsField.getText().trim()));
                module.setHours(Integer.parseInt(hoursField.getText().trim()));
                module.setSemester(Integer.parseInt(semesterField.getText().trim()));
                module.setType(typeField.getText().trim());
                module.setGrades(Integer.parseInt(gradesField.getText().trim()));
                module.setLecturer(lecturerField.getText().trim());
                module.setSummary(summaryField.getText().trim());
                module.setAims(aimsField.getText().trim());
                module.setSyllabus(syllabusField.getText().trim());
                module.setReadingList(readingListField.getText().trim());
                module.setStatus("ongoing");

                ModuleController.addModule(module, Config.getUsername());

            }
        });
    }
}