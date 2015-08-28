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
public class QPEChecker {
    static Logger logger = Logger.getLogger(QPEChecker.class);

    public static void main(String args[]) {
        verifyDrugs();
    }

    public static void verifyDrugs() {
        FDAProperties.setProperties();
        PreparedStatement ps = null;
        ResultSet rs;
        String sql = "select distinct brand_name from INF_RXTERMS where brand_name is not null";
        LinkedHashSet rscObj = DBConnectionManager.getResources(sql);
        int count = 0;
        final int batchSize = 500;
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        PreparedStatement updPs = null;
        String updSql = "update INF_RXTERMS set b_concept_id = :1 where brand_name= :2";

        LinkedHashSet updRscObj = DBConnectionManager.getResources(updSql);
        itr = updRscObj.iterator();
        if (itr.hasNext()) {
            updPs = (PreparedStatement) itr.next();
        }

        try {
            String drugName;
            rs = ps.executeQuery();

            StringBuffer strBuff;
            while (rs.next()) {
                drugName = rs.getString(1);
                strBuff = new StringBuffer();
                String temp =null;
                try{
                        temp= URLEncoder.encode(URLEncoder.encode(drugName));
                }catch (Exception e)
                {
                    System.out.println("e    "+e);
                }
                //System.out.println("temp   "+temp);
                strBuff.append("http://sfo-devqpe01:8080/qpe/api?method=AnalyzeQuery&query=").append(temp);
                //System.out.println(strBuff.toString());
                String response = callURL(strBuff.toString());
                System.out.println(response);
                if (response != null && !("".equals(response))) {
                    JSONObject jsonResult = new JSONObject(response);
                    boolean matched = jsonResult.getBoolean("full_match");
                    if (matched) {
                        JSONObject healthConcepts = jsonResult.getJSONObject("health_concepts");
                        if (healthConcepts != null) {
                            JSONArray imuiIdsArr = jsonResult.getJSONObject("health_concepts").names();
                            if (imuiIdsArr != null) {
                                for (int i = 0; i < imuiIdsArr.length(); i++) {
                                    String conceptID = imuiIdsArr.get(i).toString();
                                    String semanticCode = healthConcepts.getJSONObject(conceptID).getString("stys").toUpperCase();
                                    System.out.println("semanticCode  " + semanticCode);
                                    if (semanticCode.contains("L034")) {
                                        System.out.println("Hurray"+conceptID+" "+drugName);
                                        updPs.setString(1, conceptID);
                                        updPs.setString(2, drugName);
                                        updPs.executeUpdate();
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
