package com.coopr.hq.core.models;

public enum CharacterRole {
    LEADER("coopr_role_leader"),
    MEDIC("coopr_role_medic"),
    ENGINEER("coopr_role_engineer"),
    DMR("coopr_role_dmr"),
    MG("coopr_role_mg");

    private final String role;

    CharacterRole(final String role) {
        this.role = role;
    }

    public String toString() {
        return role;
    }
}
