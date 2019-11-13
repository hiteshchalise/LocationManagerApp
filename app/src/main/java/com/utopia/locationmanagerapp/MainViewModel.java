package com.utopia.locationmanagerapp;

import androidx.lifecycle.ViewModel;

import java.util.HashSet;
import java.util.Set;

public class MainViewModel extends ViewModel {

    private Set<String> checkPointsVisited = new HashSet<>();

    public Set<String> getCheckPointsVisited() {
        return checkPointsVisited;
    }
}
