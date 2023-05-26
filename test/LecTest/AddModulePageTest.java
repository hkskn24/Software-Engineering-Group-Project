package main.java.Pages.LecturerPages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class AddModulePageTest {

    @Test
    public void testTextFieldsAreDisplayedAndEnabled() {
        AddModulePage addModulePage = new AddModulePage();

        JTextField nameField = addModulePage.getNameField();
        JTextField codeField = addModulePage.getCodeField();
        JTextField creditsField = addModulePage.getCreditsField();
        JTextField hoursField = addModulePage.getHoursField();
        JTextField semesterField = addModulePage.getSemesterField();
        JTextField typeField = addModulePage.getTypeField();
        JTextField lecturerField = addModulePage.getLecturerField();

        Assertions.assertNotNull(nameField);
        Assertions.assertTrue(nameField.isDisplayable());
        Assertions.assertTrue(nameField.isEnabled());

        Assertions.assertNotNull(codeField);
        Assertions.assertTrue(codeField.isDisplayable());
        Assertions.assertTrue(codeField.isEnabled());

        Assertions.assertNotNull(creditsField);
        Assertions.assertTrue(creditsField.isDisplayable());
        Assertions.assertTrue(creditsField.isEnabled());

        Assertions.assertNotNull(hoursField);
        Assertions.assertTrue(hoursField.isDisplayable());
        Assertions.assertTrue(hoursField.isEnabled());

        Assertions.assertNotNull(semesterField);
        Assertions.assertTrue(semesterField.isDisplayable());
        Assertions.assertTrue(semesterField.isEnabled());

        Assertions.assertNotNull(typeField);
        Assertions.assertTrue(typeField.isDisplayable());
        Assertions.assertTrue(typeField.isEnabled());

        Assertions.assertNotNull(lecturerField);
        Assertions.assertTrue(lecturerField.isDisplayable());
        Assertions.assertTrue(lecturerField.isEnabled());
    }

    @Test
    public void testTextAreasAreDisplayedAndEnabled() {
        AddModulePage addModulePage = new AddModulePage();

        JTextArea summaryField = addModulePage.getSummaryField();
        JTextArea aimsField = addModulePage.getAimsField();
        JTextArea syllabusField = addModulePage.getSyllabusField();
        JTextArea readingListField = addModulePage.getReadingListField();

        Assertions.assertNotNull(summaryField);
        Assertions.assertTrue(summaryField.isDisplayable());
        Assertions.assertTrue(summaryField.isEnabled());

        Assertions.assertNotNull(aimsField);
        Assertions.assertTrue(aimsField.isDisplayable());
        Assertions.assertTrue(aimsField.isEnabled());

        Assertions.assertNotNull(syllabusField);
        Assertions.assertTrue(syllabusField.isDisplayable());
        Assertions.assertTrue(syllabusField.isEnabled());

        Assertions.assertNotNull(readingListField);
        Assertions.assertTrue(readingListField.isDisplayable());
        Assertions.assertTrue(readingListField.isEnabled());
    }
}