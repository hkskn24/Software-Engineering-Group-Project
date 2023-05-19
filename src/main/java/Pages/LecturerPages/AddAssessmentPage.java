package main.java.Pages.LecturerPages;


import main.java.Data.ModuleData;
import main.java.Entity.Assessment;
import main.java.Entity.Module;
import main.java.Pages.StudentsPages.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddAssessmentPage extends JFrame {
    private JTextField nameTextField;
    private JTextField moduleNameTextField;
    private JTextField codeTextField;
    private JTextField dateTextField;
    private JTextField typeTextField;
    private JTextField durationTextField;

    public AddAssessmentPage(Module module) {
        setTitle("Add Assessment");
        setLayout(new BorderLayout());
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.java.Pages.LecturerPages.HomePage homePage = new main.java.Pages.LecturerPages.HomePage();
                homePage.setVisible(true);
            }
        });

        JPanel contentPanel = new JPanel(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        contentPanel.add(nameLabel);
        contentPanel.add(nameTextField);

        JLabel moduleNameLabel = new JLabel("Module Name:");
        moduleNameTextField = new JTextField(module.getName());
        moduleNameTextField.setEditable(false);
        contentPanel.add(moduleNameLabel);
        contentPanel.add(moduleNameTextField);

        JLabel codeLabel = new JLabel("Module code:");
        codeTextField = new JTextField(module.getCode());
        codeTextField.setEditable(false);
        contentPanel.add(codeLabel);
        contentPanel.add(codeTextField);

        JLabel dateLabel = new JLabel("Date:");
        dateTextField = new JTextField();
        contentPanel.add(dateLabel);
        contentPanel.add(dateTextField);

        JLabel typeLabel = new JLabel("Type:");
        typeTextField = new JTextField();
        contentPanel.add(typeLabel);
        contentPanel.add(typeTextField);

        JLabel durationLabel = new JLabel("Duration (minutes):");
        durationTextField = new JTextField();
        contentPanel.add(durationLabel);
        contentPanel.add(durationTextField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText().trim();
                String moduleName = moduleNameTextField.getText().trim();
                String code = codeTextField.getText().trim();
                String date = dateTextField.getText().trim();
                String type = typeTextField.getText().trim();
                int duration = Integer.parseInt(durationTextField.getText().trim());

                Assessment assessment = new Assessment();
                assessment.setName(name);
                assessment.setModuleName(moduleName);
                assessment.setCode(code);
                assessment.setType(type);
                assessment.setDuration(duration);

                ModuleData.getInstance().addAssessment(assessment);

                JOptionPane.showMessageDialog(AddAssessmentPage.this, "Assessment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        add(contentPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
