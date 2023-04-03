package main.java.Pages;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LogInPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogInPage() {
        super("学生信息管理系统");
        JPanel panel = new JPanel(new GridLayout(5,1,4,4));
        JLabel usernameLabel = new JLabel("姓名:");
        JLabel passwordLabel = new JLabel("密码:");
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        JButton retrievePasswordButton = new JButton("密码找回");
        JButton recoverPasswordButton = new JButton("修改密码");
        JButton quitButton = new JButton("退出");
        quitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(retrievePasswordButton);
        panel.add(recoverPasswordButton);
        panel.add(quitButton);
        panel.add(new JLabel());

        loginButton.addActionListener(this);
        registerButton.addActionListener(new RegisterActionListener());
        retrievePasswordButton.addActionListener(new RetrievePasswordActionListener());
        recoverPasswordButton.addActionListener(new RecoverPasswordActionListener());

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!username.matches("[a-zA-Z\u4e00-\u9fa5]{1,10}")) {
            JOptionPane.showMessageDialog(this,"用户名格式有误，请输入汉字或者英文字符。");
            return;
        }
        if (!password.matches("[a-zA-Z0-9]{6,10}")) {
            JOptionPane.showMessageDialog(this,"密码格式有误，请输入6-10位数字或英文字符。");
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
                JOptionPane.showMessageDialog(this, "登录成功！");
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "读取文件时发生错误: " + ex.getMessage());
        }
    }

    private class RegisterActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame registerWindow = new JFrame("账号注册");
            JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
            JLabel usernameLabel = new JLabel("姓名:");
            JLabel passwordLabel = new JLabel("密码:");
            JLabel phoneLabel = new JLabel("电话号码:");
            JTextField usernameField = new JTextField(10);
            JPasswordField passwordField = new JPasswordField(10);
            JTextField phoneField = new JTextField(10);
            JButton registerButton = new JButton("注册");

            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(phoneLabel);
            panel.add(phoneField);
            panel.add(new JLabel());
            panel.add(registerButton);

            registerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    String phone = phoneField.getText().trim();
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                        String line1;
                        while ((line1 = reader.readLine()) != null) {
                            String[] sparts = line1.split(" ");
                            if (sparts[0].equals(username)||sparts[2].equals(phone)) {
                                JOptionPane.showMessageDialog(registerWindow, "用户名或手机号码已存在，请重新输入。");
                                return;
                            }
                            reader.close();
                        }
                    }catch (IOException ex) {
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
                }
            });

            registerWindow.add(panel);
            registerWindow.pack();
            registerWindow.setLocationRelativeTo(null);
            registerWindow.setVisible(true);
        }
    }

    private class RetrievePasswordActionListener implements ActionListener {
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
                    JOptionPane.showMessageDialog(null, "你的用户名是: " + username +"\n"+ "你的密码是: " + password);
                } else {
                    JOptionPane.showMessageDialog(null, "没有与此电话号码对应的账号。");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "读取文件时发生错误: " + ex.getMessage());
            }
        }
    }

    private class RecoverPasswordActionListener implements ActionListener {
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
                while((line3 = reader.readLine()) != null){
                    list.add(line3);
                }
                reader.close();
                for(String pw : list) {
                    //for (String line : new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("students.txt"))).split("\\r?\\n")) {
                    String dpart[] = pw.split(" ");
                    if (phone.equals(dpart[2])) {
                        username = dpart[0];
                        String newpassword = JOptionPane.showInputDialog(username+"，你好！请输入新密码:");
                        if (!newpassword.matches("[a-zA-Z0-9]{6,10}")) {
                            JOptionPane.showMessageDialog(null, "修改密码失败！\n密码格式有误，请输入6-10位数字或英文字符。");
                            return;
                        }
                        else{
                            list.remove(pw);
                            password = newpassword;
                            FileWriter fout = new FileWriter("students.txt",false);
                            fout.write("");
                            fout.close();
                            break;
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "没有与此电话号码对应的账号。");
                    }
                }
                for (String pw : list) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
                    writer.write(pw);
                    writer.newLine();
                    writer.close();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true));
                writer.write(username+" "+ password +" "+phone);
                writer.newLine();
                writer.close();
                JOptionPane.showMessageDialog(null, "修改密码成功！");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "读取文件时发生错误: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new LogInPage();
    }
}