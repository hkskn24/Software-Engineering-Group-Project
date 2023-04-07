package main.java.Pages;

import main.java.Entity.Achievement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("HomePage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,300,1094,729);
        setLocationRelativeTo(null);


        // Set the background image
        try {
            JLabel backgroundImage = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/homepage.JPG"))));
            backgroundImage.setLayout(new GridBagLayout());
            setContentPane(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建三个按钮
        JButton btnModulePage = new JButton("ModulePage");
        JButton btnGradePage = new JButton("GradePage");
        JButton btnAchievement = new JButton("Achievement");
        JButton btnBack = new JButton("Back");

        // 为按钮添加事件监听器
        btnModulePage.addActionListener(e -> {
            new ModulePage().setVisible(true);
            dispose();
        });

        btnGradePage.addActionListener(e -> {
            new GradePage().setVisible(true);
            dispose();
        });

        btnAchievement.addActionListener(e -> {
            new AchievementPage().setVisible(true);
            dispose();
        });

//        btnAchievement.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new Achievement().setVisible(true);
//                dispose();
//            }
//        });

        btnBack.addActionListener(e -> {
            new LogInPage().setVisible(true);
            dispose();
        });

        // 设置按钮颜色和文字颜色
        Color btnModulePageColor = new Color(96,33,46);
        Color btnGradePageColor = new Color(179,122,54);
        Color btnAchievementColor = new Color(25,60,79);
        Color btnBackColor = new Color(34,68,49);
        Color buttonTextColor = Color.WHITE;
        Font buttonFont = new Font("Segoe Script", Font.BOLD, 25);

        btnModulePage.setFont(buttonFont);
        btnModulePage.setBackground(btnModulePageColor);
        btnModulePage.setForeground(buttonTextColor);

        btnGradePage.setFont(buttonFont);
        btnGradePage.setBackground(btnGradePageColor);
        btnGradePage.setForeground(buttonTextColor);

        btnAchievement.setFont(buttonFont);
        btnAchievement.setBackground(btnAchievementColor);
        btnAchievement.setForeground(buttonTextColor);

        btnBack.setFont(buttonFont);
        btnBack.setBackground(btnBackColor);
        btnBack.setForeground(buttonTextColor);

        // 将按钮添加到窗口
        add(btnModulePage);
        add(btnGradePage);
        add(btnAchievement);
        add(btnBack);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}

