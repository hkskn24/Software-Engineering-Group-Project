package main.java.Pages;

import main.java.Entity.Module;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ModuleInfoPage extends JFrame {
    public ModuleInfoPage(Module module) {
        setTitle(module.getName());
        setBounds(500,300,1094,729);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        setupInfoPanel(infoPanel, module);
        add(infoPanel);

        addBackButton(infoPanel);

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
        backButton.addActionListener(e -> {
            dispose();
        });
        infoPanel.add(backButton, gbc);
    }

    private void setupInfoPanel(JPanel infoPanel, Module module) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // set Module Title
        JLabel title = new JLabel(module.getName());
        title.setFont(new Font("Lucida Calligraphy", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(title, gbc);

        // set Lecturer
        addInfoSection(infoPanel, gbc, "Lecturer", module.getLecturer(), 1);

        // set Summary
        addInfoSection(infoPanel, gbc, "Summary", module.getSummary(), 3);

        // set Aims
        addInfoSection(infoPanel, gbc, "Aims", module.getAims(), 5);

        // set syllabus
        addInfoSection(infoPanel, gbc, "Syllabus", module.getSyllabus(),7);

        //set Reading List
        addInfoSection(infoPanel, gbc, "Reading List", module.getReadingList(), 9);

        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private void addInfoSection(JPanel panel, GridBagConstraints gbc, String title, String content, int rowIndex) {
        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
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

    public static void main(String[] args) {
        Module module = new Module();
        module.setName("Advanced Network Programming");
        module.setSummary("This module builds on the students' Java programming skills to equip them with the conceptual understanding of a selection of advanced topics in network programming.\n" +
                "The module contains a coursework component requiring each student to understand how to develop a distributed application such as an e-commerce platform, implemented using several of the technologies introduced in the lectures.\n" +
                "\n");
        module.setAims("The course aims to give students that are familiar with programming:\n" +
                "\n" +
                "Understanding of the client-server model.\n" +
                "Knowledge of the varied technologies used for developing internet platforms and the context in which they are applicable.\n" +
                "Ability to decide on the appropriate technology to use for a given application.\n" +
                "Practical experience of applying a selection of the technologies.\n");
        module.setLecturer("Alice");
        module.setReadingList("Description\tCategory\n" +
                "\"An Introduction to Network Programming with Java\" by Jan Graba, 3rd ed.2014, Springer London Ltd, ISBN-13: 9781447152538\n" +
                "Must Buy\n" +
                "\"Introduction to Java Programming and Data Structures\" by Y. Daniel Liang; Pearson; 11th Edition (2018); ISBN-10: 9781292221878; ISBN-13: 978-1292221878\n" +
                "\"Head First JavaScript\" by Eric T. Freeman and E. Robson; O'Reilly Media; 1st edition (2014); ISBN-10: 9781449340131; ISBN-13: 978-1449340131\n" +
                "\"Head First Servlets and JSP\" by B. Basham and K. Sierra; O'Reilly Media; 2nd Edition (2008); ISBN-10: 9780596516680; ISBN-13: 978-0596516680\n" +
                "\"Java Network Programming: Developing Networked Applications\" by Elliotte Rusty Harold; O'Reilly; 4th Edition (2013);  ISBN-10 : 1449357679; ISBN-13 : 978-1449357672\n" +
                "Highly Recommended\n");
        module.setSyllabus("The main topics covered include:\n" +
                "\n" +
                "Processes and threads; including inter-process, thread synchronisation and communication.\n" +
                "Client side networking: some example TCP/IP clients.\n" +
                "Secure Socket layer.\n" +
                "HTTP (including HTTP5), HTML, ECMAScript (JavaScript).\n" +
                "Client side scripting.\n" +
                "Server side networking.\n" +
                "Server side programming: Servlets, JavaBeans, Java Server Pages.\n" +
                "Client-server internet applications, including multithreaded clients and servers using sockets.\n" +
                "Brief introduction to MVC.\n");
        new ModuleInfoPage(module);
    }
}
