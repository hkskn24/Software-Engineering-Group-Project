package main.java.Pages.LecturerPages;

import main.java.Config;
import main.java.Controller.ModuleController;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddModulePage extends JFrame {
    private final JTextField nameField, codeField, creditsField, hoursField,semesterField, typeField, lecturerField;
    private final JTextArea summaryField, aimsField, syllabusField, readingListField;
    private final JButton submitButton;
    private final JButton backButton;

    public AddModulePage() {

        setTitle("Add Module");
        setSize(694, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel contentPanel1 = new JPanel(new GridLayout(2, 7));
        contentPanel1.setPreferredSize(new Dimension(400, 150));
        contentPanel1.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        nameField = createLabeledTextField(contentPanel1, "Name:",10);
        codeField = createLabeledTextField(contentPanel1, "Code:",10);
        creditsField = createLabeledTextField(contentPanel1, "Credits:",10);
        hoursField = createLabeledTextField(contentPanel1, "Hours:",10);
        semesterField = createLabeledTextField(contentPanel1, "Semester:",10);
        typeField = createLabeledTextField(contentPanel1, "Type:",10);
        lecturerField = createLabeledTextField(contentPanel1, "Lecturer:",10);

        JPanel contentPanel2 = new JPanel(new GridLayout(2, 4));
        contentPanel2.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        summaryField = createLabeledTextArea(contentPanel2, "Summary");
        aimsField = createLabeledTextArea(contentPanel2, "Aims");
        syllabusField = createLabeledTextArea(contentPanel2, "Syllabus");
        readingListField = createLabeledTextArea(contentPanel2, "Reading List");


        submitButton = new JButton("Submit");
        addActionListener();

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(contentPanel1, BorderLayout.NORTH);
        contentPane.add(contentPanel2, BorderLayout.CENTER);

        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        add(contentPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePage().setVisible(true);
            }
        });
    }

    private JTextField createLabeledTextField(JPanel panel, String text ,int columns) {
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(text);
        JTextField textField = new JTextField(columns);

        subPanel.add(label);
        subPanel.add(textField);
        panel.add(subPanel);

        return textField;
    }

    private JTextArea createLabeledTextArea(JPanel panel, String text) {
        JPanel subPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout
        JLabel label = new JLabel(text);
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(30);
        textArea.setColumns(50);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Anchor label to the top-left corner
        gbc.weightx = 0;
        gbc.weighty = 0;
        subPanel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        subPanel.add(textArea, gbc);

        subPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5), // Add a margin around the subpanel
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)));

        panel.add(subPanel);

        return textArea;
    }
    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });
        return backButton;
    }

    private void addActionListener() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Module module = new Module();


                String name = nameField.getText().trim();
                String code = codeField.getText().trim();
                String credits = creditsField.getText().trim();
                String hours = hoursField.getText().trim();
                String semester = semesterField.getText().trim();
                String type = typeField.getText().trim();
                String lecturer = lecturerField.getText().trim();
                String summary = summaryField.getText().trim();
                String aims = aimsField.getText().trim();
                String syllabus = syllabusField.getText().trim();
                String readingList = readingListField.getText().trim();

                if (name.isEmpty() || code.isEmpty() || credits.isEmpty() || hours.isEmpty() || semester.isEmpty() || type.isEmpty() ||
                        lecturer.isEmpty() || summary.isEmpty() || aims.isEmpty() || syllabus.isEmpty() || readingList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    return;
                }

                int creditsInt, hoursInt, semesterInt;
                try {
                    creditsInt = Integer.parseInt(credits);
                    hoursInt = Integer.parseInt(hours);
                    semesterInt = Integer.parseInt(semester);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Credits, hours and semester must be integers!");
                    return;
                }

                module.setName(name);
                module.setCode(code);
                module.setCredits(creditsInt);
                module.setHours(hoursInt);
                module.setSemester(semesterInt);
                module.setType(type);
                module.setLecturer(lecturer);
                module.setSummary(summary);
                module.setAims(aims);
                module.setSyllabus(syllabus);
                module.setReadingList(readingList);
                module.setStatus("ongoing");

                ModuleController.addModule(module, Config.getUsername());

                JOptionPane.showMessageDialog(null, "Module added successfully!");

                AddModulePage.this.dispose();
                new HomePage().setVisible(true);
            }
        });
    }
}
