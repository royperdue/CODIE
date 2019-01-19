package com.codie.simulation.util;


public class Wrapper {
    private String name;
    private float confidenceValue;
    private String[] coordinates;

    public Wrapper(String name, float confidenceValue) {
        this.name = name;
        this.confidenceValue = confidenceValue;
    }

    public Wrapper(String name, float confidenceValue, String[] coordinates) {
        this.name = name;
        this.confidenceValue = confidenceValue;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getConfidenceValue() {
        return confidenceValue;
    }

    public void setConfidenceValue(float confidenceValue) {
        this.confidenceValue = confidenceValue;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }
}
