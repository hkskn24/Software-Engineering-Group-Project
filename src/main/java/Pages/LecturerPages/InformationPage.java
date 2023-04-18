package main.java.Pages.LecturerPages;

import main.java.Entity.Module;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;


public class InformationPage extends JFrame {
    private JTextField emailTextField;

    public InformationPage(Module module) {
        setTitle("Information Page");
        setBounds(500, 300, 1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        setupInfoPanel(infoPanel, module);
        add(infoPanel);

        addBackButton(infoPanel);
        addUpdateButton(infoPanel, module);

        // 添加滚动条
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(scrollPane);
        setVisible(true);
    }

    private void addBackButton(JPanel infoPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> dispose());
        infoPanel.add(backButton, gbc);
        setLocationRelativeTo(null);
    }

    private void addUpdateButton(JPanel infoPanel, Module currentUserData) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;

        JButton updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 14));
        updateButton.addActionListener(e -> {
            currentUserData.setEmail(emailTextField.getText());
            // 更新json文件
            updateJsonFile(currentUserData);
        });
        infoPanel.add(updateButton, gbc);
        setLocationRelativeTo(null);
    }

    private void updateJsonFile(Module module) {
        // 这里替换为您的json文件路径
        String jsonFilePath = "src/main/resources/data/lecturers/"+module.getName()+"/information.json";
        try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
            // 使用您喜欢的json库将Module对象序列化为json字符串
            String jsonString = serializeModuleToJson(module);
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeModuleToJson(Module module) {
        // 使用您喜欢的json库将Module对象序列化为json字符串
        // 这里需要您根据您的项目使用的json库来实现具体的序列化代码
        return "";
    }

    private void setupInfoPanel(JPanel infoPanel, Module module) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // get Name
        addInfoSection(infoPanel, gbc, "Name", module.getName().trim(), 1);

        // get Id
        addInfoSection(infoPanel, gbc, "Id", String.valueOf(module.getId()), 3);

        // set Email
        addEditableInfoSection(infoPanel, gbc, "Email", module.getEmail(), 5);

        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private void addInfoSection(JPanel panel, GridBagConstraints gbc, String title, String content, int rowIndex) {
        JLabel sectionTitle = new JLabel(title);
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

    private void addEditableInfoSection(JPanel panel, GridBagConstraints gbc, String title, String content, int rowIndex) {
        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 14));
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
