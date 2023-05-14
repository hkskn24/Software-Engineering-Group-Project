package main.java.Data;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import main.java.Entity.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentData extends Data{
    private static StudentData instance = null;

    private StudentData() {

    }

    public static StudentData getInstance() {
        if (instance == null) {
            instance = new StudentData();
        }

        return instance;
    }

    public List<Student> getStudentList(String code) {
        String path = "src/main/resources/data/modules/" + code + "/grades.json";
        String jsonStr = readFileToString(path);

        try {
            List<Student> students = new Gson().fromJson(jsonStr, new TypeToken<List<Student>>(){}.getType());
            if (students == null) {
                return new ArrayList<>();
            }
            return students;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Map<String, String> findStudent(String ID) {
        Path path = Paths.get("students.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(" ");
                if (parts[2].equals(ID)) {
                    Map<String, String> student = new HashMap<>();
                    student.put("name", parts[0]);
                    student.put("ID", parts[2]);
                    return student;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addStudentToJson(String code, Map<String, String> student) {
        List<Student> students = getStudentList(code);
        Student newStudent = new Student();
        newStudent.setID(student.get("ID"));
        newStudent.setName(student.get("name"));
        newStudent.setGrades(-1);
        students.add(newStudent);
        String path = "src/main/resources/data/modules/" + code + "/grades.json";
        saveJsonToFile(path, students);
    }

    public void addModuleToStudent(String studentName, String code) {
        String path = "src/main/resources/data/students/" + studentName + "/modules.json";
        List<String> modules = readJsonToList(path, new TypeToken<List<String>>(){}.getType());
        modules.add(code);
        saveJsonToFile(path, modules);
    }

    public void deleteStudent(String code, String ID) {
        List<Student> students = getStudentList(code);
        students = students.stream().filter(student -> !student.getID().equals(ID)).collect(Collectors.toList());
        String path = "src/main/resources/data/modules/" + code + "/grades.json";
        saveJsonToFile(path, students);
    }
}
