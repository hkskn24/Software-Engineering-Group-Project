package main.java.Pages.LecturerPages;

import main.java.Pages.LogInPage;
import main.java.Pages.MyPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class HomePage extends MyPage {
    public HomePage() {
        setTitle("HomePage");

        // Set the background image
        try {
            JLabel backgroundImage = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("../../../resources/images/lhomepage.jpg"))));
            backgroundImage.setLayout(new GridBagLayout());

            Dimension buttonSize = new Dimension(800, 50);
            JButton btnLec_ModulePage = new JButton("Add Module");
            btnLec_ModulePage.setPreferredSize(buttonSize);
            JButton btnLec_StudentPage = new JButton("Join Module");
            btnLec_StudentPage.setPreferredSize(buttonSize);
            JButton btnOngoingcourses = new JButton("View ongoing courses");
            btnOngoingcourses.setPreferredSize(buttonSize);
            JButton btnCompletedcourses = new JButton("View completed courses");
            btnCompletedcourses.setPreferredSize(buttonSize);
            JButton btnBack = new JButton("Log out");
            btnBack.setPreferredSize(buttonSize);

            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    LogInPage logInPage = new LogInPage();
                    logInPage.setVisible(true);
                }
            });

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

            btnLec_ModulePage.addMouseListener(mouseAdapter);
            btnLec_StudentPage.addMouseListener(mouseAdapter);
            btnOngoingcourses.addMouseListener(mouseAdapter);
            btnCompletedcourses.addMouseListener(mouseAdapter);
            btnBack.addMouseListener(mouseAdapter);

            // add ActionListener for buttons
            btnLec_ModulePage.addActionListener(e -> {
                new AddModulePage();
                dispose();
            });

            btnLec_StudentPage.addActionListener(e -> {
                new JoinPage();
                dispose();
            });

            btnOngoingcourses.addActionListener(e -> {
                new OngoingModulePage();
                dispose();
            });

            btnCompletedcourses.addActionListener(e -> {
                new CompletedModulePage();
                dispose();
            });

            btnBack.addActionListener(e -> {
                new LogInPage().setVisible(true);
                dispose();
            });

            // set color and font
            Color btnLec_ModulePageColor = new Color(96, 33, 46);
            Color btnLec_StudentPageColor = new Color(179, 122, 54);
            Color btnOngoingcoursesColor = new Color(25, 60, 79);
            Color btnCompletedcoursesColor = new Color(34, 68, 49);
            Color btnBackColor = new Color(58, 40, 79);
            Color buttonTextColor = Color.WHITE;
            Font buttonFont = new Font("Verdana", Font.BOLD, 23);

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
            btnBack.setBackground(btnBackColor);
            btnBack.setForeground(buttonTextColor);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 1, 0, 10));

            panel.add(btnLec_ModulePage);
            panel.add(btnLec_StudentPage);
            panel.add(btnOngoingcourses);
            panel.add(btnCompletedcourses);
            panel.add(btnBack);
            panel.setOpaque(false);

            add(panel);

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 1;
            c.insets = new Insets(20, 0, 0, 0);
            backgroundImage.add(panel, c);

            setContentPane(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}