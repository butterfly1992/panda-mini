/**
 * FileName:     DaoFactory.java
 * CreationTime: 2010-3-29
 * Author:       qiujy
 * EMail:        qjyong@gmail.com
 * Site:         http://www.tjitcast.com
 * CopyRight: 2010-2012 All Recieves.
 */
package com.mini.server.dao;

import java.io.IOException;
import java.util.Properties;
/**
 * Dao工厂
 * 
 */
public class DaoFactory {

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getInstance(String daoName, Class<T> interfaceType) {
        T obj = null;

        try {
            obj = interfaceType.cast(Class.forName(prop.getProperty(daoName)).newInstance());
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
