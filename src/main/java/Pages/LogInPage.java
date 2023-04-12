package main.java.Pages;

import com.formdev.flatlaf.FlatDarculaLaf;
import main.java.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogInPage extends JFrame implements ActionListener {
    private static int userType; //student:0 lecturer:1
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LogInPage() {
        super("LOG IN TO YOUR ACCOUNT");

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(false);
            }
        };
        panel.setOpaque(false);

        // 设置背景图片
        ImageIcon backgroundImageIcon = new ImageIcon("src/main/resources/login.JPG");
        JLabel backgroundImage = new JLabel(backgroundImageIcon);
        backgroundImage.setLayout(new GridLayout(0, 2, 4, 5));

        // 设置文本
        JLabel usernameLabel = new JLabel(" Username");
        JLabel passwordLabel = new JLabel(" Password");
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/HARRYP-1.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            usernameLabel.setFont(font.deriveFont(Font.PLAIN, 60f));
            passwordLabel.setFont(font.deriveFont(Font.PLAIN, 60f));

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        usernameLabel.setForeground(Color.WHITE); // 设置文本颜色为白色
        passwordLabel.setForeground(Color.WHITE); // 设置文本颜色为白色

        // 设置文本框
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setPreferredSize(new Dimension(180, 35));
        passwordField.setPreferredSize(new Dimension(180, 35));

        // 设置按钮
        JButton loginButton = new JButton("Log in");
        JButton registerButton = new JButton("Sign up");
        JButton retrievePasswordButton = new JButton("Forgot PWD");
        JButton recoverPasswordButton = new JButton("Change PWD");
        JButton quitButton = new JButton("Exit");

        // set student or lecturer
        JCheckBox studentCheckbox = new JCheckBox("Student");
        JCheckBox lecturerCheckbox = new JCheckBox("Lecturer");

        ButtonGroup group = new ButtonGroup();
        group.add(studentCheckbox);
        group.add(lecturerCheckbox);


        // 设置按钮颜色和文字颜色
        Color buttonColor = Color.BLACK;
        Color buttonTextColor = Color.WHITE;

        // 设置按钮样式
        JButton[] buttons = {loginButton, registerButton, retrievePasswordButton, recoverPasswordButton};
        for (JButton button : buttons) {
            try {
                InputStream inputStream = new BufferedInputStream(
                        new FileInputStream("src/main/resources/HARRYP-1.ttf"));

                Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

                button.setFont(font.deriveFont(Font.BOLD, 27f));

            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            button.setBackground(buttonColor);
            button.setForeground(buttonTextColor);
            button.setPreferredSize(new Dimension(200, 50)); // 增加按钮宽度以显示完整文本
        }

        // 按钮事件监听
        loginButton.addActionListener(this);
        registerButton.addActionListener(new RegisterActionListener());
        retrievePasswordButton.addActionListener(new RetrievePasswordActionListener());
        recoverPasswordButton.addActionListener(new RecoverPasswordActionListener());
        quitButton.addActionListener(new quitActionListener());

        studentCheckbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                userType = 0;
            }
        });

        lecturerCheckbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                userType = 1;
            }
        });

        // 在平面中加入
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(retrievePasswordButton);
        panel.add(recoverPasswordButton);
        panel.add(studentCheckbox);
        panel.add(lecturerCheckbox);
        panel.add(new JLabel());
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setResizable(false);

        // 在背景上放置平面
        backgroundImage.add(panel);
        backgroundImage.setSize(panel.getSize());
        add(backgroundImage);

        // 设置背景图片大小为窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
//        setResizable(false);

        // 美化平面
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 5, 4, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(studentCheckbox, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(lecturerCheckbox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(registerButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(retrievePasswordButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(recoverPasswordButton, gbc);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        FlatDarculaLaf.setup();
        new LogInPage();
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!username.matches("[a-zA-Z\u4e00-\u9fa5]{1,10}")) {
            JOptionPane.showMessageDialog(this, "Please ensure your username consists of Chinese characters or English letters only.");
            return;
        }
        if (!password.matches("[a-zA-Z0-9]{6,10}")) {
            JOptionPane.showMessageDialog(this, "Please ensure your password consists of 6-10 digits or English characters only.");
            return;
        }

        try {
            boolean loggedIn = false;

            if (userType == 0) {
                BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        loggedIn = true;
                        break;
                    }
                }
                reader.close();
            } else if (userType == 1) {
                BufferedReader reader = new BufferedReader(new FileReader("lecturers.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        loggedIn = true;
                        break;
                    }
                }
                reader.close();
            }
            if (loggedIn && userType==0) {
//                JOptionPane.showMessageDialog(this, "登录成功！");
                Config.setUsername(username);
                SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
                dispose();
            }
            else if (loggedIn && userType==1) {
//                JOptionPane.showMessageDialog(this, "登录成功！");
                Config.setUsername(username);
                SwingUtilities.invokeLater(() -> new Lec_HomePage().setVisible(true));
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to read the file: " + ex.getMessage());
        }
    }

    private static class RegisterActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel registerPanel = new JPanel();
            JFrame registerWindow = new JFrame("Sign up");
            JLabel usernameLabel = new JLabel("Username:");
            JLabel passwordLabel = new JLabel("Password:");
            JLabel idLabel = new JLabel("Student ID:");
            JTextField usernameField = new JTextField(10);
            JPasswordField passwordField = new JPasswordField(10);
            JTextField idField = new JTextField(10);
            JButton registerButton = new JButton("Sign up");

            registerPanel.add(usernameLabel);
            registerPanel.add(usernameField);
            registerPanel.add(passwordLabel);
            registerPanel.add(passwordField);
            registerPanel.add(idLabel);
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
                                JOptionPane.showMessageDialog(registerWindow, "The username or student id already exists, please try again.");
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
                    File studentFolder = new File("src/main/resources/students/" + username);
                    if (!studentFolder.exists()) {
                        if (!studentFolder.mkdir()){
                            System.out.println("Failed to create student folder.");
                        }
                    }

                    // create empty json files
                    File moduleFile = new File("src/main/resources/students/" + username + "/module.json");
                    File achievementFile = new File("src/main/resources/students/" + username + "/achievement.json");

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
                    File lecturerFolder = new File("src/main/resources/lecturers/" + username);
                    if (!lecturerFolder.exists()) {
                        if (!lecturerFolder.mkdir()){
                            System.out.println("Failed to create student folder.");
                        }
                    }

                    // create empty json files
                    File moduleFile = new File("src/main/resources/lecturers/" + username + "/module.json");

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

    private static class RetrievePasswordActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id = JOptionPane.showInputDialog("Please enter your student ID:");

            if (!id.matches("\\d{10}")) {
                JFrame tip = new JFrame();
                JOptionPane.showMessageDialog(tip, "Please ensure that your student ID is a 10-digit number.");
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
                        //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
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
                        //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
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
                    JOptionPane.showMessageDialog(null, "There is no account associated with this student id.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to read the file: " + ex.getMessage());
            }
        }
    }

    private static class RecoverPasswordActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id = JOptionPane.showInputDialog("Please enter your student ID:");

            if (!id.matches("\\d{10}")) {
                JFrame tip = new JFrame();
                JOptionPane.showMessageDialog(tip, "Please ensure that your student ID is a 10-digit number.");
                return;
            }
            if (userType == 0) {
                try {
                    String username = "";
                    String password = "";

                    BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
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
                        }
                        else if (i == list.size() - 1) {
                            JOptionPane.showMessageDialog(null, "There is no account associated with this student id.");
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

                    BufferedReader reader = new BufferedReader(new FileReader("lecturers.txt"));
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
                        }
                        else if (i == list.size() - 1) {
                            JOptionPane.showMessageDialog(null, "There is no account associated with this student id.");
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

    private class quitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> new StartPage().setVisible(true));
            dispose();
        }
    }
}
