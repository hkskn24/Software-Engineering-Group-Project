package main.java.Pages;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StartPage extends JFrame {

    public StartPage() {
        setTitle("WELCOME TO LUMOSLEARNING");
        setBounds(500,300,1094,729);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the background image
        try {
            JLabel backgroundImage = new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/startpage.JPG"))));
            backgroundImage.setLayout(new GridBagLayout());
            setContentPane(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Welcome to LumosLearning");
        label.setFont(new Font("Monotype Corsiva", Font.BOLD, 80));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(label, gbc);

        JButton startButton = new JButton("LOG IN");
        Color startButtoncolor = Color.BLACK;
        Color startButtonTextColor = Color.WHITE;
        startButton.setBackground(startButtoncolor);
        startButton.setForeground(startButtonTextColor);
        startButton.setFont(new Font("Arial", Font.BOLD, 35));
        startButton.addActionListener(e -> {
            new LogInPage().setVisible(true);
            dispose();
        });
        gbc.gridy = 1;
        getContentPane().add(startButton, gbc);
    }

    public static void main(String[] args) {
        new StartPage().setVisible(true);
    }
}
