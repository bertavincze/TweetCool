package com.codecool.web.servlet;

import com.codecool.web.listener.WebappContextListener;
import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new-tweet")
public class TweetServlet extends HttpServlet {

    private TweetList tweets = new TweetList();
    private int num = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        int id = num++;
        Tweet tweet = new Tweet(id, name, content);

        if (!WebappContextListener.xmlHandler.getTweetList().getTweets().isEmpty()) {
            tweets = WebappContextListener.xmlHandler.getTweetList();
        } else {
            tweets.addTweet(tweet);
        }
        WebappContextListener.xmlHandler.getTweetList().addTweet(tweet);

        request.getSession().setAttribute("tweets", tweets);
        request.setAttribute("name", name);
        request.setAttribute("content", content);
        request.getRequestDispatcher("index.html").forward(request, response);
    }
}
