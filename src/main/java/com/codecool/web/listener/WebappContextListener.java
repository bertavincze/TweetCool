package com.codecool.web.listener;

import com.codecool.web.service.XMLHandler;
import org.xml.sax.SAXException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    public static final XMLHandler xmlHandler = new XMLHandler();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets deployed.");
        try {
            xmlHandler.loadFromFile(sce.getServletContext().getRealPath("/") + "tweets.xml");
        } catch (IOException | SAXException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
        try {
            xmlHandler.saveToFile(sce.getServletContext().getRealPath("/") + "tweets.xml");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
