package com.codie.simulation.data.location.restaurant;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class ShelburneFallsCoffeeRoasters extends LocationData {
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

    public ShelburneFallsCoffeeRoasters() {
        this.latitude = "42.611005";
        this.longitude = "-72.66007";
        this.locationType = "Coffee Shop";
        this.locationName = "Shelburne Falls Coffee Roasters";
        this.description = "Coffee shop store";
        this.phoneNumber = "413-625-0116";
        this.locationBuildingNumber = "1335";
        this.locationStreet = "Mohawk Trail";
        this.locationTown = "Shelburne Falls";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01370";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {
                "/restaurant/shelburne_falls_coffee_roasters/cr1.jpeg",
                "/restaurant/shelburne_falls_coffee_roasters/cr2.jpeg",
                "/restaurant/shelburne_falls_coffee_roasters/cr3.jpeg",
                "/restaurant/shelburne_falls_coffee_roasters/cr4.jpeg",
                "/restaurant/shelburne_falls_coffee_roasters/cr5.jpeg",
                "/restaurant/shelburne_falls_coffee_roasters/cr6.jpeg",
                "/restaurant/shelburne_falls_coffee_roasters/cr7.jpeg"};
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
