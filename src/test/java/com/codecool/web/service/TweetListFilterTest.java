package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TweetListFilterTest {

    private TweetList tweetList;
    private TweetListFilter tweetListFilter;
    private Tweet tweetYoda;
    private Tweet tweetDonald;
    private Date ancientDate;
    private Date recentDate;

    @BeforeEach
    void setUp() {
        tweetListFilter = new TweetListFilter(10, 0);
        ancientDate = new Date(33010000); // 1970.01.01 10:10:10 in long millis
        recentDate = new Date(1552136085000L); // 2019.03.09 13:54:45 in long millis
        tweetYoda = new Tweet(1, "Yoda", "Do. Or do not. There is no try.", ancientDate);
        tweetDonald = new Tweet(2, "Donald Trump", "Despite the negative press covfefe", recentDate);
        tweetList = new TweetList();
        tweetList.addTweet(tweetDonald);
        tweetList.addTweet(tweetYoda);
    }

    @AfterEach
    void tearDown() {
        tweetListFilter = null;
        ancientDate = null;
        recentDate = null;
        tweetYoda = null;
        tweetDonald = null;
        tweetList = null;
    }

    @Test
    void filterListByDate() {
        tweetListFilter.filterList(tweetList, null, ancientDate);
        assertEquals(Arrays.asList(tweetDonald, tweetYoda), tweetList.getTweets());
        tweetListFilter.filterList(tweetList, null, recentDate);
        assertEquals(Collections.singletonList(tweetDonald), tweetList.getTweets());
    }

    @Test
    void filterListByPoster() {
        tweetListFilter.filterList(tweetList, "Yoda", null);
        assertEquals(Collections.singletonList(tweetYoda), tweetList.getTweets());
    }

    @Test
    void filterListByPosterAndDate() {
        tweetListFilter.filterList(tweetList, "Yoda", ancientDate);
        assertEquals(Collections.singletonList(tweetYoda), tweetList.getTweets());
    }
}
