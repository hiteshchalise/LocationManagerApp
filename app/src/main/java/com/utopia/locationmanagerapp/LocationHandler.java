package com.utopia.locationmanagerapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationHandler implements LocationListener {
    private static final String TAG = "LocationManagerApp";
    private Context ctx;
    private List<Coords> checkPointCoords;
    private HashMap<String, Float> distanceHashMap = new HashMap<>();
    private Set<String> setOfVisitedCheckPoints;
    private View viewImpl;

    LocationHandler(final View viewImpl, Context ctx, final List<Coords> checkPointCoords) {

        this.viewImpl = viewImpl;
        this.ctx = ctx;
        this.checkPointCoords = checkPointCoords;
        this.setOfVisitedCheckPoints = new HashSet<>();
    }

    @Override
    public void onLocationChanged(Location location) {
        processNewLocation(location);
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

    private void processNewLocation(Location newLocation) {
        double startLatitude = newLocation.getLatitude();
        double startLongitude = newLocation.getLongitude();

        this.viewImpl.handleCurrentPositionChange(newLocation);

        for (Coords checkPointCoord : checkPointCoords) {
            String locationName = checkPointCoord.getName();

            float[] results = new float[1];
            Location.distanceBetween(
                    startLatitude, startLongitude,
                    checkPointCoord.getCoordinates()[0], checkPointCoord.getCoordinates()[1],
                    results);
            float distanceInMeters = results[0];

            distanceHashMap.put(locationName, distanceInMeters);

            if (distanceInMeters < 50) {
                if (!setOfVisitedCheckPoints.contains(locationName)) {
                    MediaPlayer mPlayer = MediaPlayer.create(ctx, getRawRez(locationName));
                    mPlayer.start();
                    setOfVisitedCheckPoints.clear();
                    setOfVisitedCheckPoints.add(locationName);
                }
                viewImpl.handleCheckPointsVisited(setOfVisitedCheckPoints);
            }
        }
        this.viewImpl.handleDistancesChange(distanceHashMap);
    }

    private int getRawRez(String locationName) {
        int raw = ctx.getResources().getIdentifier(locationName.toLowerCase(), "raw", ctx.getPackageName());
        if(raw != 0) return raw;
        return R.raw.ding;
    }

    interface View {
        void handleCurrentPositionChange(Location newLocation);

        void handleDistancesChange(HashMap<String, Float> distances);

        void handleCheckPointsVisited(Set<String> visitedCheckPoints);
    }

}
