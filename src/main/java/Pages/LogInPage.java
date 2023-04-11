package main.java.Pages;

import com.formdev.flatlaf.FlatDarculaLaf;
import main.java.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogInPage extends JFrame implements ActionListener {
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
        usernameField.setPreferredSize(new Dimension (180,35));
        passwordField.setPreferredSize(new Dimension (180,35));

        // 设置按钮
        JButton loginButton = new JButton("Log in");
        JButton registerButton = new JButton("Sign up");
        JButton retrievePasswordButton = new JButton("Forgot PWD");
        JButton recoverPasswordButton = new JButton("Change PWD");
        JButton quitButton = new JButton("Exit");


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

        // 在平面中加入
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(retrievePasswordButton);
        panel.add(recoverPasswordButton);
        panel.add(new JLabel());
        setBounds(500,300,1094,729);
        setLocationRelativeTo(null);
        setResizable(false);

        // 在背景上放置平面
        backgroundImage.add(panel);
        backgroundImage.setSize(panel.getSize());
        add(backgroundImage);

        // 设置背景图片大小为窗口大小
//        setSize(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());
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
        panel.add(loginButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(registerButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(retrievePasswordButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(recoverPasswordButton, gbc);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        FlatDarculaLaf.install();
        new LogInPage();
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!username.matches("[a-zA-Z\u4e00-\u9fa5]{1,10}")) {
            JOptionPane.showMessageDialog(this, "用户名格式有误，请输入汉字或者英文字符。");
            return;
        }
        if (!password.matches("[a-zA-Z0-9]{6,10}")) {
            JOptionPane.showMessageDialog(this, "密码格式有误，请输入6-10位数字或英文字符。");
            return;
        }

        try {
            boolean loggedIn = false;
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

            if (loggedIn) {
//                JOptionPane.showMessageDialog(this, "登录成功！");
                Config.setUsername(username);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new HomePage().setVisible(true);
                    }
                });
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "读取文件时发生错误: " + ex.getMessage());
        }
    }

    private static class RegisterActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel registerPanel = new JPanel();
            JFrame registerWindow = new JFrame("Sign up");
            JLabel usernameLabel = new JLabel("Username:");
            JLabel passwordLabel = new JLabel("Password:");
            JLabel phoneLabel = new JLabel("Phone number:");
            JTextField usernameField = new JTextField(10);
            JPasswordField passwordField = new JPasswordField(10);
            JTextField phoneField = new JTextField(10);
            JButton registerButton = new JButton("Sign up");

            registerPanel.add(usernameLabel);
            registerPanel.add(usernameField);
            registerPanel.add(passwordLabel);
            registerPanel.add(passwordField);
            registerPanel.add(phoneLabel);
            registerPanel.add(phoneField);
            registerPanel.add(new JLabel());
            registerPanel.add(registerButton);

            registerButton.addActionListener(e1 -> {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String phone = phoneField.getText().trim();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                    String line1;
                    while ((line1 = reader.readLine()) != null) {
                        String[] sparts = line1.split(" ");
                        if (sparts[0].equals(username) || sparts[2].equals(phone)) {
                            JOptionPane.showMessageDialog(registerWindow, "用户名或手机号码已存在，请重新输入。");
                            return;
                        }
                        reader.close();
                    }
                } catch (IOException ignored) {
                }

                if (!username.matches("[a-zA-Z\u4e00-\u9fa5]{1,10}")) {
                    JOptionPane.showMessageDialog(registerWindow, "用户名格式有误，请输入汉字或者英文字符。");
                    return;
                }
                if (!password.matches("[a-zA-Z0-9]{6,10}")) {
                    JOptionPane.showMessageDialog(registerWindow, "密码格式有误，请输入6-10位数字或英文字符。");
                    return;
                }
                if (!phone.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(registerWindow, "电话号码格式有误，请输入11位数字。");
                    return;
                }

                File studentFolder = new File("src/main/resources/students/" + username);
                if (!studentFolder.exists()) {
                    studentFolder.mkdir();
                }

                // create empty json files
                File moduleFile = new File("src/main/resources/students/" + username + "/module.json");
                File achievementFile = new File("src/main/resources/students/" + username + "/achievement.json");

                if (!moduleFile.exists()) {
                    FileWriter moduleWriter;
                    try {
                        moduleWriter = new FileWriter(moduleFile);
                        moduleWriter.write("[]");
                        moduleWriter.close();                    } catch (IOException ex) {
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
                    writer.write(username + " " + password + " " + phone);
                    writer.newLine();
                    JOptionPane.showMessageDialog(registerWindow, "注册成功！");
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(registerWindow, "读取文件时发生错误: " + ex.getMessage());
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
            String phone = JOptionPane.showInputDialog("请输入电话号码:");

            if (!phone.matches("\\d{11}")) {
                JFrame tip = new JFrame();
                JOptionPane.showMessageDialog(tip, "电话号码格式有误，请输入11位数字。");
                return;
            }

            try {
                boolean found = false;
                String password = "";
                String username = "";
                BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                String line2;
                while ((line2 = reader.readLine()) != null) {
                    //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
                    String[] reparts = line2.split(" ");
                    if (reparts[2].equals(phone)) {
                        found = true;
                        password = reparts[1];
                        username = reparts[0];
                        break;
                    }
                }
                reader.close();

                if (found) {
                    JOptionPane.showMessageDialog(null, "你的用户名是: " + username + "\n" + "你的密码是: " + password);
                } else {
                    JOptionPane.showMessageDialog(null, "没有与此电话号码对应的账号。");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "读取文件时发生错误: " + ex.getMessage());
            }
        }
    }

    private static class RecoverPasswordActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String phone = JOptionPane.showInputDialog("请输入电话号码:");

            if (!phone.matches("\\d{11}")) {
                JFrame tip = new JFrame();
                JOptionPane.showMessageDialog(tip, "电话号码格式有误，请输入11位数字。");
                return;
            }

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
                for(int i = 0; i < list.size(); i++) {
                String pw = list.get(i); 
                    //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
                    String[] dpart = pw.split(" ");
                    if (phone.equals(dpart[2])) {
                        username = dpart[0];
                        String newpassword = JOptionPane.showInputDialog(username + "，你好！请输入新密码:");
                        if (!newpassword.matches("[a-zA-Z0-9]{6,10}")) {
                            JOptionPane.showMessageDialog(null, "修改密码失败！\n密码格式有误，请输入6-10位数字或英文字符。");
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
                    if(!phone.equals(dpart[2])&&i==list.size()-1){
                        JOptionPane.showMessageDialog(null, "没有与此电话号码对应的账号。");
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
                writer.write(username + " " + password + " " + phone);
                writer.newLine();
                writer.close();
                JOptionPane.showMessageDialog(null, "修改密码成功！");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "读取文件时发生错误: " + ex.getMessage());
            }
        }
    }

    private class quitActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new StartPage().setVisible(true);
                }
            });
            dispose();
        }
    }
}
