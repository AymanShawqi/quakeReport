package com.example.android.quakereport;


public class Earthquake {
   private double mMagnitude;
   private String mLocation;
    private Long mTimeInMilliseconds;
    private String mURL;

    public Earthquake(double magnitude, String location, Long timeInMilliseconds, String URL) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mURL = URL;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public Long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getURL() {
        return mURL;
    }
}
