package com.codie.simulation.data.location.school;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class BucklandShelburneRegionalElementary extends LocationData {
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

    public BucklandShelburneRegionalElementary() {
        this.latitude = "42.605278";
        this.longitude = "-72.738333";
        this.locationType = "School";
        this.locationName = "Buckland Shelburne Regional Elementary";
        this.description = "Public elementary school pre-k through 6th grade";
        this.phoneNumber = "413-625-2521";
        this.locationBuildingNumber = "75";
        this.locationStreet = "Mechanic Street";
        this.locationTown = "Shelburne Falls";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01370";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {"/school/elementary/s1.jpg", "/school/elementary/s2.jpg",
                "/school/elementary/s3.jpg", "/school/elementary/s4.jpg", "/school/elementary/s5.jpg",
                "/school/elementary/s6.jpg", "/school/objects/o1.jpg", "/school/objects/o2.png",
                "/school/objects/o3.jpeg", "/school/objects/o4.png", "/school/objects/o5.jpg"};
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
