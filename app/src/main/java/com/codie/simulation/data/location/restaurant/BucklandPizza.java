package com.codie.simulation.data.location.restaurant;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class BucklandPizza extends LocationData {
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

    public BucklandPizza() {
        this.latitude = "42.603734";
        this.longitude = "-72.741376";
        this.locationType = "Restaurant";
        this.locationName = "Buckland Pizza";
        this.description = "Buckland Pizza in Buckland, Massachusetts, is a pizza restaurant and family institution that creates some of the best Italian dishes you will ever get to try.";
        this.phoneNumber = "413-625-6342";
        this.locationBuildingNumber = "13";
        this.locationStreet = "State Street";
        this.locationTown = "Shelburne Falls";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01370";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {
                "/restaurant/buckland_pizza/bp1.jpg",
                "/restaurant/buckland_pizza/bp2.jpg",
                "/restaurant/buckland_pizza/bp3.jpg",
                "/restaurant/buckland_pizza/bp4.jpg",
                "/restaurant/buckland_pizza/bp5.jpg",
                "/restaurant/buckland_pizza/bp6.jpg",
                "/restaurant/buckland_pizza/bp7.jpg"};
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
