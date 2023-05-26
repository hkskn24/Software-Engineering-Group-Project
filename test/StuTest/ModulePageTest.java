package main.java.Pages.StudentPages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class ModulePageTest {
    @Test
    public void testButtonsAreEnabled() {
        SwingUtilities.invokeLater(() -> {
            ModulePage modulePage = new ModulePage();

            JButton searchButton = getButtonByName(modulePage, "Search");
            JButton clearButton = getButtonByName(modulePage, "Clear");
            JButton backButton = getButtonByName(modulePage, "Back");

            JTextField searchTextField = getTextField(modulePage, 0);

            Assertions.assertTrue(searchButton.isEnabled());
            Assertions.assertTrue(clearButton.isEnabled());
            Assertions.assertTrue(backButton.isEnabled());
            Assertions.assertTrue(searchTextField.isEnabled());
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private JButton getButtonByName(ModulePage modulePage, String buttonText) {

        JPanel searchPanel = (JPanel) modulePage.getContentPane().getComponent(0);

        for (Component component : searchPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            }
        }

        return null;
    }

    private JTextField getTextField(ModulePage modulePage, int index) {

        JPanel searchPanel = (JPanel) modulePage.getContentPane().getComponent(0);

        Component component = searchPanel.getComponent(index);
        if (component instanceof JTextField) {
            return (JTextField) component;
        }

        return null;
    }
}
