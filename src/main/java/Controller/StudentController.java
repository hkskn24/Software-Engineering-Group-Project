package main.java.Controller;

import main.java.Data.StudentData;
import main.java.Entity.Student;

import java.util.List;
import java.util.Map;

public class StudentController {

    public StudentController() {
    }

    public List<Student> getStudentList(String code) {
        return StudentData.getInstance().getStudentList(code);
    }

    public int addStudent(String code, String ID) {
        Map<String, String> student = StudentData.getInstance().findStudent(ID);
        if (student == null) {
            return 0;   // student not found
        }

        List<Student> students = StudentData.getInstance().getStudentList(code);
        for (Student s : students) {
            if (s.getID().equals(ID)) {
                return 1;   // student already added
            }
        }

        StudentData.getInstance().addStudentToJson(code, student);
        StudentData.getInstance().addModuleToStudent(student.get("name"), code);
        return 2; // added successfully
    }

    public boolean deleteStudent(String code, String ID) {
        List<Student> students = StudentData.getInstance().getStudentList(code);
        boolean isFound = false;
        String studentName = null;

        for (Student s : students) {
            if (s.getID().equals(ID)) {
                studentName = s.getName();
                isFound = true;
                break;
            }
        }

        if (isFound) {
            StudentData.getInstance().deleteStudent(code, ID);
            StudentData.getInstance().removeModuleFromStudent(studentName, code);
            return true;
        } else {
            return false;
        }
    }

    public int getGradesByCode(String name, String code) {
        List<Student> students = getStudentList(code);
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student.getGrades();
            }
        }
        return -1;
    }

    public boolean updateGrades(String code, String ID, int grades) {
        return StudentData.getInstance().updateGrades(code, ID, grades);
    }
}
