package main.java.Pages.LecturerPages;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class CompletedModulePageTest {

    @Test
    public void testButtonAvailability() {
        CompletedModulePage completedModulePage = new CompletedModulePage();

        JButton assignGradesButton = getAssignGradesButton(completedModulePage);
        JButton backButton = getBackButton(completedModulePage);

        assert assignGradesButton.isEnabled();
        assert backButton.isEnabled();
    }

    private JButton getAssignGradesButton(CompletedModulePage completedModulePage) {
        JPanel buttonPanel = completedModulePage.setupButtonPanel();
        return (JButton) buttonPanel.getComponent(0);
    }

    private JButton getBackButton(CompletedModulePage completedModulePage) {
        JPanel buttonPanel = completedModulePage.setupButtonPanel();
        return (JButton) buttonPanel.getComponent(1);
    }
}
