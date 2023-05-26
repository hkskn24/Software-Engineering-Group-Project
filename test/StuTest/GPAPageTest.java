package main.java.Pages.StudentPages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GPAPageTest {

    @Test
    public void testNoRecordScenario() {

        GPAPage gpaPage = new GPAPage();

        gpaPage.averageGPA();
        gpaPage.totalGPA();
        gpaPage.postGPA();

        String text = gpaPage.textArea.getText();

        Assertions.assertTrue(text.contains("Total credits: No Record"));
        Assertions.assertTrue(text.contains("Average GPA: No Record"));
        Assertions.assertTrue(text.contains("Average grade: No Record"));
        Assertions.assertTrue(text.contains("The Postgraduate GPA is: No Record"));
        Assertions.assertTrue(text.contains("The average Postgraduate GPA is: No Record"));
    }
}