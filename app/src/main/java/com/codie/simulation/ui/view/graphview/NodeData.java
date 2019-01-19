package com.codie.simulation.ui.view.graphview;


public class NodeData {
    private long id;
    private String objectName;
    private float confidenceValue;

    public NodeData() {
    }

    public NodeData(long id, String objectName, float confidenceValue) {
        this.id = id;
        this.objectName = objectName;
        this.confidenceValue = confidenceValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public float getConfidenceValue() {
        return confidenceValue;
    }

    public void setConfidenceValue(float confidenceValue) {
        this.confidenceValue = confidenceValue;
    }
}
