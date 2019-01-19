package com.codie.simulation.data.location.school;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class GreenfieldCommunityCollege extends LocationData {
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

    public GreenfieldCommunityCollege() {
        this.latitude = "42.596983";
        this.longitude = "-72.627758";
        this.locationType = "School";
        this.locationName = "Greenfield Community College";
        this.description = "Community college";
        this.phoneNumber = "413-775-1000";
        this.locationBuildingNumber = "1";
        this.locationStreet = "One College Drive";
        this.locationTown = "Greenfield";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01301";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {"/school/college/c1.jpg", "/school/college/c2.jpeg",
                "/school/college/c3.jpeg", "/school/college/c4.jpeg", "/school/college/c5.jpg",
                "/school/college/c6.jpeg", "/school/college/c7.jpg", "/school/college/c8.jpg",
                "/school/objects/o1.jpg", "/school/objects/o2.png", "/school/objects/o3.jpeg",
                "/school/objects/o4.png", "/school/objects/o5.jpg"};
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
