package com.codie.simulation.data.location.library;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class ArmsLibrary extends LocationData {
    private String latitude;
    private String longitude;
    private String locationType;
    private String locationName;
    private String description;
    private String phoneNumber;
    private String locationBuildingNumber;
    private String locationStreet;
    private String locationTown;
    private String locationState;
    private String locationZipCode;
    private String locationCountry;
    private long ratingValue;

    private String[] locationImages;

    public ArmsLibrary() {
        this.latitude = "42.603615";
        this.longitude = "-72.738046";
        this.locationType = "Library";
        this.locationName = "Arms Library";
        this.description = "Library";
        this.phoneNumber = "413-625-0306";
        this.locationBuildingNumber = "60";
        this.locationStreet = "Bridge Street";
        this.locationTown = "Shelburne Falls";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01370";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {"/library/objects/l1.jpeg", "/library/objects/l2.jpeg", "/library/objects/l3.jpg",
                "/library/objects/l4.jpg", "/library/objects/l5.jpeg"};
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String getLocationType() {
        return locationType;
    }

    @Override
    public String getLocationName() {
        return locationName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getLocationBuildingNumber() {
        return locationBuildingNumber;
    }

    @Override
    public String getLocationStreet() {
        return locationStreet;
    }

    @Override
    public String getLocationTown() {
        return locationTown;
    }

    @Override
    public String getLocationState() {
        return locationState;
    }

    @Override
    public String getLocationZipCode() {
        return locationZipCode;
    }

    @Override
    public String getLocationCountry() {
        return locationCountry;
    }

    @Override
    public long getRatingValue() {
        return ratingValue;
    }

    @Override
    public String getRandomLocationImage() {
        return locationImages[new Random().nextInt(locationImages.length)];
    }
}
