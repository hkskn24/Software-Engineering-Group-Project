package main.java.Pages.StudentPages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class AssessmentPageTest {
    @Test
    public void testBackButton() {
        SwingUtilities.invokeLater(() -> {
            AssessmentPage assessmentPage = new AssessmentPage();

            JButton backButton = getButtonByName(assessmentPage, "Back");

            Assertions.assertTrue(backButton.isEnabled());

        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTable() {
        SwingUtilities.invokeLater(() -> {
            AssessmentPage assessmentPage = new AssessmentPage();

            JTable table = assessmentPage.getTable();

            Assertions.assertTrue(table.isEnabled());

            Assertions.assertEquals(0, table.getRowCount());
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private JButton getButtonByName(AssessmentPage assessmentPage, String buttonText) {

        JPanel buttonPanel = (JPanel) assessmentPage.getContentPane().getComponent(1);

        for (Component component : buttonPanel.getComponents()) {
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