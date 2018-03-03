package co.iam_infinity.www.hackathon.model;

/**
 * Created by Infinity on 03-03-2018.
 */

public class TaxData {
    String name;
    String uid;
    String year;
    String status;
    String type;

    public TaxData() {
    }

    public TaxData(String name, String uid, String year, String status, String type) {
        this.name = name;
        this.uid = uid;
        this.year = year;
        this.status = status;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
