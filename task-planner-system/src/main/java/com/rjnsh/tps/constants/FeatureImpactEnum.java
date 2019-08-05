package com.rjnsh.tps.constants;

public enum FeatureImpactEnum {

    LOW("LOW"),
    MODERATE("MODERATE"),
    HIGH("HIGH");

    private String impact;

    FeatureImpactEnum(String impact) {
        this.impact = impact;
    }

    public String getImpact() {
        return impact;
    }
}
