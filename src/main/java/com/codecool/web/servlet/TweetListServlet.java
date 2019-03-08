package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/tweets")
public class TweetListServlet extends HttpServlet {

    private TweetList tweets;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        tweets = (TweetList) request.getSession().getAttribute("tweets");

        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        String poster = request.getParameter("poster");
        Date date = getDateFromString(request.getParameter("date"));

        if (tweets != null && tweets.getTweets().size() < limit) {
            limit = tweets.getTweets().size();
            tweets.filterList(limit, offset, poster, date);
            request.setAttribute("filtered", tweets);
            request.getRequestDispatcher("tweets.jsp").forward(request, response);
        }

    }

    private Date getDateFromString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
