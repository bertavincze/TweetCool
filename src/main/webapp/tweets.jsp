<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TweetCool</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>

<div class="header">
    <a href="index.html"><h1>TweetCool</h1></a>
</div>

<div class="wrapper">
    <div class="content">
        <div class="container">

            <div class="containerhead">
                <div class="title"><a href="">Tweets</a></div>
            </div>

            <c:choose>
                <c:when test="${tweets != null}">
                    <form action="/TweetCool/tweets" method="GET">
                        Limit:
                        <select id="limit" name="limit">
                            <option value="10" selected>10</option>
                            <option value="20">20</option>
                            <option value="30">30</option>
                            <option value="40">40</option>
                        </select>
                        <br>
                        Offset:
                        <select id="offset" name="offset">
                            <option value="0" selected>0</option>
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                        </select>
                        <br>
                        Poster:
                        <input type="text" name="poster">
                        <br>
                        Date:
                        <input type="datetime-local" name="date" />
                        <br>
                        <p><input type="submit" value="Filter tweets"></p>
                    </form>
                    <c:forEach var="tweet" items="${tweets.getTweets()}">
                        <div class="divider"></div>
                        <c:out value="${tweet.getPosterName()}"/> : <c:out value="${tweet.getContent()}"/><br>
                        <c:out value="${tweet.getTimestamp()}"/><br>
                        <div class="divider"></div>
                        <br>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No tweets have been sent yet!</p>
                </c:otherwise>
            </c:choose>

    <div class="containerfoot"></div>
    </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">

            <div class="desc">
                <div class="containerhead">
                    <div class="title">Info</div>
                </div>
                <p>
                    TweetCool is a Twitter clone for Codecool.
                </p>
            </div>

            <ul class="links">
                <div class="linktitle">Navigation</div>
                <li><a href="index.html">Home</a></li>
                <li><a href="tweets.jsp">Tweets</a></li>
            </ul>

        </div>
    </div>

</div>
</body>
</html>
