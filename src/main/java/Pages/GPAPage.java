package main.java.Pages;

import main.java.Data;
import main.java.Entity.Module;

import java.text.DecimalFormat;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;

public class GPAPage extends JFrame {
    double totalHours = 0.0;
    double totalQualityPoints = 0;
    ArrayList<Module> modules = Data.getInstance().modules;
    JTextArea textArea = new JTextArea();
    JPanel panel = new JPanel();

    public GPAPage(){
        super("GPA Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setResizable(false);

        panel.setLayout(new BorderLayout());
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 18));
        panel.add(new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
        public void averageGPA(){
        int totalCredits = 0;
        double averageGPA = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat dw = new DecimalFormat("#.0");
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            totalCredits = totalCredits + module.getCredits();
            averageGPA += (Integer.valueOf(module.getGrades())/10.0-5)*Integer.valueOf(module.getCredits());
        }
        //计算平均绩点
        double agpa = averageGPA / totalCredits;
        textArea.append("Average GPA: "+df.format(agpa) + "\n");
        textArea.append("Total credits: "+dw.format(totalCredits) + "\n");
    }
    public void totalGPA() {
            int totalCredits = 0;
            DecimalFormat df = new DecimalFormat("#.00");
            for (int i = 0; i < modules.size(); i++) {
                Module module = modules.get(i);
                totalCredits += Integer.valueOf(module.getCredits());
                totalHours += Integer.valueOf(module.getHours());
                totalQualityPoints += Integer.valueOf(module.getGrades())*Integer.valueOf(module.getCredits()); 
            }
            //计算总GPA
            double gpa = totalQualityPoints / totalCredits;
            textArea.append("GPA: "+df.format(gpa)+"\n");
    }

    public void perGPA() {
        int maxSemester=0;
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        DecimalFormat df = new DecimalFormat("#.00");
        for (Module module : modules) {
            int semester = module.getSemester();
            if (semester > maxSemester) {
                maxSemester = semester;
            }
        }

        for (int semester = maxSemester; semester > 0; semester--) {
            int totalCredits = 0;
            double totalGradePoints = 0;

            for (Module module : modules) {
                if (module.getSemester() == semester) {
                    int credits = Integer.parseInt(String.valueOf(module.getCredits()));
                    int grade = Integer.parseInt(String.valueOf(module.getGrades()));
                    totalCredits += credits;
                    totalGradePoints += (grade / 10.0 - 5) * credits;
                }
            }

            //计算每学期GPA
            double gpa = totalGradePoints / totalCredits;
            listModel.addElement( "Semester "+ semester + " : " + df.format(gpa));
        }
        JList<String> list = new JList<String>(listModel);
        list.setFont(new Font("Courier New", Font.PLAIN, 18));
        JScrollPane listPane = new JScrollPane(list);
        listPane.setPreferredSize(new Dimension(100, 200));
        listPane.setBorder(BorderFactory.createTitledBorder("Per Semester GPA"));
        panel.add(listPane, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }

    public void postGPA(){
        int totalCredits = 0;
        int totalHours = 0;
        int totalQualityPoints = 0;
        double totalGradePoints = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            // Exclude courses with type "elective" or "minor"
            if (!module.getType().equalsIgnoreCase("elective") && !module.getType().equalsIgnoreCase("minor")) {
                totalCredits += Integer.valueOf(module.getCredits());
                totalHours += Integer.valueOf(module.getHours());
                totalQualityPoints += Integer.valueOf(module.getGrades()) * Integer.valueOf(module.getCredits());
                totalGradePoints += (Integer.valueOf(module.getGrades()) / 10.0 - 5) * Integer.valueOf(module.getCredits());
            }
        }
        //计算总GPA
        double gpa = totalQualityPoints / totalCredits;
        double agpa = totalGradePoints / totalCredits;
        textArea.append("The Postgraduate GPA is: "+df.format(gpa)+"\n");
        textArea.append("The average Postgraduate GPA is: "+df.format(agpa));
    }

    public static void main(String[] args) {
        GPAPage p = new GPAPage();
        p.averageGPA();
        p.totalGPA();
        p.postGPA();
        p.perGPA();
    }
}