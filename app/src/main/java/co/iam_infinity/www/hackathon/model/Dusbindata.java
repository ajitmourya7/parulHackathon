package co.iam_infinity.www.hackathon.model;

/**
 * Created by Infinity on 25-02-2018.
 */

public class Dusbindata {
    String lat;
    String lng;

    public Dusbindata() {
    }

    public Dusbindata(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
