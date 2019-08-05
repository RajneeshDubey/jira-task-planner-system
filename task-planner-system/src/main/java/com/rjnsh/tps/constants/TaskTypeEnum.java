package com.rjnsh.tps.constants;

public enum TaskTypeEnum {

    FEATURE("FEATURE"),
    BUG("BUG"),
    STORY("STORY");

    private String type;

    TaskTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
