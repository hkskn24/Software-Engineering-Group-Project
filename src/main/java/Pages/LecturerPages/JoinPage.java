package main.java.Pages.LecturerPages;

import main.java.Controller.ModuleController;
import main.java.Data.ModuleData;
import main.java.Entity.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.List;

/**
 * Page for lecturer to join other module
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class JoinPage extends JFrame {
    /**
     * list for modules after searching
     */
    private JList<Module> moduleList;
    /**
     * search text field
     */
    private JTextField searchTextField;

    /**
     * set up the page
     */
    public JoinPage() {
        setTitle("Join A Module");
        setSize(1094, 729);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main.java.Pages.LecturerPages.HomePage homePage = new main.java.Pages.LecturerPages.HomePage();
                homePage.setVisible(true);
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel searchPanel = setupSearchPanel();
        contentPanel.add(searchPanel, BorderLayout.NORTH);

        moduleList = new JList<>();
        contentPanel.add(new JScrollPane(moduleList), BorderLayout.CENTER);

        JPanel buttonPanel = setupButtonPanel();
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
        setVisible(true);
    }

    /**
     * @param modules search result
     */
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

    /**
     * @return {@link JPanel}
     */
    protected JPanel setupSearchPanel() {
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

    /**
     * @return {@link JPanel}
     */
    protected JPanel setupButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton joinButton = new JButton("Join");
        joinButton.addActionListener(e -> joinSelectedModule());
        buttonPanel.add(joinButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
            new HomePage().setVisible(true);
        });
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    /**
     * join selected module and feedback message
     */
    private void joinSelectedModule() {
        Module selectedModule = moduleList.getSelectedValue();
        if (selectedModule != null) {
            if (ModuleData.getInstance().modules.contains(selectedModule)) {
                JOptionPane.showMessageDialog(this, "You have already joined this module.", "Module Already Joined", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ModuleController.joinModule(selectedModule);
                JOptionPane.showMessageDialog(this, "Successfully joined this module.", "Module Joined", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * search from all modules
     */
    private void searchModules() {
        String term = searchTextField.getText().trim();
        if (!term.isEmpty()) {
            List<Module> result = ModuleController.searchAllModules(term);
            updateSearchResult(result);
        } else {
            updateSearchResult(Collections.emptyList());
        }
    }
}


