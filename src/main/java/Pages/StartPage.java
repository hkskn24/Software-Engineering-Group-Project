package main.java.Pages;

import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class StartPage extends MyPage {

    public StartPage() {
        setTitle("WELCOME TO LUMOSLEARNING");

        // Set the background image
        try {
            InputStream inputStream = getClass().getResourceAsStream("/main/resources/images/startpage.jpg");
            BufferedImage image = ImageIO.read(inputStream);
            ImageIcon icon = new ImageIcon(image);
            JLabel backgroundImage = new JLabel(icon);
            backgroundImage.setLayout(new GridBagLayout());
            setContentPane(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Welcome to LumosLearning");
        try {
            InputStream inputStream = getClass().getResourceAsStream("/main/resources/fonts/ParryHotter-1.ttf");

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            label.setFont(font.deriveFont(Font.BOLD, 60f));

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
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
        try {
            InputStream inputStream = getClass().getResourceAsStream("/main/resources/fonts/HARRYP-1.ttf");

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            startButton.setFont(font.deriveFont(Font.BOLD, 27f));

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        startButton.addActionListener(e -> {
            new LogInPage().setVisible(true);
            dispose();
        });
        gbc.gridy = 1;
        getContentPane().add(startButton, gbc);
    }

    public static void main(String[] args) {
        FlatLightLaf.install();

        UIManager.put("defaultFont", new Font("Dialog", Font.PLAIN, 18));

        Locale.setDefault(Locale.ENGLISH);

        new StartPage().setVisible(true);
    }
}
