package main.java.Pages;

import com.formdev.flatlaf.FlatDarculaLaf;
import main.java.Config;
import main.java.Controller.RecoverPasswordActionListener;
import main.java.Controller.RegisterActionListener;
import main.java.Controller.RetrievePasswordActionListener;
import main.java.Pages.LecturerPages.HomePage;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.*;

public class LogInPage extends JFrame implements ActionListener {
    public static int userType; //student:0 lecturer:1
    final JTextField usernameField;
    final JPasswordField passwordField;

    public LogInPage() {
        super("LOG IN TO YOUR ACCOUNT");
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(false);
            }
        };
        panel.setOpaque(false);

        // set student or lecturer
        JCheckBox studentCheckbox = new JCheckBox("Student");
        studentCheckbox.setFont(new Font("Arial", Font.ITALIC, 17));
        studentCheckbox.setForeground(Color.BLACK);
        JCheckBox lecturerCheckbox = new JCheckBox("Lecturer");
        lecturerCheckbox.setFont(new Font("Arial", Font.ITALIC, 17));
        lecturerCheckbox.setForeground(Color.BLACK);

        ButtonGroup group = new ButtonGroup();
        group.add(studentCheckbox);
        group.add(lecturerCheckbox);

        // set background
        ImageIcon backgroundImageIcon = null;
        try {
            backgroundImageIcon = new ImageIcon(ImageIO.read(getClass().getResource("../../resources/images/login2.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel backgroundImage = new JLabel(backgroundImageIcon);
        backgroundImage.setLayout(new GridLayout(0, 2, 4, 5));

        // set text label
        JLabel usernameLabel = new JLabel(" Username");
        JLabel passwordLabel = new JLabel(" Password");
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/fonts/HARRYP-1.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            usernameLabel.setFont(font.deriveFont(Font.PLAIN, 60f));
            passwordLabel.setFont(font.deriveFont(Font.PLAIN, 60f));

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        usernameLabel.setForeground(Color.WHITE); // 设置文本颜色为白色
        passwordLabel.setForeground(Color.WHITE); // 设置文本颜色为白色

        // set test field
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setPreferredSize(new Dimension(180, 35));
        usernameField.setFont(new Font("Arial", Font.ITALIC, 20));
        passwordField.setPreferredSize(new Dimension(180, 35));
        passwordField.setFont(new Font("Arial", Font.ITALIC, 20));
        passwordField.setEchoChar('*');

        // set button
        JButton loginButton = new JButton("Log in");
        JButton registerButton = new JButton("Sign up");
        JButton retrievePasswordButton = new JButton("Forgot PWD");
        JButton recoverPasswordButton = new JButton("Change PWD");

        // set color
        Color buttonColor = Color.BLACK;
        Color buttonTextColor = Color.WHITE;

        // set button item
        JButton[] buttons = {loginButton, registerButton, retrievePasswordButton, recoverPasswordButton};
        for (JButton button : buttons) {
            try {
                InputStream inputStream = new BufferedInputStream(
                        new FileInputStream("src/main/resources/fonts/HARRYP-1.ttf"));

                Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

                button.setFont(font.deriveFont(Font.BOLD, 27f));

            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            button.setBackground(buttonColor);
            button.setForeground(buttonTextColor);
            button.setPreferredSize(new Dimension(200, 50)); // 增加按钮宽度以显示完整文本
        }

        // set ActionListener
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
        loginButton.addActionListener(this);
        registerButton.addActionListener(e -> {
            ActionListener registerActionListener = new RegisterActionListener(userType);
            registerActionListener.actionPerformed(e);
        });
        retrievePasswordButton.addActionListener(e -> {
            ActionListener retrievePasswordActionListener = new RetrievePasswordActionListener(userType);
            retrievePasswordActionListener.actionPerformed(e);
        });
        recoverPasswordButton.addActionListener(e -> {
            ActionListener recoverPasswordActionListener = new RecoverPasswordActionListener(userType);
            recoverPasswordActionListener.actionPerformed(e);
        });

        // put on the panel
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

        // put the panel on the background
        backgroundImage.add(panel);
        backgroundImage.setSize(panel.getSize());
        add(backgroundImage);

        // set GridBagConstraints
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
                        Config.setUserType("students");
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
                        Config.setUserType("lecturers");
                        loggedIn = true;
                        break;
                    }
                }
                reader.close();
            }
            if (loggedIn && userType == 0) {
                Config.setUsername(username);
                SwingUtilities.invokeLater(() -> new main.java.Pages.StudentsPages.HomePage().setVisible(true));
                dispose();
            } else if (loggedIn && userType == 1) {
                Config.setUsername(username);
                SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to read the file: " + ex.getMessage());
        }
    }
}
