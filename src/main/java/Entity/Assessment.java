package main.java.Entity;

public class Assessment {
    private String name;
    private String code;
    private String type;
    private int duration;   // (minutes)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModuleName(String moduleName) {
    }

    public String getType() {
        return type;
    }
    
    public String getCode() {
        return code;
    }

    public int getDuration() {
        return duration;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
