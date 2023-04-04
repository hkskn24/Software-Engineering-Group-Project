package main.java.Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModulePage {

    private JFrame frame;
    private JList<String> courseList;
    private List<Course> courses;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ModulePage window = new ModulePage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ModulePage() {
        initializeCourses();

        frame = new JFrame("Course Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        courseList = new JList<>(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return courses.size();
            }

            @Override
            public String getElementAt(int index) {
                return courses.get(index).getName();
            }
        });

        courseList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = courseList.getSelectedIndex();
                    showCourseDetails(selectedIndex);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(courseList);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeCourses() {
        courses = new ArrayList<>(Arrays.asList(
                new Course("EBU5042", "Advanced Network Programming", "Introduction", 3.5),
                new Course("EBU6335", "Digital Systems Design", "Introduction", 2.5),
                new Course("EBU5302", "Telecommunications Systems", "Introduction", 2.5)
        ));
    }

    private void showCourseDetails(int index) {
        Course course = courses.get(index);
        String courseInfo = "Course Introduction: " + course.getIntroduction() + "\n";
        String codeInfo = "Code: " + course.getCode() + "\n";
        String creditsInfo = "Credits: " + course.getCredits() + "\n";
        JOptionPane.showMessageDialog(frame, codeInfo + creditsInfo + courseInfo, course.getName(), JOptionPane.INFORMATION_MESSAGE);
    }

    static class Course {
        private String code;
        private String name;
        private double credits;
        private String introduction;

        public Course(String code, String name, String introduction, double credits) {
            this.code = code;
            this.name = name;
            this.introduction = introduction;
            this.credits = credits;
        }

        public String getName() {return name;}
        public String getIntroduction() {return introduction;}
        public String getCode() {return code;}
        public double getCredits() {return credits;}
    }
}


