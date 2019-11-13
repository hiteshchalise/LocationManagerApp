package com.utopia.locationmanagerapp;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

class MyLocationListener implements LocationListener {
    private static final String TAG = "LocationManagerApp";

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: latitude: " + location.getLatitude() + " longitude: " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
