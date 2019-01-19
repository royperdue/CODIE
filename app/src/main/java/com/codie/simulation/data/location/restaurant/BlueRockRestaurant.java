package com.codie.simulation.data.location.restaurant;


import com.codie.simulation.data.location.LocationData;

import java.util.Random;


public class BlueRockRestaurant extends LocationData {
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

    public BlueRockRestaurant() {
        this.latitude = "42.605278";
        this.longitude = "-72.738333";
        this.locationType = "Restaurant";
        this.locationName = "The Blue Rock Restaurant";
        this.description = "restaurant and bar";
        this.phoneNumber = "413-625-8133";
        this.locationBuildingNumber = "1";
        this.locationStreet = "Ashfield Street";
        this.locationTown = "Shelburne Falls";
        this.locationState = "Massachusetts";
        this.locationZipCode = "01370";
        this.locationCountry = "U.S.A.";
        this.ratingValue = (long) 1.0;

        locationImages = new String[] {
                "/restaurant/the_blue_rock_restaurant/the_blue_rock_people/a1.jpg",
                "/restaurant/the_blue_rock_restaurant/the_blue_rock_people/a2.jpg",
                "/restaurant/the_blue_rock_restaurant/the_blue_rock_people/a3.jpg",
                "/restaurant/the_blue_rock_restaurant/the_blue_rock_people/c1.jpg",
                "/restaurant/the_blue_rock_restaurant/the_blue_rock_people/c2.jpeg",
                "/restaurant/the_blue_rock_restaurant/the_blue_rock_people/c3.jpg",
                "/restaurant/the_blue_rock_restaurant/chris_ramirez1.jpg",
                "/restaurant/the_blue_rock_restaurant/chris_ramirez2.jpg",
                "/restaurant/the_blue_rock_restaurant/chris_ramirez3.jpg",
                "/restaurant/the_blue_rock_restaurant/chris_ramirez4.jpg"};
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
