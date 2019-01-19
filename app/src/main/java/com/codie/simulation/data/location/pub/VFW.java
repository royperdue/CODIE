package com.codie.simulation.data.location.pub;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class VFW extends LocationData {
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

    public VFW() {
        this.latitude = "42.60451";
        this.longitude = "-72.739367";
        this.locationType = "Pub";
        this.locationName = "Veterans of Foreign Wars";
        this.description = "restaurant and bar";
        this.phoneNumber = "413-625-9277";
        this.locationBuildingNumber = "19";
        this.locationStreet = "Water Street";
        this.locationTown = "Shelburne Falls";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01370";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {"/pub/objects/p1.jpg", "/pub/objects/p2.jpg", "/pub/objects/p3.jpeg",
                "/pub/objects/p4.jpg"};
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
