package main.java.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecoverPasswordActionListener implements ActionListener {
    public final int userType;

    public RecoverPasswordActionListener(int num) {
        this.userType = num;
    }

    public void actionPerformed(ActionEvent e) {
        String id = JOptionPane.showInputDialog("Please enter your ID:");

        if (!id.matches("\\d{10}")) {
            JFrame tip = new JFrame();
            JOptionPane.showMessageDialog(tip, "Please ensure that your ID is a 10-digit number.");
            return;
        }
        if (userType == 0) {
            try {
                String username = "";
                String password = "";

                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("students.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line3;
                List<String> list = new ArrayList<>();
                while ((line3 = reader.readLine()) != null) {
                    list.add(line3);
                }
                reader.close();
                for (int i = 0; i < list.size(); i++) {
                    String pw = list.get(i);
                    //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
                    String[] dpart = pw.split(" ");
                    if (id.equals(dpart[2])) {
                        username = dpart[0];
                        String newpassword = JOptionPane.showInputDialog("Hello! " + username + " Please enter your new password:");
                        if (!newpassword.matches("[a-zA-Z0-9]{6,10}")) {
                            JOptionPane.showMessageDialog(null, "Failed to change password.\nPassword format is invalid. Please enter 6-10 digits or English characters.");
                            return;
                        } else {
                            list.remove(pw);
                            password = newpassword;
                            FileWriter fout = new FileWriter("students.txt", false);
                            fout.write("");
                            fout.close();
                            break;
                        }
                    } else if (i == list.size() - 1) {
                        JOptionPane.showMessageDialog(null, "There is no account associated with this ID.");
                        return;
                    }
                }
                for (String pw : list) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
                    writer.write(pw);
                    writer.newLine();
                    writer.close();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
                writer.write(username + " " + password + " " + id);
                writer.newLine();
                writer.close();
                JOptionPane.showMessageDialog(null, "Password updated successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to read the file: " + ex.getMessage());
            }
        } else if (userType == 1) {
            try {
                String username = "";
                String password = "";
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("lecturers.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line3;
                List<String> list = new ArrayList<>();
                while ((line3 = reader.readLine()) != null) {
                    list.add(line3);
                }
                reader.close();
                for (int i = 0; i < list.size(); i++) {
                    String pw = list.get(i);
                    //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
                    String[] dpart = pw.split(" ");
                    if (id.equals(dpart[2])) {
                        username = dpart[0];
                        String newpassword = JOptionPane.showInputDialog("Hello! " + username + " Please enter your new password:");
                        if (!newpassword.matches("[a-zA-Z0-9]{6,10}")) {
                            JOptionPane.showMessageDialog(null, "Failed to change password.\nPassword format is invalid. Please enter 6-10 digits or English characters.");
                            return;
                        } else {
                            list.remove(pw);
                            password = newpassword;
                            FileWriter fout = new FileWriter("lecturers.txt", false);
                            fout.write("");
                            fout.close();
                            break;
                        }
                    } else if (i == list.size() - 1) {
                        JOptionPane.showMessageDialog(null, "There is no account associated with this ID.");
                        return;
                    }
                }
                for (String pw : list) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("lecturers.txt", true));
                    writer.write(pw);
                    writer.newLine();
                    writer.close();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("lecturers.txt", true));
                writer.write(username + " " + password + " " + id);
                writer.newLine();
                writer.close();
                JOptionPane.showMessageDialog(null, "Password updated successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to read the file: " + ex.getMessage());
            }
        }
    }
}
