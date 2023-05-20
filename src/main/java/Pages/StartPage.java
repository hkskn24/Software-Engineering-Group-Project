package main.java.Pages;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Locale;

public class StartPage extends JFrame {

    public StartPage() {
        setTitle("WELCOME TO LUMOSLEARNING");
        setBounds(500, 300, 900, 599);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the background image
        try {
            JLabel backgroundImage = new JLabel();
            ImageIcon ii = new ImageIcon(ImageIO.read(getClass().getResource("../../resources/images/startpage.jpg")));
            ii.setImage(ii.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
            backgroundImage.setIcon(ii);
            backgroundImage.setLayout(new GridBagLayout());
            setContentPane(backgroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Welcome to LumosLearning");
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/fonts/ParryHotter-1.ttf"));

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
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/fonts/HARRYP-1.ttf"));

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

        UIManager.put("defaultFont",  new Font("Dialog", Font.PLAIN, 18));

        Locale.setDefault(Locale.ENGLISH);

        new StartPage().setVisible(true);
    }
}
