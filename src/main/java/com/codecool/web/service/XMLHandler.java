package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLHandler {

    private Document document;
    private TweetList tweetList;
    private SimpleDateFormat sdf;

    public XMLHandler() {
        DocumentBuilder docBuilder = createDocumentBuilder();
        this.document = docBuilder.newDocument();
        this.tweetList = new TweetList();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
    }

    public void loadFromFile(String filename) throws IOException, SAXException, ParseException {
        DocumentBuilder db = createDocumentBuilder();
        InputStream is = new FileInputStream(filename);
        document = db.parse(is);
        document.getDocumentElement().normalize();
        getTweetsFromFile();
    }

    private void getTweetsFromFile() throws ParseException {
        List<Element> elements = getElements(document.getDocumentElement());
        for (Element element: elements) {
            int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            String poster = element.getElementsByTagName("poster name").item(0).getTextContent();
            String content = element.getElementsByTagName("content").item(0).getTextContent();
            Date timestamp = sdf.parse(element.getElementsByTagName("timestamp").item(0).getTextContent());
            tweetList.addTweet(new Tweet(id,poster, content, timestamp));
        }
    }

    public void saveToFile(String filename) throws TransformerException {
        createXML();
        transformToFile(filename);
    }

    private void createXML() {
        Element root = document.createElement("tweets");
        document.appendChild(root);
        for (Tweet tweet : tweetList.getTweets()) {
            writeNodeToDocument(tweet);
        }
    }

    private void writeNodeToDocument(Tweet tweet) {
        Element tempElement = document.createElement("tweet");
        document.getDocumentElement().appendChild(tempElement);
        createElement("id", String.valueOf(tweet.getId()), tempElement);
        createElement("poster name", tweet.getPosterName(), tempElement);
        createElement("content", tweet.getContent(), tempElement);
        createElement("timestamp", sdf.format(tweet.getTimestamp()), tempElement);
    }

    private void transformToFile(String filename) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }

    private List<Element> getElements(Element parentNode) {
        ArrayList<Element> elements = new ArrayList<>();
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node childNode = parentNode.getChildNodes().item(i);
            if (childNode instanceof Element) {
                elements.add((Element) childNode);
            }
        }
        return elements;
    }

    private DocumentBuilder createDocumentBuilder() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }

    private void createElement(String tagName, String textContent, Element element) {
        Element newElement = document.createElement(tagName);
        newElement.setTextContent(textContent);
        element.appendChild(newElement);
    }

    public TweetList getTweetList() {
        return tweetList;
    }
}
