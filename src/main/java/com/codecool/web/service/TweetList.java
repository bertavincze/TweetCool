package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetList {

    private List<Tweet> tweets;

    public TweetList() {
        tweets = new ArrayList<>();
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void filterList(int limit, int offset, String poster, Date date) {
        List<Tweet> filtered = new ArrayList<>();
        for (int i = offset; i < limit; i++) {
            if ((poster == null) && (date == null)) {
                filtered.add(tweets.get(i));
            } else if (tweets.get(i).getPosterName().equals(poster) && (date == null)) {
                filtered.add(tweets.get(i));
            } else if (tweets.get(i).getPosterName().equals(poster)) {
                if (date != null && tweets.get(i).getTimestamp().after(date)) {
                    filtered.add(tweets.get(i));
                }
            } else {
                if (date != null && tweets.get(i).getTimestamp().after(date)) {
                    filtered.add(tweets.get(i));
                }
            }
        }
        tweets = filtered;
    }
}
