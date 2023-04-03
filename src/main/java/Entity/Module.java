package main.java.Entity;

public class Module {
    private String name;
    private String code;
    private int credits;
    private int hours;
    private int semester;
    private String type;
    private int grades;

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

    public static String[] getAllAttributes() {
        return new String[]{"name", "code", "credits", "hours", "semester", "type", "grades"};
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", credits=" + credits +
                ", hours=" + hours +
                ", semester=" + semester +
                ", type='" + type + '\'' +
                ", grades=" + grades +
                '}';
    }
}

