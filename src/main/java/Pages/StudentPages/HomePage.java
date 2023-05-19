package main.java.Pages.StudentPages;

import com.formdev.flatlaf.FlatDarculaLaf;
import main.java.Controller.DateTimePanel;
import main.java.Pages.LogInPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("HomePage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);

        // Set the background image
        try {
            JLabel backgroundImage = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("../../../resources/images/homepage.jpg"))));
            backgroundImage.setLayout(new GridBagLayout());
            // 创建按钮并设置大小
            Dimension buttonSize = new Dimension(200, 50);
            JButton btnModulePage = new JButton("Module");
            btnModulePage.setPreferredSize(buttonSize);
            JButton btnGradePage = new JButton("Grade");
            btnGradePage.setPreferredSize(buttonSize);
            JButton btnAchievement = new JButton("Achievement");
            btnAchievement.setPreferredSize(buttonSize);
            JButton btnAssessment = new JButton("Assessment");
            btnAssessment.setPreferredSize(buttonSize);
            JButton btnBack = new JButton("Log out");
            btnBack.setPreferredSize(buttonSize);

            // 添加边距
            int margin = 10;
            btnModulePage.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
            btnGradePage.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
            btnAchievement.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
            btnAssessment.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
            btnBack.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));

            // 添加动态效果
            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    Color originalColor = button.getBackground();
                    button.setBackground(originalColor.darker());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    Color originalColor = button.getBackground();
                    button.setBackground(originalColor.brighter());
                }
            };

            btnModulePage.addMouseListener(mouseAdapter);
            btnGradePage.addMouseListener(mouseAdapter);
            btnAchievement.addMouseListener(mouseAdapter);
            btnAssessment.addMouseListener(mouseAdapter);
            btnBack.addMouseListener(mouseAdapter);


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
            
            btnAssessment.addActionListener(e -> {
                new AssessmentPage().setVisible(true);
                dispose();
            });

            btnBack.addActionListener(e -> {
                new LogInPage().setVisible(true);
                dispose();
            });

            // 设置按钮颜色和文字颜色
            Color btnModulePageColor = new Color(96, 33, 46);
            Color btnGradePageColor = new Color(179, 122, 54);
            Color btnAchievementColor = new Color(25, 60, 79);
            Color btnAssessmentColor = new Color(34, 68, 49);
            Color btnBackColor = new Color(58, 40, 79);
            Color buttonTextColor = Color.WHITE;
            Font buttonFont = new Font("Verdana", Font.BOLD, 23);

            btnModulePage.setFont(buttonFont);
            btnModulePage.setBackground(btnModulePageColor);
            btnModulePage.setForeground(buttonTextColor);

            btnGradePage.setFont(buttonFont);
            btnGradePage.setBackground(btnGradePageColor);
            btnGradePage.setForeground(buttonTextColor);

            btnAchievement.setFont(buttonFont);
            btnAchievement.setBackground(btnAchievementColor);
            btnAchievement.setForeground(buttonTextColor);
            
            btnAssessment.setFont(buttonFont);
            btnAssessment.setBackground(btnAssessmentColor);
            btnAssessment.setForeground(buttonTextColor);

            btnBack.setFont(buttonFont);
            btnBack.setBackground(btnBackColor);
            btnBack.setForeground(buttonTextColor);

            JPanel panel = new JPanel(new FlowLayout());

            DateTimePanel DTpanel = new DateTimePanel();
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            // 将按钮添加到窗口
            panel.add(btnModulePage);
            panel.add(btnGradePage);
            panel.add(btnAchievement);
            panel.add(btnAssessment);
            panel.add(btnBack);
            panel.setOpaque(false);
            DTpanel.setOpaque(false);

            add(DTpanel);
            add(panel);

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(50, 0, 0, 0);
            backgroundImage.add(DTpanel, c);

            c.gridx = 0;
            c.gridy = 1;
            c.insets = new Insets(20, 0, 0, 0);
            backgroundImage.add(panel, c);

            setContentPane(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
