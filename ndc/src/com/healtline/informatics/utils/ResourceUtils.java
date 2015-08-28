package com.healtline.informatics.utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by IntelliJ IDEA.
 * User: wstephen
 * Date: 9/30/14
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceUtils {
    static Logger logger = Logger.getLogger(ResourceUtils.class);
    public static BufferedReader getReader(String filePath) {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(
                    new FileReader(filePath));
        } catch (FileNotFoundException e) {
            logger.error("File Not found");
        }
        return bReader;
    }

    /**
     * Displays the running time from the start to the end of Multum update
     * process.
     *
     * @param startTime
     *            start time of Multum update process
     * @param endTime
     *            end time of Multum update process
     * @return procces time: hours, minutes, and seconds
     */
    public static String displayProcessTime(long startTime, long endTime) {
        int sec = (int) (endTime - startTime) / 1000;
        int min = (int) sec / 60;
        int h = min / 60;
        if (h > 0) {
            min = min % 60;
        }
        if (min > 0) {
            sec = sec % 60;
        }
        String output = h + " hours " + min + " minutes and " + sec
                + " seconds";
        return output;
    }
}
