package com.rjnsh.tps.constants;

public enum TaskStatusEnum {
    OPEN("OPEN"),
    IN_PROGRESS("IN_PROGRESS"),
    TESTING("TESTING"),
    DEPLOYED("DEPLOYED"),
    FIXED("FIXED"),
    COMPLETED("COMPLETED");

    private String status;

    TaskStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
