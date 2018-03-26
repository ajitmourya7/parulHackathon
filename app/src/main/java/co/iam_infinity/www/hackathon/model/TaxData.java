package co.iam_infinity.www.hackathon.model;

/**
 * Created by Infinity on 03-03-2018.
 */

public class TaxData {
    String data;
    String uid;
    String year;
    String status;
    String type;
    String amount;

    public TaxData() {
    }

    public TaxData(String name, String uid, String year, String status, String type, String amount) {
        this.data = name;
        this.uid = uid;
        this.year = year;
        this.status = status;
        this.type = type;
        this.amount = amount;
    }

    public String getName() {
        return data;
    }

    public void setName(String name) {
        this.data = name;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
