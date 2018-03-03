package co.iam_infinity.www.hackathon.model;

/**
 * Created by Infinity on 02-03-2018.
 */

public class Notification {
    String title;
    String body;
    String timeStamp;

    public Notification() {
    }

    public Notification(String title, String body, String timeStamp) {
        this.title = title;
        this.body = body;
        this.timeStamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
