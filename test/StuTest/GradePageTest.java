package main.java.Pages.StudentPages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class GradePageTest {
    @Test
    public void testBackButton() {
        SwingUtilities.invokeLater(() -> {
            GradePage gradePage = new GradePage();

            JPanel contentPanel = (JPanel) gradePage.getContentPane().getComponent(1);

            JButton backButton = getButtonByName(contentPanel, "Back");

            Assertions.assertTrue(backButton.isEnabled());

        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGPAButton() {
        SwingUtilities.invokeLater(() -> {
            GradePage gradePage = new GradePage();

            JPanel contentPanel = (JPanel) gradePage.getContentPane().getComponent(1);

            JButton GPAButton = getButtonByName(contentPanel, "view GPA");

            Assertions.assertTrue(GPAButton.isEnabled());

        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private JButton getButtonByName(JPanel panel, String buttonText) {

        for (Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            }
        }

        return null;
    }
}