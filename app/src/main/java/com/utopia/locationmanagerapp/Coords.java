package com.utopia.locationmanagerapp;

import androidx.annotation.NonNull;

import java.util.List;

public class Coords {

    private final double[] coordinates;
    private final String name;

    public Coords(String name, double[] coordinates) {
        if (checkCoordinates(coordinates)) {
            this.coordinates = coordinates;
            this.name = name;
        }else {
            throw new IllegalArgumentException("Coordinates is invalid");
        }
    }

    private boolean checkCoordinates(double[] coordinates) {
        return coordinates.length == 2;
    }

    public String getName() {
        return name;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    @NonNull
    @Override
    public String toString() {
        return "Place: " + name + "Latitude: " + this.coordinates[0] + " Longitude: " + this.coordinates[1];
    }

//    static final class Preset {
//        static final Coords HiteshHome = new Coords("HiteshHome", 27.648771, 85.429155);
//        static final Coords Suryabinayek = new Coords("Suryabinayek", 27.6657667, 85.4256237);
//        static final Coords Sallaghari = new Coords("Srijananagar", 27.6699162, 85.4108176);
//        static final Coords Srijananagar = new Coords("Srijananagar", 27.6728771, 85.4039317);
//        static final Coords RadheRadhe = new Coords("RadheRadhe", 27.674544, 85.397655);
//        static final Coords Thimi = new Coords("Thimi", 27.673387, 85.386351);
//        static final Coords Baneshwor = new Coords("Baneshwor", 27.688296, 85.335575);
//        static final Coords Tinkune = new Coords("Tinkune", 27.685821, 85.346680);
//        static final Coords Koteshwor = new Coords("Koteshwor", 27.678794, 85.349503);
//        static final Coords Jadibuti = new Coords("Jadibuti", 27.675231, 85.352776);
//        static final Coords GatthaGhar = new Coords("GatthaGhar", 27.673968, 85.373199);
//        static final Coords Lokanthali = new Coords("Lokanthali", 27.674763, 85.360379);
//        static final Coords Office = new Coords("Office", 27.691578, 85.338074);
//    }
}
