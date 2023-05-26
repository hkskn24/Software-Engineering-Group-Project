package main.java.Pages.LecturerPages;


import main.java.Data.ModuleData;
import main.java.Entity.Assessment;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Set page to add assessment
 *
 * @author : Yanshu He
 * @version : v4.2
 */
public class AddAssessmentPage extends JFrame {
    /**
     * text field for assessment name
     */
    private final JTextField nameTextField;
    /**
     * text field for assessment module name
     */
    private final JTextField moduleNameTextField;
    /**
     * text field for assessment module code
     */
    private final JTextField codeTextField;
    /**
     * text field for assessment type
     */
    private final JTextField typeTextField;
    /**
     * text field for assessment duration
     */
    private final JTextField durationTextField;

    /**
     * @param module assessment module
     */
    public AddAssessmentPage(Module module) {
        setTitle("Add Assessment");
        setLayout(new BorderLayout());
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        JTextField dateTextField = new JTextField();
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
        submitButton.addActionListener(e -> {
            String name = nameTextField.getText().trim();
            String moduleName = moduleNameTextField.getText().trim();
            String code = codeTextField.getText().trim();
            String date = dateTextField.getText().trim();
            String type = typeTextField.getText().trim();
            String durationStr = durationTextField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(AddAssessmentPage.this, "Please enter the name. ", "Input error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (!isValidDate(date)) {
                JOptionPane.showMessageDialog(AddAssessmentPage.this, "Invalid date. It must be in the format yyyy/MM/dd.", "Input error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (type.isEmpty()) {
                JOptionPane.showMessageDialog(AddAssessmentPage.this, "Please enter the type. ", "Input error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int duration;
            try {
                duration = Integer.parseInt(durationStr);
                if (duration <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(AddAssessmentPage.this, "Duration must be a positive integer.", "Input error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Assessment assessment = new Assessment();
            assessment.setName(name);
            assessment.setModuleName(moduleName);
            assessment.setCode(code);
            assessment.setDate(date);
            assessment.setType(type);
            assessment.setDuration(duration);

            ModuleData.getInstance().addAssessment(assessment);

            JOptionPane.showMessageDialog(AddAssessmentPage.this, "Assessment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        add(contentPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * @param dateString assessment date
     * @return boolean
     */
    private boolean isValidDate(String dateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        if (dateString.matches("\\d{4}/\\d{2}/\\d{2}")) {
            try {
                LocalDate date = LocalDate.parse(dateString, dateFormatter);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
