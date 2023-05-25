package main.java.Pages;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimePanel extends JPanel {
    private final JLabel dateTimeLabel;

    public DateTimePanel() {
        // set the layout of the panel
        setLayout(new BorderLayout());

        // create a label to display the time and date
        dateTimeLabel = new JLabel();
        dateTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        dateTimeLabel.setFont(new Font("Segoe Script", Font.BOLD, 18));
        dateTimeLabel.setForeground(Color.WHITE);

        // add the label to the panel
        add(dateTimeLabel, BorderLayout.CENTER);

        // update the label with the current time and date
        updateTimeAndDate();

        // start a timer to update the time and date every second
        Timer timer = new Timer(1000, e -> updateTimeAndDate());
        timer.start();
    }

    private void updateTimeAndDate() {
        // create a date object with the current time
        Date now = new Date();

        // create a date format to display the time and date
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm:ss a", Locale.ENGLISH);

        // update the label with the formatted date and time
        dateTimeLabel.setText(dateFormat.format(now));
    }
}
