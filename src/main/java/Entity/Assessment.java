package main.java.Entity;

import main.java.Data.ModuleData;

public class Assessment {
    private String name;
    private String code;
    private String moduleName;
    private String type;
    private int duration;   // (minutes)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getType() {
        return type;
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
