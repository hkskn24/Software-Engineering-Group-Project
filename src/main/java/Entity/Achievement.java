package main.java.Entity;

/**
 * Achievement entity
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class Achievement {
    private String name;
    private int semester;
    private String type;

    /**
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name achievement name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int
     */
    public int getSemester() {
        return semester;
    }

    /**
     * @param semester achievement semester
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
     * @param type achievement type
     */
    public void setType(String type) {
        this.type = type;
    }
}
