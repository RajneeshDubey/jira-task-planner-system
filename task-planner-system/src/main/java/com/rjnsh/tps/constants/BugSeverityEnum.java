package com.rjnsh.tps.constants;

public enum BugSeverityEnum {

    P0("P0"),
    P1("P1"),
    P2("P2");

    private String severity;

    BugSeverityEnum(String impact) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }
}
