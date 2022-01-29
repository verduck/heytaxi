package com.moca.heytaxi.util;

import com.moca.heytaxi.dto.LatLng;

public class Utils {
    public static double distance(LatLng latLng1, LatLng latLng2, String unit) {
        double theta = latLng1.getLongitude() - latLng2.getLongitude();
        double dist = Math.sin(deg2rad(latLng1.getLatitude())) * Math.sin(deg2rad(latLng2.getLatitude()))
                + Math.cos(deg2rad(latLng1.getLatitude())) * Math.cos(deg2rad(latLng2.getLatitude())) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit.equals("kilometer")) {
            dist = dist * 1.609344;
        } else if(unit.equals("meter")){
            dist = dist * 1609.344;
        }

        return dist;
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
