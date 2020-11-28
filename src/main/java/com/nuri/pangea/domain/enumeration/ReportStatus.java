package com.nuri.pangea.domain.enumeration;

/**
 * The ReportStatus enumeration.
 */
public enum ReportStatus {
    ORIGINATE,
    TAKEOVER("접수"),
    COMPLETED("완료");

    private String value;

    ReportStatus() {}

    ReportStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
