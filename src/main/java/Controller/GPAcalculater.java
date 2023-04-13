package main.java.Controller;

import main.java.Data.ModuleData;
import main.java.Entity.Module;

import java.text.DecimalFormat;
import java.util.ArrayList;
/* 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
*/

public class GPAcalculater {
    double totalHours = 0.0;
    double totalQualityPoints = 0;

    ArrayList<Module> modules = ModuleData.getInstance().modules;

        public void averageGPA(){
        int totalCredits = 0;
        double averageGPA = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat dw = new DecimalFormat("#.0");
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            totalCredits = totalCredits + module.getCredits();
            averageGPA += (Integer.valueOf(module.getGrades())/10-5)*Integer.valueOf(module.getCredits());
        }
        //计算平均绩点
        double agpa = averageGPA / totalCredits;
        System.out.println("Average GPA: "+df.format(agpa));
        System.out.println("Total credits: "+dw.format(totalCredits));
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
            System.out.println("GPA: "+df.format(gpa));
    }

    public void perGPA() {
        int maxSemester=0;
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
            System.out.println("The GPA for semester " + semester + " is: " + df.format(gpa));
        }
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
        System.out.println("The Postgraduate GPA is: "+df.format(gpa));
        System.out.println("The average Postgraduate GPA is: "+df.format(agpa));
    }

    public static void main(String[] args) {
        GPAcalculater p = new GPAcalculater();
        p.averageGPA();
        p.totalGPA();
        p.perGPA();
        p.postGPA();
    }
}