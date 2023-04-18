package main.java.Pages.LecturerPages;

import main.java.Config;
import main.java.Controller.ModuleController;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPage extends JFrame{
    private JTextField nameField, idField, emailField;
    private JButton submitButton;

    public InformationPage() {
        setTitle("My Information");
        setSize(1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = createLabeledTextField(contentPanel, "Name:");
        idField = createLabeledTextField(contentPanel, "Id:");
        emailField = createLabeledTextField(contentPanel, "Email:");

        submitButton = new JButton("Submit");
        addActionListener();
        contentPanel.add(submitButton);

        setContentPane(contentPanel);

        setVisible(true);
        addBackButton(contentPanel);
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
                module.setEmail(emailField.getText().trim());
                module.setId(Integer.parseInt(idField.getText().trim()));

                ModuleController.addModule(module, Config.getUsername());

            }
        });
    }

    private void addBackButton(JPanel infoPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> dispose());
        infoPanel.add(backButton, gbc);
        setLocationRelativeTo(null);
    }
}