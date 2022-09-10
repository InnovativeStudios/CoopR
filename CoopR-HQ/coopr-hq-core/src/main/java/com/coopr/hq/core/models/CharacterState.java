package com.coopr.hq.core.models;

public enum CharacterState {
    OK("coopr_state_ok"),
    HOSTAGE("coopr_state_hostage"),
    WOUNDED_IN_ACTION("coopr_state_wia"),
    KILLED_IN_ACTION("coopr_state_kia");

    private final String state;

    CharacterState(final String state) {
        this.state = state;
    }

    public String toString() {
        return state;
    }
}
