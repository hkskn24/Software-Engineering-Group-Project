package main.java.Pages.LecturerPages;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.java.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;


public class InformationPage extends JFrame {
    private JTextField emailTextField;

    public InformationPage() {
        setTitle("Information Page");
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        setupInfoPanel(infoPanel);
        add(infoPanel);

        addBackButton(infoPanel);
        addUpdateButton(infoPanel);

        // 添加滚动条
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(scrollPane);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new HomePage().setVisible(true);
                dispose();
            }
        });

    }

    private void addBackButton(JPanel infoPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            new HomePage().setVisible(true);
            dispose();
        });
        infoPanel.add(backButton, gbc);
        setLocationRelativeTo(null);
    }

    private void addUpdateButton(JPanel infoPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;

        JButton updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 14));
        updateButton.addActionListener(e -> {
            Config.setUserEmail(emailTextField.getText());
            // 更新json文件
            updateJsonFile();
        });
        infoPanel.add(updateButton, gbc);
        setLocationRelativeTo(null);
    }

    private void updateJsonFile() {
        // 这里替换为您的json文件路径
        String jsonFilePath = "src/main/resources/data/lecturers/" + Config.getUsername() + "/information.json";
        try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
            // 创建一个JsonObject并添加用户信息
            JsonObject userInfo = new JsonObject();
            userInfo.addProperty("username", Config.getUsername());
            userInfo.addProperty("id", Config.getUserId());
            userInfo.addProperty("email", Config.getUserEmail());

            // 创建一个JsonArray并将userInfo添加到数组
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(userInfo);

            // 使用Gson库将JsonArray对象序列化为json字符串
            Gson gson = new Gson();
            String jsonString = gson.toJson(jsonArray);
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setupInfoPanel(JPanel infoPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // get Name
        addInfoSection(infoPanel, "Name", gbc, Config.getUsername().trim(), 1);

        // get Id
        addInfoSection(infoPanel, "Id", gbc, String.valueOf(Config.getUserId()), 3);

        // set Email
        addEditableInfoSection(infoPanel, gbc, Config.getUserEmail());

        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private void addInfoSection(JPanel panel, String text, GridBagConstraints gbc, String content, int rowIndex) {
        JLabel sectionTitle = new JLabel(text);
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = rowIndex;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(sectionTitle, gbc);

        JTextArea sectionContent = new JTextArea(content);
        sectionContent.setLineWrap(true);
        sectionContent.setWrapStyleWord(true);
        sectionContent.setEditable(false);
        sectionContent.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = rowIndex + 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(sectionContent, gbc);
    }

    private void addEditableInfoSection(JPanel panel, GridBagConstraints gbc, String content) {
        JLabel sectionTitle = new JLabel("Email");
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 14));
        int rowIndex = 5;
        gbc.gridx = 0;
        gbc.gridy = rowIndex;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(sectionTitle, gbc);

        emailTextField = new JTextField(content);
        emailTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = rowIndex + 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(emailTextField, gbc);
    }
}
