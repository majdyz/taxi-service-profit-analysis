package main;

import java.util.ArrayList;

/**
 * Created by kadal_ijo on 9/4/2015.
 */
public class Data {
    String  TRIP_ID;
    char CALL_TYPE;
    int  ORIGIN_CALL;
    int  ORIGIN_STAND;
    int  TAXI_ID;
    int  TIMESTAMP;
    char DAY_TYPE;
    boolean  MISSING_DATA;
    double CENTROID_LATITUDE;
    double CENTROID_LONGITUDE;
    double COST;

    ArrayList<Coordinate> POLYLINE;

    public Data () {
        POLYLINE = new ArrayList<Coordinate>();
    }

    public String toString() {
        return TRIP_ID + " , " + CALL_TYPE + " , " + ORIGIN_CALL + " , " + ORIGIN_STAND + " , " + TAXI_ID + " , " + TIMESTAMP + " , " + DAY_TYPE + " , " + MISSING_DATA + " , " + POLYLINE;
    }
}

class Coordinate {
    public double latitude;
    public double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString() {
        return "(" + latitude + "," + longitude + ")";
    }
}
