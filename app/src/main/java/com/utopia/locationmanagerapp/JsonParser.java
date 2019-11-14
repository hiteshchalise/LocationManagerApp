package com.utopia.locationmanagerapp;

import org.json.JSONException;

import java.util.List;

public interface JsonParser<T> {
    T getListOfCord(String jsonString);
}
