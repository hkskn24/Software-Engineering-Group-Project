package main.java.Pages;

import main.java.Controller.ModuleController;
import main.java.Entity.Module;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JoinPage extends JFrame {
    private JList<Module> moduleList;
    private JTextField searchTextField;

    public JoinPage() {
        setTitle("Join A Module");
        setSize(1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel searchPanel = setupSearchPanel();
        contentPanel.add(searchPanel, BorderLayout.NORTH);

        moduleList = new JList<>();
        contentPanel.add(new JScrollPane(moduleList), BorderLayout.CENTER);

        setContentPane(contentPanel);
        setVisible(true);
    }

    private void updateSearchResult(List<Module> modules) {
        DefaultListModel<Module> moduleListModel = new DefaultListModel<>();
        for (Module module : modules) {
            moduleListModel.addElement(module);
        }

        if (moduleList == null) {
            moduleList = new JList<>(moduleListModel);
        } else {
            moduleList.setModel(moduleListModel);
        }
    }

    private JPanel setupSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchTextField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchModules());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            searchTextField.setText("");
            searchModules();
        });

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        return searchPanel;
    }

    private void searchModules() {
        String term = searchTextField.getText().trim();
        if (!term.isEmpty()) {
            List<Module> result = ModuleController.searchAllModules(term);
            updateSearchResult(result);
        } else {
            updateSearchResult(List.of());
        }
    }

    public static void main(String[] args) {
        new JoinPage();
    }
}


