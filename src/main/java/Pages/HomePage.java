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

        // 将按钮添加到窗口
        add(btnModulePage);
        add(btnGradePage);
        add(btnAchievement);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}

