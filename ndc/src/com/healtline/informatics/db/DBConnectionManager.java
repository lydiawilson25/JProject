package com.healtline.informatics.db;

import com.healtline.informatics.utils.FDAProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by IntelliJ IDEA.
 * User: wstephen
 * Date: 9/26/14
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBConnectionManager {
    static Logger logger = Logger.getLogger(DBConnectionManager.class);
    public static Connection taxonomyConnection() {
        logger.debug("Start of the method createConnection()");
        // JDBC driver name and database URL
        String DB_URL = FDAProperties.dbProp.getProperty("INF_DB_URL");

        //  Database credentials
        String USER = FDAProperties.dbProp.getProperty("INF_USERNAME");
        String PASS = FDAProperties.dbProp.getProperty("INF_PASSWORD");
        Connection conn = null;
        //Register JDBC driver
        try {
            Class.forName(FDAProperties.dbProp.getProperty("JDBC_DRIVER"));
        } catch (ClassNotFoundException e) {
            logger.error("The Classnotfound exception is: "+e);
        }

        //Open a connection
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.debug("Got the connection");
        } catch (SQLException e) {
            logger.error("The SQL exception is: "+e);
        }
        logger.debug("End of the method createConnection()");
        return conn;
    }

    public static Connection dictionaryConnection() {
        logger.debug("Start of the method createConnection()");
        // JDBC driver name and database URL
        String DB_URL = FDAProperties.dbProp.getProperty("DICTIONARY_DB_URL");

        //  Database credentials
        String USER = FDAProperties.dbProp.getProperty("DISCTIONARY_USERNAME");
        String PASS = FDAProperties.dbProp.getProperty("DISCTIONARY_PASSWORD");
        Connection conn = null;
        //Register JDBC driver
        try {
            Class.forName(FDAProperties.dbProp.getProperty("JDBC_DRIVER"));
        } catch (ClassNotFoundException e) {
            logger.error("The Classnotfound exception is: "+e);
        }

        //Open a connection
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.debug("Got the connection");
        } catch (SQLException e) {
            logger.error("The SQL exception is: "+e);
        }
        logger.debug("End of the method createConnection()");
        return conn;
    }

    public static LinkedHashSet getResources(String sql) {
        logger.debug("Start of the method getResources()");
        LinkedHashSet rscObj = new LinkedHashSet();
        Connection conn = DBConnectionManager.taxonomyConnection();

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            rscObj.add(ps);
            logger.debug("Got the PreparedStatement Object");
        } catch (SQLException e) {
            logger.error("The SQL exception is: "+e);
        }
        rscObj.add(conn);
        logger.debug("End of the method getResources()");
        return rscObj;
    }

    public static void releaseResources(LinkedHashSet rscObj) {
        logger.debug("Start of the method releaseResources()");
        PreparedStatement ps = null;
        Connection conn = null;
        try {

            Iterator itr = rscObj.iterator();
            if (itr.hasNext()) {
                ps = (PreparedStatement) itr.next();
            }

            if (itr.hasNext()) {
                conn = (Connection) itr.next();
            }

            ps.close();
            conn.close();
            logger.debug("Closed the resource");
        } catch (SQLException se) {
            logger.error("The SQL exception is: "+se);
        } catch (Exception e) {
            logger.error("The exception is: "+e);
        } finally {
            //finally block used to close resources
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                logger.error("The next SQL exception is: "+se);
            }//end finally try
        }//end try
        logger.debug("End of the method releaseResources()");
    }

}
