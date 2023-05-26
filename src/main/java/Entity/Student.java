package main.java.Entity;

/**
 * Student entity
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class Student {
    private String ID;
    private String name;
    private int grades;

    /**
     * @return {@link String}
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID student id
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name student name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int
     */
    public int getGrades() {
        return grades;
    }

    /**
     * @param grades student grades
     */
    public void setGrades(int grades) {
        this.grades = grades;
    }
}
