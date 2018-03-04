package co.iam_infinity.www.hackathon.model;

/**
 * Created by Infinity on 02-03-2018.
 */

public class SubmitFormData {
    String pic;
    String lat;
    String log;
    String desp;
    String address;

    public SubmitFormData() {
    }

    public SubmitFormData(String pic, String lat, String log, String desp, String address) {
        this.pic = pic;
        this.lat = lat;
        this.log = log;
        this.desp = desp;
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
