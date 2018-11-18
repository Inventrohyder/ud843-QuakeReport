package com.example.android.quakereport;

/**
 * Custom {@link Earthquake} class to hold data for each specific earthquake
 */
class Earthquake {

    /**
     * This is the magnitude of the {@link Earthquake} object
     */
    private String mMag;

    /**
     * This is the Place where the {@link Earthquake} hit
     */
    private String mPlace;

    /**
     * This is the date when the {@link Earthquake} struck
     */
    private String mDate;

    /**
     * Constructor for the {@link Earthquake} object
     * @param mag is the magnitued of the {@link Earthquake}
     * @param place is the place where the {@link Earthquake} struck
     * @param date is the date when the {@link Earthquake} hit
     */
    public Earthquake(String mag, String place, String date){
        this.mMag = mag;
        this.mPlace = place;
        this.mDate = date;
    }

    String getMag() {
        return mMag;
    }

    public void setMag(String mag) {
        this.mMag = mag;
    }

    public String getPlace() {
        return mPlace;
    }

    public void setPlace(String place) {
        this.mPlace = place;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }
}
