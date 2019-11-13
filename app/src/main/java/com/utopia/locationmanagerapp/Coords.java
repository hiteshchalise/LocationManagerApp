package com.utopia.locationmanagerapp;

import androidx.annotation.NonNull;

public class Coords {
    private final double latitude, longitude;

    public Coords(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @NonNull
    @Override
    public String toString() {
        return "Latitude: " + this.latitude + " Longitude: " + this.longitude;
    }
}
