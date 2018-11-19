package com.example.android.quakereport;

/**
 * Custom {@link Earthquake} class to hold data for each specific earthquake
 */
class Earthquake {

    /**
     * This is the magnitude of the {@link Earthquake} object
     */
    private double mMag;

    /**
     * This is the Place where the {@link Earthquake} hit
     */
    private String mPlace;

    /**
     * This is the date when the {@link Earthquake} struck
     */
    private long mTimeInMilliseconds;

    /**
     * This is the url to the earthquake page
     */
    private String mUrl;

    /**
     * Constructor for the {@link Earthquake} object
     *
     * @param mag                is the magnitued of the {@link Earthquake}
     * @param place              is the place where the {@link Earthquake} struck
     * @param timeInMilliseconds is the timeInMilliseconds when the {@link Earthquake} hit
     * @param url is the link to the {@Earthquake} page
     */
    Earthquake(double mag, String place, long timeInMilliseconds, String url) {
        this.mMag = mag;
        this.mPlace = place;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    double getMag() {
        return mMag;
    }

    String getPlace() {
        return mPlace;
    }

    long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
