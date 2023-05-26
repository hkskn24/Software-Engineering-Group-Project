package main.java.Entity;

import java.util.Objects;

/**
 * Assessment entity
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class Module {
    private String name;
    private String code;
    private int credits;
    private int hours;
    private int semester;
    private String type;
    private String lecturer;
    private String summary;
    private String aims;
    private String syllabus;
    private String readingList;
    private String status;
    private int id;
    private int grades;

    /**
     * @return {@link String[]}
     */
    public static String[] getGradesAttributes() {
        return new String[]{"name", "code", "credits", "hours", "semester", "type", "grades"};
    }

    /**
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name module name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link String}
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code module code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return int
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits module credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * @return int
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param hours module hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * @return int
     */
    public int getSemester() {
        return semester;
    }

    /**
     * @param semester module semester
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * @return {@link String}
     */
    public String getType() {
        return type;
    }

    /**
     * @param type module type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return {@link String}
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * @param lecturer module lecturers
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * @return {@link String}
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary module summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return {@link String}
     */
    public String getAims() {
        return aims;
    }

    /**
     * @param aims module aims
     */
    public void setAims(String aims) {
        this.aims = aims;
    }

    /**
     * @return {@link String}
     */
    public String getSyllabus() {
        return syllabus;
    }

    /**
     * @param syllabus module syllabus
     */
    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    /**
     * @return {@link String}
     */
    public String getReadingList() {
        return readingList;
    }

    /**
     * @param readingList module reading list
     */
    public void setReadingList(String readingList) {
        this.readingList = readingList;
    }

    /**
     * @return {@link String}
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status module status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param grades grades
     */
    public void setGrades(int grades) {
        this.grades = grades;
    }

    /**
     * @return int
     */
    public int getGrades() {
        return grades;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, name, semester, type);
    }

    /**
     * @param obj object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Module module = (Module) obj;
        return Objects.equals(code, module.code) && Objects.equals(name, module.name) && Objects.equals(semester, module.semester) && Objects.equals(type, module.type);
    }
}

