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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public void addTweet(Tweet tweet) {
        tweetList.addTweet(tweet);
    }

    public void loadFromFile(String filename) throws IOException, SAXException {
        DocumentBuilder db = createDocumentBuilder();
        InputStream is = new FileInputStream(filename);
        document = db.parse(is);
        document.getDocumentElement().normalize();
    }

    public void saveToFile(String filename) throws IOException, SAXException, TransformerException {
        for (String file : getFiles()) {
            if (filename.equals(file)) {
                loadFromFile(filename);
                break;
            } else {
                createNewXML();
            }
        }
        transformToFile(filename);


    }

    private void createNewXML() {
        Element root = document.createElement("tweets");
        document.appendChild(root);
        for (Tweet tweet : tweetList.getTweets()) {
            writeNodeToDocument(tweet);
        }
    }

    private void writeNodeToDocument(Tweet tweet) {
        Element tempElement = document.createElement("tweet");
        document.getDocumentElement().appendChild(tempElement);
        createElement("id", String.valueOf(tweet.getId()));
        createElement("poster name", tweet.getPosterName());
        createElement("content", tweet.getContent());
        createElement("timestamp", sdf.format(tweet.getTimestamp()));
    }

    private void transformToFile(String filename) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }

    private String getString(List<Element> elements, String name) {
        for (Element element : elements) {
            if (element.getTagName().equals(name)) {
                return element.getTextContent();
            }
        }
        throw new IllegalStateException();
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

    private List<String> getFiles() {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".xml"));
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && !file.getName().equals("pom.xml")) {
                    fileNames.add(file.getName());
                }
            }
        }
        return fileNames;
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

    private void createElement(String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        document.getDocumentElement().appendChild(element);
    }
}
