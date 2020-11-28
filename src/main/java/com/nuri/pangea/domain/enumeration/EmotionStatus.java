package com.nuri.pangea.domain.enumeration;

/**
 * The EmotionStatus enumeration.
 */
public enum EmotionStatus {
    RESPECT("1"),
    DISS("-1"),
    NONE("0");

    private final String value;


    EmotionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
