package com.healtline.informatics;

import com.healtline.informatics.db.DBConnectionManager;
import com.healtline.informatics.db.InfoHandler;
import com.healtline.informatics.normalization.Dictionary;
import com.healtline.informatics.utils.FDAProperties;
import com.healtline.informatics.utils.ResourceUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: wstephen
 * Date: 9/26/14
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class DrugsProcessor {
    static Logger logger = Logger.getLogger(DrugsProcessor.class);
    public static void main(String[] args) {
        logger.debug("Start of the method main()");
        long startTime = Calendar.getInstance().getTime().getTime();
        Controller c = new Controller();
        c.run();

        // updates code end

        // Stop timer and display processing time.
        long endTime = Calendar.getInstance().getTime().getTime();
        String time = ResourceUtils.displayProcessTime(startTime, endTime);
        String out = "Mission accomplished in " + time + ".";
        logger.info(out);
        logger.info("Finished.");
        logger.debug("End of the method main()");
    }
}
