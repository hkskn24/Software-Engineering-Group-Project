package main.java.Pages;

import main.java.Entity.Achievement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Lec_HomePage extends JFrame {
    public Lec_HomePage() {
        setTitle("HomePage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 1094, 729);
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
        JButton btnLec_ModulePage = new JButton("Add Module");
        JButton btnLec_StudentPage = new JButton("Join Module");
        JButton btnOngoingcourses = new JButton("View ongoing courses");
        JButton btnCompletedcourses = new JButton("View completed courses");
        JButton btnBack = new JButton("Log out");

        // 为按钮添加事件监听器
        btnLec_ModulePage.addActionListener(e -> {
            new Lec_ModulePage().setVisible(true);
            dispose();
        });

        btnLec_StudentPage.addActionListener(e -> {
//            new Lec_StudentPage().setVisible(true);
            dispose();
        });

        btnOngoingcourses.addActionListener(e -> {
//            new OngoingCoursesPage().setVisible(true);
            dispose();
        });

        btnCompletedcourses.addActionListener(e -> {
//            new CompletedCoursesPage().setVisible(true);
            dispose();
        });

        btnBack.addActionListener(e -> {
            new LogInPage().setVisible(true);
            dispose();
        });

        // 设置按钮颜色和文字颜色
        Color btnLec_ModulePageColor = new Color(96, 33, 46);
        Color btnLec_StudentPageColor = new Color(179, 122, 54);
        Color btnOngoingcoursesColor = new Color(25, 60, 79);
        Color btnCompletedcoursesColor = new Color(34, 68, 49);
        Color buttonTextColor = Color.WHITE;
        Font buttonFont = new Font("Segoe Script", Font.BOLD, 25);

        btnLec_ModulePage.setFont(buttonFont);
        btnLec_ModulePage.setBackground(btnLec_ModulePageColor);
        btnLec_ModulePage.setForeground(buttonTextColor);

        btnLec_StudentPage.setFont(buttonFont);
        btnLec_StudentPage.setBackground(btnLec_StudentPageColor);
        btnLec_StudentPage.setForeground(buttonTextColor);

        btnOngoingcourses.setFont(buttonFont);
        btnOngoingcourses.setBackground(btnOngoingcoursesColor);
        btnOngoingcourses.setForeground(buttonTextColor);

        btnCompletedcourses.setFont(buttonFont);
        btnCompletedcourses.setBackground(btnCompletedcoursesColor);
        btnCompletedcourses.setForeground(buttonTextColor);

        btnBack.setFont(buttonFont);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(buttonTextColor);

        // 创建一个按钮面板并设置布局
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // 设置面板为透明

        // 将按钮添加到面板
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 200))); // 添加间距
        buttonPanel.add(btnLec_ModulePage);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 添加间距
        buttonPanel.add(btnLec_StudentPage);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 添加间距
        buttonPanel.add(btnOngoingcourses);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 添加间距
        buttonPanel.add(btnCompletedcourses);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 添加间距
        buttonPanel.add(btnBack);

        // 将按钮面板添加到窗口
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1.0;
        add(buttonPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lec_HomePage().setVisible(true));
    }
}
