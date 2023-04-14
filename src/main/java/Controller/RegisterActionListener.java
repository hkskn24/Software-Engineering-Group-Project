package main.java.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RegisterActionListener implements ActionListener {
    public final int userType;

    public RegisterActionListener(int num) {
        this.userType = num;
    }

    public void actionPerformed(ActionEvent e) {
        JPanel registerPanel = new JPanel();
        JFrame registerWindow = new JFrame("Sign up");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel studentidLabel = new JLabel("Student ID:");
        JLabel lectureridLabel = new JLabel("Lecturer ID:");
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        JTextField idField = new JTextField(10);
        JButton registerButton = new JButton("Sign up");

        registerPanel.add(usernameLabel);
        registerPanel.add(usernameField);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordField);
        if(userType == 0){
            registerPanel.add(studentidLabel);
        }
        else if (userType == 1){
            registerPanel.add(lectureridLabel);
        }
        registerPanel.add(idField);
        registerPanel.add(new JLabel());
        registerPanel.add(registerButton);

        registerButton.addActionListener(e1 -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String id = idField.getText().trim();
            if (userType == 0) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                    String line1;
                    while ((line1 = reader.readLine()) != null) {
                        String[] sparts = line1.split(" ");
                        if (sparts[0].equals(username) || sparts[2].equals(id)) {
                            JOptionPane.showMessageDialog(registerWindow, "The username or student id already exists, please try again.");
                            return;
                        }
                        reader.close();
                    }
                } catch (IOException ignored) {
                }
            } else if (userType == 1) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("lecturers.txt"));
                    String line1;
                    while ((line1 = reader.readLine()) != null) {
                        String[] sparts = line1.split(" ");
                        if (sparts[0].equals(username) || sparts[2].equals(id)) {
                            JOptionPane.showMessageDialog(registerWindow, "The username or id already exists, please try again.");
                            return;
                        }
                        reader.close();
                    }
                } catch (IOException ignored) {
                }
            }

            if (!username.matches("[a-zA-Z\u4e00-\u9fa5]{1,10}")) {
                JOptionPane.showMessageDialog(registerWindow, "Please ensure your username consists of Chinese characters or English letters only.");
                return;
            }
            if (!password.matches("[a-zA-Z0-9]{6,10}")) {
                JOptionPane.showMessageDialog(registerWindow, "Please ensure your password consists of 6-10 digits or English characters only.");
                return;
            }
            if (!id.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(registerWindow, "Please ensure that your student ID is a 10-digit number.");
                return;
            }

            // student
            if (userType == 0) {
                File studentFolder = new File("src/main/resources/data/students/" + username);
                if (!studentFolder.exists()) {
                    if (!studentFolder.mkdir()){
                        System.out.println("Failed to create student folder.");
                    }
                }

                // create empty json files
                File moduleFile = new File("src/main/resources/data/students/" + username + "/modules.json");
                File achievementFile = new File("src/main/resources/data/students/" + username + "/achievements.json");

                if (!moduleFile.exists()) {
                    FileWriter moduleWriter;
                    try {
                        moduleWriter = new FileWriter(moduleFile);
                        moduleWriter.write("[]");
                        moduleWriter.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (!achievementFile.exists()) {
                    FileWriter achievementWriter;
                    try {
                        achievementWriter = new FileWriter(achievementFile);
                        achievementWriter.write("[]");
                        achievementWriter.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
                    writer.write(username + " " + password + " " + id);
                    writer.newLine();
                    JOptionPane.showMessageDialog(registerWindow, "Registration successful!");
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(registerWindow, "Failed to read the file: " + ex.getMessage());
                }
            }

            // lecturer
            else {
                File lecturerFolder = new File("src/main/resources/data/lecturers/" + username);
                if (!lecturerFolder.exists()) {
                    if (!lecturerFolder.mkdir()){
                        System.out.println("Failed to create lecturer folder.");
                    }
                }

                // create empty json files
                File moduleFile = new File("src/main/resources/data/lecturers/" + username + "/modules.json");

                if (!moduleFile.exists()) {
                    FileWriter moduleWriter;
                    try {
                        moduleWriter = new FileWriter(moduleFile);
                        moduleWriter.write("[]");
                        moduleWriter.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            registerWindow.dispose();
        });

        registerWindow.add(registerPanel);
        registerWindow.pack();
        registerWindow.setLocationRelativeTo(null);
        registerWindow.setVisible(true);
    }
}
