package com.codecool.web.model;

import java.util.Date;

public class Tweet {

    private int id;
    private String poster;
    private String content;
    private Date timestamp;

    public Tweet(int id, String poster, String content) {
        this.id = id;
        this.poster = poster;
        this.content = content;
        this.timestamp = new Date(System.currentTimeMillis());
    }

    public Tweet(int id, String poster, String content, Date timestamp) {
        this.id = id;
        this.poster = poster;
        this.content = content;
        this.timestamp = timestamp;
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
        return timestamp;
    }
}
