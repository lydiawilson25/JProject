package com.healtline.informatics.test;

import com.healtline.informatics.db.DBConnectionManager;
import com.healtline.informatics.utils.FDAProperties;
import com.healtline.informatics.utils.ResourceUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/10/14
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProductTempDataLoader {
    static Logger logger = Logger.getLogger(ProductTempDataLoader.class);
    public static void main(String args[]) throws IOException {
        DOMConfigurator.configure("log4j.xml");
        FDAProperties.setProperties();
        insertData("product.txt","/home/wstephen/Documents/Projects/FDA/NDC/ndc-29/product.txt");
        insertData("package.txt","/home/wstephen/Documents/Projects/FDA/NDC/ndc-29/package.txt");
    }

    public static void insertData(String fileName, String filePath) throws IOException {
        logger.debug("Start of the method insertData()");
        BufferedReader bReader = ResourceUtils.getReader(filePath);
        bReader.readLine();
        String line;
        int count = 0;
        final int batchSize = 1000;
        DateFormat formatter;
        Date date;

        PreparedStatement ps = null;
        LinkedHashSet rscObj = null;
        String sql;

        if ("product.txt".equalsIgnoreCase(fileName)) {
            sql = "INSERT INTO INF_NDC_PRD_CURRENT VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15, :16, :17, :18, :19, SYSDATE, 'DRUG AUTOMATION PROCESSOR', null, null)";

            rscObj = DBConnectionManager.getResources(sql);
            Iterator itr = rscObj.iterator();
            if (itr.hasNext()) {
                ps = (PreparedStatement) itr.next();
            }

            try {
                while ((line = bReader.readLine()) != null) {

                    String datavalue[] = line.split("\t");

                    int length = datavalue.length;
                    if (length >= 1) {
                        if (datavalue[0] != null && !("".equalsIgnoreCase(datavalue[0])))
                            ps.setString(1, datavalue[0]);
                        else
                            ps.setString(1, null);
                    } else
                        ps.setString(1, null);
                    if (length >= 2) {
                        if (datavalue[1] != null && !("".equalsIgnoreCase(datavalue[1])))
                            ps.setString(2, datavalue[1]);
                        else{
                            ps.setString(2, null);
                        }
                    } else {
                        ps.setString(2, null);
                    }
                    if (length >= 3) {
                        if (datavalue[2] != null && !("".equalsIgnoreCase(datavalue[2])))
                            ps.setString(3, datavalue[2]);
                        else
                            ps.setString(3, null);
                    } else
                        ps.setString(3, null);
                    if (length >= 4) {
                        if (datavalue[3] != null && !("".equalsIgnoreCase(datavalue[3])))
                            ps.setString(4, datavalue[3]);
                        else
                            ps.setString(4, null);
                    } else
                        ps.setString(4, null);
                    if (length >= 5) {
                        if (datavalue[4] != null && !("".equalsIgnoreCase(datavalue[4])))
                            ps.setString(5, datavalue[4]);
                        else
                            ps.setString(5, null);
                    } else
                        ps.setString(5, null);
                    if (length >= 6) {
                        if (datavalue[5] != null && !("".equalsIgnoreCase(datavalue[5])))
                            ps.setString(6, datavalue[5]);
                        else
                            ps.setString(6, null);
                    } else
                        ps.setString(6, null);
                    if (length >= 7) {
                        if (datavalue[6] != null && !("".equalsIgnoreCase(datavalue[6])))
                            ps.setString(7, datavalue[6]);
                        else
                            ps.setString(7, null);
                    } else
                        ps.setString(7, null);
                    if (length >= 8) {
                        if (datavalue[7] != null && !("".equalsIgnoreCase(datavalue[7])))
                            ps.setString(8, datavalue[7]);
                        else
                            ps.setString(8, null);
                    } else
                        ps.setString(8, null);
                    formatter = new SimpleDateFormat("yyyymmdd");
                    if (length >= 9) {
                        if (datavalue[8] != null && !("".equalsIgnoreCase(datavalue[8]))) {
                            date = formatter.parse(datavalue[8]);
                            ps.setDate(9, new java.sql.Date(date.getTime()));
                        } else
                            ps.setString(9, null);
                    } else
                        ps.setString(9, null);
                    if (length >= 10) {
                        if (datavalue[9] != null && !("".equalsIgnoreCase(datavalue[9]))) {
                            date = formatter.parse(datavalue[9]);
                            ps.setDate(10, new java.sql.Date(date.getTime()));
                        } else
                            ps.setString(10, null);
                    } else
                        ps.setString(10, null);

                    if (length >= 11) {
                        if (datavalue[10] != null && !("".equalsIgnoreCase(datavalue[10])))
                            ps.setString(11, datavalue[10]);
                        else
                            ps.setString(11, null);

                    } else
                        ps.setString(11, null);
                    if (length >= 12) {
                        if (datavalue[11] != null && !("".equalsIgnoreCase(datavalue[11])))
                            ps.setString(12, datavalue[11]);
                        else
                            ps.setString(12, null);
                    } else
                        ps.setString(12, null);
                    if (length >= 13) {
                        if (datavalue[12] != null && !("".equalsIgnoreCase(datavalue[12])))
                            ps.setString(13, datavalue[12]);
                        else
                            ps.setString(13, null);
                    } else
                        ps.setString(13, null);
                    if (length >= 14) {
                        if (datavalue[13] != null && !("".equalsIgnoreCase(datavalue[13])))
                            ps.setString(14, datavalue[13]);
                        else
                            ps.setString(14, null);
                    } else
                        ps.setString(14, null);
                    if (length >= 15) {
                        if (datavalue[14] != null && !("".equalsIgnoreCase(datavalue[14])))
                            ps.setString(15, datavalue[14]);
                        else
                            ps.setString(15, null);
                    } else
                        ps.setString(15, null);
                    if (length >= 16) {
                        if (datavalue[15] != null && !("".equalsIgnoreCase(datavalue[15])))
                            ps.setString(16, datavalue[15]);
                        else
                            ps.setString(16, null);
                    } else
                        ps.setString(16, null);
                    if (length >= 17) {
                        if (datavalue[16] != null && !("".equalsIgnoreCase(datavalue[16])))
                            ps.setString(17, datavalue[16]);
                        else
                            ps.setString(17, null);
                    } else
                        ps.setString(17, null);
                    if (length >= 18) {
                        if (datavalue[17] != null && !("".equalsIgnoreCase(datavalue[17])))
                            ps.setString(18, datavalue[17]);
                        else
                            ps.setString(18, null);
                    } else
                        ps.setString(18, null);
                    ps.setString(19, null);
                    ps.addBatch();

                    if (++count % batchSize == 0) {
                        ps.executeBatch();
                    }
                }
                ps.executeBatch(); // insert remaining records

            } catch (SQLException se) {
                logger.error("The INF_NDC_PRD_CURRENT SQL exception is:" + se);
            } catch (ParseException e) {
                logger.error("The INF_NDC_PRD_CURRENT Parse exception is:" + e);
            }

        } else if ("package.txt".equalsIgnoreCase(fileName)) {
            sql = "INSERT INTO INF_NDC_PKG_CURRENT VALUES (:1, :2, :3, :4, :5,SYSDATE, 'DRUG AUTOMATION PROCESSOR', null, null)";

            rscObj = DBConnectionManager.getResources(sql);
            Iterator itr = rscObj.iterator();
            if (itr.hasNext()) {
                ps = (PreparedStatement) itr.next();
            }

            try {
                while ((line = bReader.readLine()) != null) {

                    String datavalue[] = line.split("\t");
                    ps.setString(1, datavalue[0]);
                    ps.setString(2, datavalue[1]);
                    ps.setString(3, datavalue[2]);
                    ps.setString(4, datavalue[3]);
                    ps.setString(5, null);
                    ps.addBatch();

                    if (++count % batchSize == 0) {
                        ps.executeBatch();
                    }
                }
                ps.executeBatch(); // insert remaining records

            } catch (SQLException se) {
                logger.error("The INF_NDC_PKG_CURRENT SQL exception is:" + se);
            }
        }
        try {
            ps.executeBatch(); // insert remaining records
            DBConnectionManager.releaseResources(rscObj);
            bReader.close();
        } catch (SQLException se) {
            logger.error("The SQL exception is:" + se);
        } catch (Exception e) {
            logger.error("The exception is:" + e);
        }
        logger.debug("End of the method insertData()");
    }

}
