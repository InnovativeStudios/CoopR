package com.coopr.hq.client;

public enum Version {
    LATEST("v0.1"),
    V0_1("v0.1"),
    V0_2("v0.2");

    private final String version;

    Version(final String version) {
        this.version = version;
    }

    public String toString() {
        return version;
    }
}
