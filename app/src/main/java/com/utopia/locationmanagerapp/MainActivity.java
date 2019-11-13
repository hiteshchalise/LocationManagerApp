package com.utopia.locationmanagerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LocationManagerApp";
    private LocationManager locationManager;
    private HashMap<String, Coords> checkPointCoords = new HashMap<>();
    TextView coordinateTV, checkPointTv, currentPositionTV;
    private MainViewModel mainViewModel;
    private Set<String> checkPointsVisited;
    private MainViewModel mainViewModel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinateTV = findViewById(R.id.CoordinateTV);
        checkPointTv = findViewById(R.id.checkPointsTV);
        currentPositionTV = findViewById(R.id.currentPositionTV);

        mainViewModel1 = ViewModelProviders.of(this).get(MainViewModel.class);
        checkPointsVisited = mainViewModel1.getCheckPointsVisited();

        checkPointCoords.put("HiteshHome", new Coords(27.648771, 85.429155));
//        checkPointCoords.put("KhajaKhaneThau", new Coords(27.690973, 85.335695));
        checkPointCoords.put("Suryabinayek", new Coords(27.6657667, 85.4256237));
        checkPointCoords.put("Sallaghari", new Coords(27.6699162, 85.4108176));
        checkPointCoords.put("Srijananagar", new Coords(27.6728771, 85.4039317));
        checkPointCoords.put("RadheRadhe", new Coords(27.674544, 85.397655));
        checkPointCoords.put("Thimi", new Coords(27.673387, 85.386351));
//        checkPointCoords.put("Chardobato", new Coords(27.673393, 85.380963));
        checkPointCoords.put("Baneshwor", new Coords(27.688296, 85.335575));
        checkPointCoords.put("Tinkune", new Coords(27.685821, 85.346680));
        checkPointCoords.put("Koteshwor", new Coords(27.678794, 85.349503));
        checkPointCoords.put("Jadibuti", new Coords(27.675231, 85.352776));
        checkPointCoords.put("GatthaGhar", new Coords(27.673968, 85.373199));
        checkPointCoords.put("Lokanthali", new Coords(27.674763, 85.360379));
//
//        checkPointCoords.put("Office", new Coords(27.691578, 85.338074));
//
//        checkPointCoords.put("Pepsicola", new Coords(27.688747, 85.360031));
//        checkPointCoords.put("Khahare", new Coords(27.696584, 85.366238));
//        checkPointCoords.put("Mahantar", new Coords(27.705991, 85.370607));
//        checkPointCoords.put("Tejbinayek", new Coords(27.706946, 85.378797));
//        checkPointCoords.put("SampadaGhar", new Coords(27.709314, 85.381962));

        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        enableLocationManager();
    }

    @SuppressLint("MissingPermission")
    private void enableLocationManager() {
        Log.d(TAG, "onCreate: Permission Granted");
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d(TAG, "onCreate: gps is enabled");
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            measureDistance(location);
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
                    });
        } else {
            Log.d(TAG, "onCreate: gps is not enabled");
        }
    }

    private void measureDistance(Location newLocation) {
        double startLatitude = newLocation.getLatitude();
        double startLongitude = newLocation.getLongitude();
        Log.d(TAG, "measureDistance: here");

        coordinateTV.setText("");
        currentPositionTV.setText(String.format("Latitude: %s Longitude: %s", newLocation.getLatitude(), newLocation.getLongitude()));

        for (Map.Entry<String, Coords> entry :
                checkPointCoords.entrySet()) {
            String key = entry.getKey();
            Coords value = entry.getValue();

            float[] results = new float[1];
            Location.distanceBetween(startLatitude, startLongitude, value.getLatitude(), value.getLongitude(), results);
            float distanceInMeters = results[0];

            coordinateTV.append(key + " --> " + distanceInMeters + "\n");

            if (distanceInMeters < 50) {
                Log.d(TAG, "distance is less then 50 meters " + distanceInMeters);
                if (!checkPointsVisited.contains(key)) {

                    int rawRez = getRawRez(key);
                    Log.d(TAG, "measureDistance: rawres: " + rawRez + "ting: " + R.raw.ding);
                    MediaPlayer mPlayer = MediaPlayer.create(this, rawRez);
                    Log.d(TAG, "measureDistance: mPlayer: " + mPlayer);
                    mPlayer.start();
                    checkPointsVisited.add(key);
                }
                checkPointTv.setText("");
                for (String s : checkPointsVisited) {
                    Log.d(TAG, "measureDistance: checkpointVisited " + s);
                    checkPointTv.append(s + "\n");
                }
            } else {
                Log.d(TAG, "distance is more than 1000 meters " + distanceInMeters);
            }
        }
    }

    private int getRawRez(String key) {
        switch (key){
            case "Baneshwor": return R.raw.baneshwor;
            case "GatthaGhar" : return R.raw.gathhaghar;
            case "Jadibuti" : return R.raw.jadibuti;
            case "Khahare" : return R.raw.khahare;
            case "Koteshwor" : return R.raw.koteshwor;
            case "Lokanthali" : return R.raw.lokanthali;
            case "Mahantar" : return R.raw.mahantar;
            case "manisha_ghar" : return R.raw.manisha_ghar;
            case "Pepsicola" : return R.raw.pepsicola;
            case "RadheRadhe" : return R.raw.radheradhe;
            case "Sallaghari" : return R.raw.sallaghari;
            case "Srijananagar" : return R.raw.srijananagar;
            case "Suryabinayek" : return R.raw.suryabinayak;
            case "Tejbinayek" : return R.raw.tejbinayek;
            case "Thimi" : return R.raw.thimi;
            case "Tinkune" : return R.raw.tinkune;
        }
        return R.raw.ding;
    }

}

