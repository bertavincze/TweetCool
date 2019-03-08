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
            } else if (poster.equals(tweets.get(i).getPosterName()) && (date == null)) {
                filtered.add(tweets.get(i));
            } else if (poster.equals(tweets.get(i).getPosterName())) {
                if (isNewer(date, i)) {
                    filtered.add(tweets.get(i));
                }
            } else {
                if (isNewer(date, i)) {
                    filtered.add(tweets.get(i));
                }
            }
        }
        tweets = filtered;
    }

    private boolean isNewer(Date date, int index) {
        return tweets.get(index).getTimestamp().getTime() > date.getTime();
    }
}
