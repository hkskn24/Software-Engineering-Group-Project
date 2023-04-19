package main.java.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RetrievePasswordActionListener implements ActionListener {
    public final int userType;

    public RetrievePasswordActionListener(int num) {
        this.userType = num;
    }

    public void actionPerformed(ActionEvent e) {
        String id = JOptionPane.showInputDialog("Please enter your ID:");

        if (!id.matches("\\d{10}")) {
            JFrame tip = new JFrame();
            JOptionPane.showMessageDialog(tip, "Please ensure that your ID is a 10-digit number.");
            return;
        }

        try {
            boolean found = false;
            String password = "";
            String username = "";
            if (userType == 0) {
                BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                String line2;
                while ((line2 = reader.readLine()) != null) {
                    String[] reparts = line2.split(" ");
                    if (reparts[2].equals(id)) {
                        found = true;
                        password = reparts[1];
                        username = reparts[0];
                        break;
                    }
                }
                reader.close();
            } else if (userType == 1) {
                BufferedReader reader = new BufferedReader(new FileReader("lecturers.txt"));
                String line2;
                while ((line2 = reader.readLine()) != null) {
                    String[] reparts = line2.split(" ");
                    if (reparts[2].equals(id)) {
                        found = true;
                        password = reparts[1];
                        username = reparts[0];
                        break;
                    }
                }
                reader.close();
            }

            if (found) {
                JOptionPane.showMessageDialog(null, "Your username is: " + username + "\n" + "Your password is: " + password);
            } else {
                JOptionPane.showMessageDialog(null, "There is no account associated with this ID.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to read the file: " + ex.getMessage());
        }
    }
}
