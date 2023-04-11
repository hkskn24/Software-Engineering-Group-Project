package main.java.Entity;

public class Lec_Module {
    private String name;
    private String code;
    private int credits;
    private int hours;
    private int semester;
    private String type;
    private int grades;
    private String lecturer;
    private String summary;
    private String aims;
    private String syllabus;
    private String readingList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGrades() {
        return grades;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAims() {
        return aims;
    }

    public void setAims(String aims) {
        this.aims = aims;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getReadingList() {
        return readingList;
    }

    public void setReadingList(String readingList) {
        this.readingList = readingList;
    }

    public static String[] getAllAttributes() {
        return new String[]{"name", "code", "credits", "hours", "semester", "type", "students"};
    }

    @Override
    public String toString() {
        return getName();
    }
}

