package com.codecool.web.model;

import java.util.Date;

public class Tweet {

    private int id;
    private String poster;
    private String content;
    private Date timeStamp;

    public Tweet(int id, String poster, String content) {
        this.id = id;
        this.poster = poster;
        this.content = content;
        this.timeStamp = new Date(getTimeInMilliseconds());
    }

    public int getId() {
        return id;
    }

    public String getPosterName() {
        return poster;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timeStamp;
    }

    public boolean isNewerThan(Date date) {
        return this.getTimestamp().getTime() > date.getTime();
    }

    private Long getTimeInMilliseconds() {
        Date date = new Date();
        return date.getTime();
    }
}
