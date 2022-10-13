package com.project.drones.model.audit;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuditEvent {

    registerDrone("New Drone registered "),
    registerMedication("New Medication registered "),
    droneLoaded("Registered drone has been loaded successfully "),
    droneBattery("Checked drone battery is {$battery}");

    private final String title;


    AuditEvent(String title) {
        this.title = title;

    }


    public String getTitle() {
        return title;
    }
}
