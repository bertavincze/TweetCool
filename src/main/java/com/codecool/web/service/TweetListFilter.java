package com.codecool.web.service;

import com.codecool.web.model.Filter;
import com.codecool.web.model.Tweet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetListFilter {

    private Filter filter;
    private int limit;
    private int offset;

    public TweetListFilter(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    private void setFilter(String poster, Date date) {
        if (poster == null && date == null) {
            filter = Filter.NONE;
        } else if (poster == null) {
            filter = Filter.DATE;
        } else if (date == null) {
            filter = Filter.POSTER;
        } else {
            filter = Filter.POSTERDATE;
        }
    }

    public TweetList filterList(TweetList tweetList, String poster, Date date) {
        TweetList filtered = new TweetList();
        setFilter(poster, date);
        if (filter.equals(Filter.DATE)) {
            filtered = filterByDate(tweetList, date);
        } else if (filter.equals(Filter.POSTER)) {
            filtered = filterByPoster(tweetList, poster);
        } else if (filter.equals(Filter.POSTERDATE)) {
            filtered = filterByPosterAndDate(tweetList, poster, date);
        }
        return filterByLimitAndOffset(filtered);
    }

    private TweetList filterByPoster(TweetList tweetList, String poster) {
        TweetList filtered = new TweetList();
        for (Tweet tweet : tweetList.getTweets()) {
            if (tweet.getPosterName().equals(poster)) {
                filtered.addTweet(tweet);
            }
        }
        return filtered;
    }

    private TweetList filterByDate(TweetList tweetList, Date date) {
        TweetList filtered = new TweetList();
        for (Tweet tweet : tweetList.getTweets()) {
            if (tweet.getTimestamp().compareTo(date) >= 0) {
                filtered.addTweet(tweet);
            }
        }
        return filtered;
    }

    private TweetList filterByPosterAndDate(TweetList tweetList, String poster, Date date) {
        TweetList filtered = filterByPoster(tweetList, poster);
        filtered = filterByDate(filtered, date);
        return filtered;
    }

    private TweetList filterByLimitAndOffset(TweetList tweetList) {
        TweetList filtered = new TweetList();
        if (tweetList.getTweets().size() < limit) {
            limit = tweetList.getTweets().size();
        }
        for (int i = offset; i < limit; i++) {
            filtered.addTweet(tweetList.getTweets().get(i));
        }
        return filtered;
    }
}
