package com.healtline.informatics;

import com.healtline.informatics.db.DBConnectionManager;
import com.healtline.informatics.db.InfoHandler;
import com.healtline.informatics.normalization.Dictionary;
import com.healtline.informatics.normalization.NormalizationTable;
import com.healtline.informatics.utils.FDAProperties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/20/14
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    static Logger logger = Logger.getLogger(DrugsProcessor.class);
    private static Set<String> dictionaryWords;

    /**
     * Runs the application: sets configuration properties, initializes database
     * connections, dictionary, normalization tables, and updates.
     */
    public void run() {
        logger.debug("Start of the method main()");
        try {
            //DOMConfigurator is used to configure logger from xml configuration file
            DOMConfigurator.configure("log4j.xml");
            FDAProperties.setProperties();

            InfoHandler info = new InfoHandler();
            //info.deleteTmpTable();
            //readContents();
            //info.comparePrdTblState();
            //info.comparePkgTblState();
            // QPEChecker class to be included - TO-DO
            initDictionary();
            initNormalization(true);

            //info.taxonomyChecker();
        } catch (Exception e) {
            logger.error("The IO exception is: " + e);
        }
        logger.debug("End of the method main()");
    }

    public static void readContents() throws IOException {
        logger.debug("Start of the method readContents()");
        Map<String, String> dirNames = null;
        String type = null;
        String DIR_PATH = FDAProperties.constantsProp.getProperty("NDC_DIR_PATH");

        File directory = new File(DIR_PATH);
        FileUtils.cleanDirectory(directory);

        URL u = null;
        try {
            u = new URL(FDAProperties.constantsProp.getProperty("NDC_URL"));
        } catch (MalformedURLException e) {
            logger.error("The MalformedURL exception is: " + e);
        }
        URLConnection uc = null;
        try {
            uc = u.openConnection();
        } catch (IOException e) {
            logger.error("The IO exception is: " + e);
        }
        type = uc.getContentType();

        ZipInputStream stream = null;
        if (uc != null) {
            try {
                stream = new ZipInputStream(uc.getInputStream());
            } catch (IOException e) {
                logger.error("The next IO exception is: " + e);
            }
        }
        logger.info("Got the Zip File");
        byte[] buffer = new byte[2048];

        try {
            // now iterate through each item in the stream. The get next
            // entry call will return a ZipEntry for each file in the
            // stream
            ZipEntry entry;
            logger.info("Extracting the Zip File");
            dirNames = new HashMap<String, String>();
            while ((entry = stream.getNextEntry()) != null) {

                // Once we get the entry from the stream, the stream is
                // positioned read to read the raw data, and we keep
                // reading until read returns 0 or less.
                if (entry.getName().contains("txt")) {
                    String outpath = DIR_PATH + entry.getName();
                    logger.info("outpath" + outpath);
                    dirNames.put(entry.getName(), outpath);
                    FileOutputStream output = null;
                    try {
                        output = new FileOutputStream(outpath);
                        int len;
                        while ((len = stream.read(buffer)) > 0) {
                            output.write(buffer, 0, len);
                        }
                    } finally {
                        // we must always close the output file
                        if (output != null) output.close();
                    }
                }
            }
            logger.debug("Completed the extraction of the Zip File");
        } finally {
            // we must always close the zip file.
            stream.close();
        }

        InfoHandler insert = new InfoHandler();

        for (String fileName : dirNames.keySet()) {
            insert.insertData(fileName, dirNames.get(fileName));
        }
        logger.debug("End of the method readContents()");
    }

    /**
     * Initializes dictionary: loads a set of the dictionary words.
     */
    private void initDictionary() {
        logger.debug("Getting dictionary words");
        Dictionary d = new Dictionary(DBConnectionManager.dictionaryConnection());
        dictionaryWords = d.getDictionaryWords();
    }

    /**
     * Returns a set of the dictionary words.
     *
     * @return a set of the dictionary words
     */
    public static Set<String> getDictionaryWords() {
        return dictionaryWords;
    }

    /**
     * Initializes normalization tables, tests if they are exist and rebuilds
     * them if needed.
     *
     * @param isRebuildTaxonomy if <code>false</code>, table <code>TAXONOMY_SYN_NORM</code>
     *                          will not be rebuilt if it exists and normalized (normally,
     *                          this table should be always rebuilt on each run of the Multum
     *                          updates)
     */
    private void initNormalization(boolean isRebuildTaxonomy) {
        NormalizationTable n = new NormalizationTable(DBConnectionManager.taxonomyConnection());
        n.setRebuildTaxonomy(isRebuildTaxonomy);
        n.normalize();

    }
}
