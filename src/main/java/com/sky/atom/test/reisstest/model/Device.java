package com.sky.atom.test.reisstest.model;

public class Device {
    private String type;
    private String platform;


    public void setType(String type) {
        this.type = type;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getType() {
        return type;
    }

    public String getPlatform() {
        return platform;
    }

    public String toString() {
        return type + ":" + platform;
    }
}
