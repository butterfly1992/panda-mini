package com.mini.server.tools;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MySpecialListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            JDBC.freeDataSources();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextInitialized(ServletContextEvent arg0) {
        try {
            JDBC.close(JDBC.getDataSource().getConnection());
            Memcached.getInstance();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}