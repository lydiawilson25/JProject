package com.healtline.informatics.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: wstephen
 * Date: 9/30/14
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class FDAProperties {

    public static Properties constantsProp;
    public static Properties dbProp;
    static Logger logger = Logger.getLogger(FDAProperties.class);

    public static void setProperties() {
        InputStream input = null;
        logger.debug("Start of the method setProperties()");
        try {
            input = new FileInputStream("./properties/constants.properties");
            constantsProp = new Properties();
            constantsProp.load(input);
            input = new FileInputStream("./properties/dev.properties");
            dbProp = new Properties();
            dbProp.load(input);
            logger.info("Properties are loaded");

        } catch (IOException ex) {
            logger.error("The IO exception is: "+ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("The next IO exception is: "+e);
                }
            }
        }
        logger.debug("End of the method setProperties()");
    }

}
