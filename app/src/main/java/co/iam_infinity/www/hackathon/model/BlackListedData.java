package co.iam_infinity.www.hackathon.model;

/**
 * Created by Infinity on 02-03-2018.
 */

public class BlackListedData {
    String bizType;
    String desp;
    String name;
    String status;

    public BlackListedData() {
    }

    public BlackListedData(String bizType, String desp, String name, String status) {
        this.bizType = bizType;
        this.desp = desp;
        this.name = name;
        this.status = status;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
