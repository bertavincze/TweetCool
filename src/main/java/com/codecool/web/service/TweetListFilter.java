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
        if (poster == null) {
            filter = Filter.DATE;
        } else if (date == null) {
            filter = Filter.POSTER;
        } else {
            filter = Filter.POSTERDATE;
        }
    }

    public TweetList filterList(TweetList tweetList, String poster, Date date) {
        setFilter(poster, date);
        if (filter.equals(Filter.DATE)) {
            filterByDate(tweetList, date);
        } else if (filter.equals(Filter.POSTER)) {
            filterByPoster(tweetList, poster);
        } else if (filter.equals(Filter.POSTERDATE)) {
            filterByPosterAndDate(tweetList, poster, date);
        }
        filterByLimitAndOffset(tweetList);
        return tweetList;
    }

    private void filterByPoster(TweetList tweetList, String poster) {
        List<Tweet> filtered = new ArrayList<>();
        for (Tweet tweet : tweetList.getTweets()) {
            if (tweet.getPosterName().equals(poster)) {
                filtered.add(tweet);
            }
        }
        tweetList.setTweets(filtered);
    }

    private void filterByDate(TweetList tweetList, Date date) {
        List<Tweet> filtered = new ArrayList<>();
        for (Tweet tweet : tweetList.getTweets()) {
            if (tweet.getTimestamp().compareTo(date) >= 0) {
                filtered.add(tweet);
            }
        }
        tweetList.setTweets(filtered);
    }

    private void filterByPosterAndDate(TweetList tweetList, String poster, Date date) {
        filterByPoster(tweetList, poster);
        filterByDate(tweetList, date);
    }

    private void filterByLimitAndOffset(TweetList tweetList) {
        List<Tweet> filtered = new ArrayList<>();
        if (tweetList.getTweets().size() < limit) {
            limit = tweetList.getTweets().size();
        }
        for (int i = offset; i < limit; i++) {
            filtered.add(tweetList.getTweets().get(i));
        }
        tweetList.setTweets(filtered);
    }
}
