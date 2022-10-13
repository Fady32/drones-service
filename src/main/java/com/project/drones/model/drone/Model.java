package com.project.drones.model.drone;

public enum Model {
    Lightweight("Light Weight"),
    Middleweight("Middle Weight"),
    Cruiserweight("Cruiser Weight"),
    Heavyweight("Heavy Weight");


    private String name;


    Model(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return this.name();
    }
}
