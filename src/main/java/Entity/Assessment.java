package main.java.Entity;

/**
 * Assessment entity
 *
 * @author : Yunxin Wang
 * @version : v4.0
 */
public class Assessment {
    private String name;
    private String code;
    private String moduleName;
    private String type;
    private String date;

    /**
     * @return {@link String}
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date assessment date
     */
    public void setDate(String date) {
        this.date = date;
    }

    private int duration;   // (minutes)

    /**
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name assessment name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link String}
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName assessment module name
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return {@link String}
     */
    public String getType() {
        return type;
    }

    /**
     * @return {@link String}
     */
    public String getCode() {
        return code;
    }

    /**
     * @return int
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param type assessment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param duration assessment duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @param code assessment module code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
