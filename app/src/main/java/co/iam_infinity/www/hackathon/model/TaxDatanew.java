package co.iam_infinity.www.hackathon.model;

/**
 * Created by ajitm on 24-03-2018.
 */

public class TaxDatanew {
    String uid;
    String data;

    public TaxDatanew() {
    }

    public TaxDatanew(String uid, String data) {
        this.uid = uid;
        this.data = data;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
