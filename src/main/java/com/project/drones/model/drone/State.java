package com.project.drones.model.drone;

public enum State {
    IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING;

    State() {

    }

    public String getCode() {
        return this.name();
    }
}
