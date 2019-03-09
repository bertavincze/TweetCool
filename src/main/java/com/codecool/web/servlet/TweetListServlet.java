package com.codecool.web.servlet;

import com.codecool.web.service.TweetList;
import com.codecool.web.service.TweetListFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/tweets")
public class TweetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        TweetList tweets = (TweetList) request.getSession().getAttribute("tweets");

        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        String poster = request.getParameter("poster");
        Date date = getDateFromString(request.getParameter("date"));

        if (tweets != null) {
            TweetListFilter filter = new TweetListFilter(limit, offset);
            tweets = filter.filterList(tweets, poster, date);
        }

        request.setAttribute("tweets", tweets);
        request.getRequestDispatcher("tweets.jsp").forward(request, response);
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
