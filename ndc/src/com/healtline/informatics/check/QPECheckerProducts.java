package com.healtline.informatics.check;

import com.healtline.informatics.db.DBConnectionManager;
import com.healtline.informatics.utils.FDAProperties;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/13/14
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class QPECheckerProducts {
    static Logger logger = Logger.getLogger(QPEChecker.class);

    public static void main(String args[]) {
        verifyDrugs();

    }

    public static void verifyDrugs() {

        FDAProperties.setProperties();
        PreparedStatement ps = null;
        ResultSet rs;
        String sql = FDAProperties.constantsProp.getProperty("QPEPRO");
        LinkedHashSet rscObj = DBConnectionManager.getResources(sql);
        int count = 0;
        final int batchSize = 500;
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        PreparedStatement updPs = null;
        String updSql = FDAProperties.constantsProp.getProperty("QPEPROUPD");

        LinkedHashSet updRscObj = DBConnectionManager.getResources(updSql);
        itr = updRscObj.iterator();
        if (itr.hasNext()) {
            updPs = (PreparedStatement) itr.next();
        }

        try {

            String propName, propNamesuffix, dosage,routeName, strength, unit,stri;
            rs = ps.executeQuery();

            StringBuffer strBuff;
            while (rs.next()) {
                System.out.println("*****************");
                propName = rs.getString(1);
                propNamesuffix = rs.getString(2);
                dosage = rs.getString(3);
                routeName = rs.getString(4);
                strength = rs.getString(5);
                unit = rs.getString(6);
                System.out.println(propName+propNamesuffix+dosage+routeName+strength+unit);
                stri = ((propName == null)?"":propName)+" "+((propNamesuffix == null)?"":propNamesuffix)+" "+((dosage == null)?"":dosage)+" "+((routeName == null)?"":routeName)+" "+((strength == null)?"":strength)+" "+((unit == null)?"":unit);
                stri = stri.replace(";", " ");
                strBuff = new StringBuffer();
                System.out.println("changed"+stri);
                String temp =null;
                try{
                    temp= URLEncoder.encode(URLEncoder.encode(stri));
                }catch (Exception e)
                {
                    System.out.println("e    "+e);
                }
                //System.out.println("temp   "+temp);
                strBuff.append(FDAProperties.constantsProp.getProperty("QPE_URL")).append(temp);
                String response = callURL(strBuff.toString());
                if (response != null && !("".equals(response))) {
                    JSONObject jsonResult = new JSONObject(response);
                    boolean matched = jsonResult.getBoolean("full_match");
                    if (matched) {
                        JSONObject healthConcepts = jsonResult.getJSONObject("health_concepts");
                        if (healthConcepts != null) {
                            JSONArray imuiIdsArr = jsonResult.getJSONObject("health_concepts").names();
                            if (imuiIdsArr != null) {
                                int updateCount = 0;
                                for (int i = 0; i < imuiIdsArr.length(); i++) {
                                    String conceptID = imuiIdsArr.get(i).toString();
                                    String semanticCode = healthConcepts.getJSONObject(conceptID).getString("stys").toUpperCase();
                                    System.out.println("semanticCode  " + semanticCode);
                                    if (semanticCode.contains("T200")) {
                                        System.out.println("Hurray");
                                        System.out.println(conceptID+propName+propNamesuffix+dosage+routeName+strength+unit);
                                        updPs.setString(1, conceptID.toLowerCase());
                                        updPs.setString(2, ((propName == null)?null:propName.toLowerCase()));
                                        updPs.setString(3, ((propNamesuffix == null || "".equalsIgnoreCase(propNamesuffix))?null:propNamesuffix.toLowerCase()));
                                        updPs.setString(4, ((dosage == null)?null:dosage.toLowerCase()));
                                        updPs.setString(5, ((routeName == null)?null:routeName.toLowerCase()));
                                        updPs.setString(6, ((strength == null)?null:strength.toLowerCase()));
                                        updPs.setString(7, ((unit == null)?null:unit.toLowerCase()));
                                        System.out.println("sdfsdf"+ updPs.executeUpdate());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            rs.close();
            DBConnectionManager.releaseResources(updRscObj);
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException e) {
            System.out.println("The exception is: " + e.getMessage());
        }
    }


    public static String callURL(String myURL) {
        System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }

        return sb.toString();
    }

}
