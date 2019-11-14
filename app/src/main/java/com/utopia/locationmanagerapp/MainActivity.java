package com.utopia.locationmanagerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements LocationHandler.View {
    private static final String TAG = "LocationManagerApp";

    TextView distancesTV, checkPointTv, currentPositionTV;
    JsonParser<List<Coords>> jsonParser;
    private LocationHandler listener;
    private LocationManager locationManager;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        distancesTV = findViewById(R.id.distancesTV);
        checkPointTv = findViewById(R.id.checkPointsTV);
        currentPositionTV = findViewById(R.id.currentPositionTV);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        jsonParser = new JsonParserImpl();

        InputStream inputStream = this.getResources().openRawResource(R.raw.sample_json_coords);
        String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();
        List<Coords> checkPointCoords;
        checkPointCoords = jsonParser.getListOfCord(jsonString);
        for (Coords checkPointCoord : checkPointCoords) {
            Log.d(TAG, "onCreate: " + checkPointCoord);
        }
        listener = new LocationHandler(this, this, checkPointCoords);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 0, listener);
    }

    @Override
    public void handleCurrentPositionChange(Location newLocation) {
        currentPositionTV.setText(String.format("Latitude: %s Longitude: %s", newLocation.getLatitude(), newLocation.getLongitude()));
    }

    @Override
    public void handleDistancesChange(HashMap<String, Float> distances) {
        distancesTV.setText("");
        for (Map.Entry<String, Float> entry : distances.entrySet()) {
            distancesTV.append(entry.getKey() + " ----> " + entry.getValue() + "\n");
        }
    }

    @Override
    public void handleCheckPointsVisited(Set<String> visitedCheckPoints) {
        checkPointTv.setText("");
        for (String visitedCheckPoint : visitedCheckPoints) {
            checkPointTv.setText(visitedCheckPoint);
        }
    }

    @Override
    protected void onStop() {
        locationManager.removeUpdates(listener);
        super.onStop();
    }
}

