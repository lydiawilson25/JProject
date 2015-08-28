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
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/13/14
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class QPECheckerActive {
    static Logger logger = Logger.getLogger(QPEChecker.class);

    public static void main(String args[]) {
        verifyDrugs();

    }

    public static void verifyDrugs() {
        FDAProperties.setProperties();
        PreparedStatement ps = null;
        ResultSet rs;
        String sql = FDAProperties.constantsProp.getProperty("QPEACTIVE");
        LinkedHashSet rscObj = DBConnectionManager.getResources(sql);
        int count = 0;
        final int batchSize = 500;
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        PreparedStatement updPs = null;
        String updSql = FDAProperties.constantsProp.getProperty("QPEACTIVEUPD");

        LinkedHashSet updRscObj = DBConnectionManager.getResources(updSql);
        itr = updRscObj.iterator();
        if (itr.hasNext()) {
            updPs = (PreparedStatement) itr.next();
        }

        try {
            String drugName,stri = null;
            rs = ps.executeQuery();

            StringBuffer strBuff;
            while (rs.next()) {
                drugName = rs.getString(1);
                stri =  rs.getString(1);
                System.out.println(drugName);
                //String[] combinations = drugName.split(";");
                stri = stri.replace(";", " ");
                String result="";
                //for(String str1: combinations){

                strBuff = new StringBuffer();
                System.out.println(stri);
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
                    String output = "";
                if (response != null && !("".equals(response))) {
                    JSONObject jsonResult = new JSONObject(response);
                    boolean matched = jsonResult.getBoolean("full_match");
                    if (matched) {
                        JSONObject healthConcepts = jsonResult.getJSONObject("health_concepts");
                        if (healthConcepts != null) {
                            JSONArray imuiIdsArr = jsonResult.getJSONObject("health_concepts").names();
                            if (imuiIdsArr != null) {
                                HashMap<String,String> obj = new HashMap<String, String>();
                                boolean   pharma = false;
                                boolean   brand = false;
                                boolean   generic = false;
                                for (int i = 0; i < imuiIdsArr.length(); i++) {
                                    String conceptID = imuiIdsArr.get(i).toString();
                                    String semanticCode = healthConcepts.getJSONObject(conceptID).getString("stys").toUpperCase();
                                    if (semanticCode.contains("T121") ) {
                                        pharma = true;
                                        obj.put("T121", conceptID);
                                    }
                                    if (semanticCode.contains("L034") ) {
                                        brand = true;
                                        obj.put("L034", conceptID);
                                    }
                                    if (semanticCode.contains("L035") ) {
                                        generic = true;
                                        obj.put("L035", conceptID);
                                    }
                                    if(!(semanticCode.contains("T121")) && !(semanticCode.contains("L034")) && !(semanticCode.contains("L035")) )
                                        System.out.println(drugName);
                                }

                                if (generic) {
                                    //System.out.println("generic");
//                                    updPs.setString(1, obj.get("L035"));
//                                    updPs.setString(2, "G");
//                                    updPs.setString(3, drugName);
//                                    updPs.executeUpdate();
                                    output =  obj.get("L035") + " (G)";
                                } else {
                                    if (brand ) {
                                       // System.out.println("brand");
//                                        updPs.setString(1, obj.get("L034"));
//                                        updPs.setString(2, "B");
//                                        updPs.setString(3, drugName);
//                                        updPs.executeUpdate();
                                        output =  obj.get("L034") + " (B)";
                                    } else {
                                        if(pharma) {
                                         //   System.out.println("pharma");

                                            output =  obj.get("T121") + " (P)";
                                        }else {
                                            output = "NANA";
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        output = "NANA";
                    }
                }
                result = result + output + ";";
            //}
                result = result.substring(0, result.lastIndexOf(";"));
                System.out.println("Hurray:"+result);
                updPs.setString(1, result);
                updPs.setString(2, "Y");
                updPs.setString(3, drugName);
                updPs.executeUpdate();
            }
            rs.close();
            DBConnectionManager.releaseResources(updRscObj);
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException e) {
            System.out.println("The exception is: " + e.getMessage());
        }
    }


    public static String callURL(String myURL) {
        //System.out.println("Requeted URL:" + myURL);
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
