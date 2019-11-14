package com.utopia.locationmanagerapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonParserImpl implements JsonParser<java.util.List<Coords>> {
    private static final String TAG = "LocationManagerApp";
    @Override
    public List<Coords> getListOfCord(String jsonString){
        String name;
        JSONArray jsonArray = null;
        List<Coords> coordsList = new ArrayList<>();
        try {
            jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                double[] coordinates = new double[2];
                name = row.getString("location");
                JSONArray coordinateArray = row.getJSONArray("coordinates");
                coordinates[0] = coordinateArray.getDouble(0);
                coordinates[1] = coordinateArray.getDouble(1);

                Coords e = new Coords(name, coordinates);
                coordsList.add(e);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return coordsList;
    }
}
