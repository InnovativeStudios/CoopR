package com.coopr.hq.core.models;

public enum AceRole {
    ACE_MEDIC("ace3_role_medic"),
    ACE_ENGINEER("ace3_role_engineer");

    private final String roleText;

    AceRole(final String roleText) {
        this.roleText = roleText;
    }

    public String toString() {
        return roleText;
    }
}
