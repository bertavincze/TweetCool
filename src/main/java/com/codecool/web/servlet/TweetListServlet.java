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

    private final Date defaultDate = new Date(33010000);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        String poster = request.getParameter("poster");
        String dateString = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        TweetList tweets = (TweetList) request.getSession().getAttribute("tweets");

        Date date;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            date = defaultDate;
        }

        TweetList filtered = null;
        if (tweets != null) {
            TweetListFilter filter = new TweetListFilter(limit, offset);
            filtered = filter.filterList(tweets, poster, date);
        }

        request.setAttribute("filtered", filtered);
        request.getRequestDispatcher("tweetsfiltered.jsp").forward(request, response);
    }

}
