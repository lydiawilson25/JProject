package com.healtline.informatics.db;

import com.healtline.informatics.entities.Packages;
import com.healtline.informatics.entities.Products;
import com.healtline.informatics.normalization.MultumStemmer;
import com.healtline.informatics.utils.FDAProperties;
import com.healtline.informatics.utils.ResourceUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: wstephen
 * Date: 9/26/14
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class InfoHandler {
    static Logger logger = Logger.getLogger(InfoHandler.class);

    public void deleteTmpTable() {
        logger.debug("Start of the method deleteNDCTmpTable()");
        PreparedStatement ps = null;
        LinkedHashSet rscObj;

        for (SQLEnum tableEnum : SQLEnum.values()) {

            rscObj = DBConnectionManager.getResources(tableEnum.getDeleteTableSql());
            Iterator itr = rscObj.iterator();
            if (itr.hasNext()) {
                ps = (PreparedStatement) itr.next();
            }
            try {
                ps.executeUpdate();
            } catch (SQLException se) {
                logger.error("The SQLexception is:" + se);
            }
            try {
                DBConnectionManager.releaseResources(rscObj);
            } catch (Exception e) {
                logger.error("The exception is:" + e);
            }
        }
        logger.debug("End of the method deleteNDCTmpTable()");
    }

    public void insertData(String fileName, String filePath) throws IOException {
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

        if (FDAProperties.constantsProp.getProperty("FILE1").equalsIgnoreCase(fileName)) {
            sql = FDAProperties.constantsProp.getProperty("SQL1");

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
                        else {
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
                    ps.addBatch();

                    if (++count % batchSize == 0) {
                        ps.executeBatch();
                    }
                }
                ps.executeBatch(); // insert remaining records

            } catch (SQLException se) {
                logger.error("The INF_FDA_PRODUCT SQL exception is:" + se);
            } catch (ParseException e) {
                logger.error("The INF_FDA_PRODUCT Parse exception is:" + e);
            }

        } else if (FDAProperties.constantsProp.getProperty("FILE2").equalsIgnoreCase(fileName)) {
            sql = FDAProperties.constantsProp.getProperty("SQL2");

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

                    ps.addBatch();

                    if (++count % batchSize == 0) {
                        ps.executeBatch();
                    }
                }
                ps.executeBatch(); // insert remaining records

            } catch (SQLException se) {
                logger.error("The INF_FDA_PRODUCT_TECODE SQL exception is:" + se);
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

    public void comparePrdTblState() {
        logger.debug("Start of the method compareTblState()");
        LinkedHashMap<String, List> differences = new LinkedHashMap<String, List>();

        PreparedStatement ps = null;
        ResultSet rs;
        LinkedHashSet rscObj;
        String sql = FDAProperties.constantsProp.getProperty("DIFF_PRD");
        rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        try {
            List<Products> value = new ArrayList<Products>();
            String previousKey = null;
            Products diffInfo;
            rs = ps.executeQuery();
            while (rs.next()) {
                String key = rs.getString(2);
                logger.debug(key);

                diffInfo = new Products();
                diffInfo.setProductID((rs.getString(1) == null) ? "" : rs.getString(1));
                diffInfo.setProductNdc((rs.getString(2) == null) ? "" : rs.getString(2));
                diffInfo.setProductTypeName((rs.getString(3) == null) ? "" : rs.getString(3));
                diffInfo.setProprietaryName((rs.getString(4) == null) ? "" : rs.getString(4));
                diffInfo.setProprietaryNameSuffix((rs.getString(5) == null) ? "" : rs.getString(5));
                diffInfo.setNonProprietaryName((rs.getString(6) == null) ? "" : rs.getString(6));
                diffInfo.setDosageFormName((rs.getString(7) == null) ? "" : rs.getString(7));
                diffInfo.setRouteName((rs.getString(8) == null) ? "" : rs.getString(8));
                diffInfo.setStartMarketingDate((rs.getDate(9) == null) ? null : rs.getDate(9));
                diffInfo.setEndMarketingDate((rs.getDate(10) == null) ? null : rs.getDate(10));
                diffInfo.setMarketingCategoryName((rs.getString(11) == null) ? "" : rs.getString(11));
                diffInfo.setApplNo((rs.getString(12) == null) ? "" : rs.getString(12));
                diffInfo.setLabellerName((rs.getString(13) == null) ? "" : rs.getString(13));
                diffInfo.setSubstanceName((rs.getString(14) == null) ? "" : rs.getString(14));
                diffInfo.setActiveNumeratorStrength((rs.getString(15) == null) ? "" : rs.getString(15));
                diffInfo.setActiveIngredUnit((rs.getString(16) == null) ? "" : rs.getString(16));
                diffInfo.setPharmClasses((rs.getString(17) == null) ? "" : rs.getString(17));
                diffInfo.setDeaSchedule((rs.getString(18) == null) ? "" : rs.getString(18));
                diffInfo.setFoundNdcProdTmp(rs.getInt(19));
                diffInfo.setFoundNdcProd(rs.getInt(20));

                if (previousKey == null || previousKey.equalsIgnoreCase(key)) {
                    value.add(diffInfo);
                } else {
                    if (value.size() == 2) {
                        for (Products info : value) {
                            if (info.getFoundNdcProdTmp() == 1) {
                                logger.info("Update" + info.toString());
                                updateProducts(info);
                            }
                        }
                    } else if (value.size() == 1) {
                        logger.info("Insert$$" + value.get(0).toString());
                        insertProducts(value.get(0));
                    }
                    value = new ArrayList<Products>();
                    value.add(diffInfo);
                }
                previousKey = key;
            }
            if (value.size() == 2) {
                for (Products info : value) {
                    if (info.getFoundNdcProdTmp() == 1) {
                        logger.info("Update" + info.toString());
                        updateProducts(info);
                    }
                }
            } else if (value.size() == 1) {
                logger.info("Insert" + value.get(0).toString());
                insertProducts(value.get(0));
            }
            logger.info(differences.toString());

        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        try {
            DBConnectionManager.releaseResources(rscObj);
        } catch (Exception e) {
            logger.error("The exception is:" + e);
        }
        logger.debug("End of the method compareTblState()");
    }

    public void comparePkgTblState() {
        logger.debug("Start of the method comparePkgTblState()");
        LinkedHashMap<String, List> differences = new LinkedHashMap<String, List>();

        PreparedStatement ps = null;
        ResultSet rs;
        LinkedHashSet rscObj;
        String sql = FDAProperties.constantsProp.getProperty("DIFF_PKG");
        rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        try {
            List<Packages> value = new ArrayList<Packages>();
            String previousKey = null;
            Packages diffInfo;
            rs = ps.executeQuery();
            while (rs.next()) {
                String key = rs.getString(3);
                diffInfo = new Packages();
                diffInfo.setProductID((rs.getString(1) == null) ? "" : rs.getString(1));
                diffInfo.setProductNdc((rs.getString(2) == null) ? "" : rs.getString(2));
                diffInfo.setNdcPkgCode((rs.getString(3) == null) ? "" : rs.getString(3));
                diffInfo.setPkgDesc((rs.getString(4) == null) ? "" : rs.getString(4));
                diffInfo.setFoundNdcPkgTmp(rs.getInt(5));
                diffInfo.setFoundNdcPkg(rs.getInt(6));

                if (previousKey == null || previousKey.equalsIgnoreCase(key)) {
                    value.add(diffInfo);
                } else {
                    if (value.size() == 2) {
                        for (Packages info : value) {
                            if (info.getFoundNdcPkgTmp() == 1) {
                                logger.info("UpdatePkg" + info.toString());
                                updatePackages(info);
                            }
                        }
                    } else if (value.size() == 1) {
                        logger.info("InsertPkg" + value.get(0).toString());
                        insertPackages(value.get(0));
                    }
                    value = new ArrayList<Packages>();
                    value.add(diffInfo);
                }
                previousKey = key;
            }
            if (value.size() == 2) {
                for (Packages info : value) {
                    if (info.getFoundNdcPkgTmp() == 1) {
                        logger.info("UpdatePkg" + info.toString());
                        updatePackages(info);
                    }
                }
            } else if (value.size() == 1) {
                logger.info("InsertPkg" + value.get(0).toString());
                insertPackages(value.get(0));
            }
            logger.info(differences.toString());

        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        try {
            DBConnectionManager.releaseResources(rscObj);
        } catch (Exception e) {
            logger.error("The exception is:" + e);
        }
        logger.debug("End of the method comparePkgTblState()");
    }

    public void insertProducts(Products info) {
        logger.debug("Start of the method insertProducts()");
        PreparedStatement ps = null;
        LinkedHashSet rscObj;
        String sql;
        try {
            if (info.getFoundNdcProd() != 1) {
                sql = FDAProperties.constantsProp.getProperty("SQL4");
                rscObj = DBConnectionManager.getResources(sql);
                Iterator itr = rscObj.iterator();
                if (itr.hasNext()) {
                    ps = (PreparedStatement) itr.next();
                }
                ps.setString(1, info.getProductID());
                ps.setString(2, info.getProductNdc());
                ps.setString(3, info.getProductTypeName());
                ps.setString(4, info.getProprietaryName());
                ps.setString(5, info.getProprietaryNameSuffix());
                ps.setString(6, info.getNonProprietaryName());
                ps.setString(7, info.getDosageFormName());
                ps.setString(8, info.getRouteName());
                if (info.getStartMarketingDate() != null)
                    ps.setDate(9, new java.sql.Date(info.getStartMarketingDate().getTime()));
                else
                    ps.setDate(9, null);
                if (info.getEndMarketingDate() != null)
                    ps.setDate(10, new java.sql.Date(info.getEndMarketingDate().getTime()));
                else
                    ps.setDate(10, null);
                ps.setString(11, info.getMarketingCategoryName());
                ps.setString(12, info.getApplNo());
                ps.setString(13, info.getLabellerName());
                ps.setString(14, info.getSubstanceName());
                ps.setString(15, info.getActiveNumeratorStrength());
                ps.setString(16, info.getActiveIngredUnit());
                ps.setString(17, info.getPharmClasses());
                ps.setString(18, info.getDeaSchedule());
                ps.setString(19, "NEW");
                ps.execute();
            } else {
                sql = FDAProperties.constantsProp.getProperty("SQL6");
                rscObj = DBConnectionManager.getResources(sql);
                Iterator itr = rscObj.iterator();
                if (itr.hasNext()) {
                    ps = (PreparedStatement) itr.next();
                }
                ps.setString(1, info.getProductID());
                ps.setString(2, info.getProductNdc());
                ps.executeUpdate();
            }
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        logger.debug("End of the method insertProducts()");
    }

    public void updateProducts(Products info) {
        logger.debug("Start of the method updateProducts()");
        PreparedStatement ps = null;
        LinkedHashSet rscObj;
        String sql = FDAProperties.constantsProp.getProperty("SQL5");
        rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        try {
            ps.setString(1, info.getProductID());
            ps.setString(2, info.getProductTypeName());
            ps.setString(3, info.getProprietaryName());
            ps.setString(4, info.getProprietaryNameSuffix());
            ps.setString(5, info.getNonProprietaryName());
            ps.setString(6, info.getDosageFormName());
            ps.setString(7, info.getRouteName());
            if (info.getStartMarketingDate() != null)
                ps.setDate(8, new java.sql.Date(info.getStartMarketingDate().getTime()));
            else
                ps.setDate(8, null);
            if (info.getEndMarketingDate() != null)
                ps.setDate(9, new java.sql.Date(info.getEndMarketingDate().getTime()));
            else
                ps.setDate(9, null);
            ps.setString(10, info.getMarketingCategoryName());
            ps.setString(11, info.getApplNo());
            ps.setString(12, info.getLabellerName());
            ps.setString(13, info.getSubstanceName());
            ps.setString(14, info.getActiveNumeratorStrength());
            ps.setString(15, info.getActiveIngredUnit());
            ps.setString(16, info.getPharmClasses());
            ps.setString(17, info.getDeaSchedule());
            ps.setString(18, "UPDATE");
            ps.setString(19, info.getProductNdc());
            ps.executeUpdate();
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        logger.debug("End of the method updateProducts()");
    }

    public void insertPackages(Packages info) {
        logger.debug("Start of the method insertPackages()");
        PreparedStatement ps = null;
        LinkedHashSet rscObj;
        String sql;
        try {
            if (info.getFoundNdcPkg() != 1) {
                sql = FDAProperties.constantsProp.getProperty("INSERT_PKG");
                rscObj = DBConnectionManager.getResources(sql);
                Iterator itr = rscObj.iterator();
                if (itr.hasNext()) {
                    ps = (PreparedStatement) itr.next();
                }
                ps.setString(1, info.getProductID());
                ps.setString(2, info.getProductNdc());
                ps.setString(3, info.getNdcPkgCode());
                ps.setString(4, info.getPkgDesc());
                ps.setString(5, "NEW");
                ps.execute();
            } else {
                sql = FDAProperties.constantsProp.getProperty("MARK_DELETE_PKG");
                rscObj = DBConnectionManager.getResources(sql);
                Iterator itr = rscObj.iterator();
                if (itr.hasNext()) {
                    ps = (PreparedStatement) itr.next();
                }
                ps.setString(1, info.getProductID());
                ps.setString(2, info.getNdcPkgCode());
                ps.executeUpdate();
            }
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        logger.debug("End of the method insertPackages()");
    }

    public void updatePackages(Packages info) {
        logger.debug("Start of the method updatePackages()");
        PreparedStatement ps = null;
        LinkedHashSet rscObj;
        String sql = FDAProperties.constantsProp.getProperty("UPDATE_PKG");
        rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        try {
            ps.setString(1, info.getProductID());
            ps.setString(2, info.getProductNdc());
            ps.setString(3, info.getPkgDesc());
            ps.setString(4, "UPDATE");
            ps.setString(5, info.getNdcPkgCode());
            ps.executeUpdate();
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        logger.debug("End of the method updatePackages()");
    }

    public void taxonomyChecker() {
        logger.info("Start of the method taxonomyCheck()");

        PreparedStatement ps = null;
        ResultSet rs;
        LinkedHashSet rscObj;
        String sql = FDAProperties.constantsProp.getProperty("SQL21");
        rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        try {
            rs = ps.executeQuery();
            //while (rs.next()) {
            String drugName = "ACETYLCYSTEINE SOLUTION,INHL 10%";//rs.getString(1);
            logger.debug("The drug name is :" + drugName);
            String str = MultumStemmer.normalizeString(drugName);
            logger.debug("The norm drug name is :" + str);
            String output = verifyConcepts(str);
            logger.debug(" output " + output);
            if (output != null) {
                String[] tokens = output.split(" ");
                int concept_id = Integer.parseInt(tokens[0]);
                int sty_id = Integer.parseInt(tokens[1]);
                if (sty_id == 7850118 || sty_id == 7850119) {
                    String sql1 = FDAProperties.constantsProp.getProperty("SQL23");
                    PreparedStatement ps1 = null;
                    LinkedHashSet rscObj1 = DBConnectionManager.getResources(sql1);
                    Iterator itr1 = rscObj1.iterator();
                    if (itr1.hasNext()) {
                        ps1 = (PreparedStatement) itr1.next();
                    }
                    try {
                        ps1.setInt(1, concept_id);
                        ps1.setString(2, "Y");
                        ps1.setString(3, drugName);
                        ps1.executeUpdate();
                    } catch (SQLException se) {
                        logger.error("The SQLexception is:" + se);
                    }
                    try {
                        DBConnectionManager.releaseResources(rscObj1);
                    } catch (Exception e) {
                        logger.error("The exception is:" + e);
                    }

                }
            }
            //}
        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        try {
            DBConnectionManager.releaseResources(rscObj);
        } catch (Exception e) {
            logger.error("The exception is:" + e);
        }
        logger.info("End of the method taxonomyCheck()");


    }

    public String verifyConcepts(String str) {
        logger.info("Start of the method verifyConcepts()");

        PreparedStatement ps = null;
        ResultSet rs;
        LinkedHashSet rscObj;
        String sql = FDAProperties.constantsProp.getProperty("SQL22");
        StringBuffer buff;
        rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        String output = null;
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        try {
            ps.setString(1, str);
            rs = ps.executeQuery();
            while (rs.next()) {
                output = rs.getString(1) + " " + rs.getString(2);
            }
        } catch (SQLException se) {
            logger.error("The SQLexception is:" + se);
        }
        try {
            DBConnectionManager.releaseResources(rscObj);
        } catch (Exception e) {
            logger.error("The exception is:" + e);
        }
        logger.info("End of the method verifyConcepts()");

        return output;
    }

    public enum SQLEnum {

        INF_NDC_PRD_TMP, INF_NDC_PKG_TMP;

        /**
         * Returns "delete table ..." SQL code
         */
        String getDeleteTableSql() {
            return "delete from " + this;
        }

    }

}