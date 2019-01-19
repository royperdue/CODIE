package com.codie.simulation.data.location.store;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class BucklandNeighbors extends LocationData {
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

    public BucklandNeighbors() {
        this.latitude = "42.613743";
        this.longitude = "-72.739083";
        this.locationType = "Store";
        this.locationName = "Buckland Neighbors";
        this.description = "Convenience store and gas station";
        this.phoneNumber = "413-625-9577";
        this.locationBuildingNumber = "195";
        this.locationStreet = "State Street";
        this.locationTown = "Shelburne Falls";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01370";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {"/store/convenience_store/c1.jpg", "/store/convenience_store/c2.jpg",
                "/store/convenience_store/c3.jpg", "/store/convenience_store/c4.jpeg"};
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
