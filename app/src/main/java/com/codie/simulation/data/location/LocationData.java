package com.codie.simulation.data.location;


public abstract class LocationData {
    protected String latitude;
    protected String longitude;
    protected String locationType;
    protected String locationName;
    protected String description;
    protected String phoneNumber;
    protected String locationBuildingNumber;
    protected String locationStreet;
    protected String locationTown;
    protected String locationState;
    protected String locationZipCode;
    protected String locationCountry;
    protected long ratingValue;
    protected String[] locationImages;

    public abstract String getLatitude();

    public abstract String getLongitude();

    public abstract String getLocationType();

    public abstract String getLocationName();

    public abstract String getDescription();

    public abstract String getPhoneNumber();

    public abstract String getLocationBuildingNumber();

    public abstract String getLocationStreet();

    public abstract String getLocationTown();

    public abstract String getLocationState();

    public abstract String getLocationZipCode();

    public abstract String getLocationCountry();

    public abstract long getRatingValue();

    public abstract String getRandomLocationImage();
}
