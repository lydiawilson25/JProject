package com.healtline.informatics.test;

import com.healtline.informatics.Controller;
import com.healtline.informatics.db.DBConnectionManager;
import com.healtline.informatics.normalization.MultumStemmer;
import com.healtline.informatics.utils.FDAProperties;
import com.healtline.informatics.utils.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/28/14
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Normalizer {
    public static void main(String args[]) throws IOException, SQLException {
        FDAProperties.setProperties();
        Controller d = new Controller();
        d.run();
        PreparedStatement ps = null;
        ResultSet rs;
        LinkedHashSet rscObj;
        String sql = "select distinct active_ingred from INF_NDC_AI_AM";
        //String sql = "SELECT DISTINCT ACTIVE_INGRED  FROM INF_FDA_PRODUCT_OCT22 WHERE  DRUG_STATUS In ('PRESCRIPTION','OTC')";
        //String sql = "SELECT distinct SUBSTANCE_NAME FROM INF_NDC_PRODUCT_CURRENT where mARKETING_CATEGORY_NAME not like '%UNAPPROVED%' and SUBSTANCE_NAME is not null ";
        rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
            String drugName =rs.getString(1);
            System.out.println("The drug name is :"+drugName);
            String str = MultumStemmer.normalizeString(drugName);
            System.out.println("The norm drug name is :" + str);

            if (str != null) {
                    String sql1 = "insert into norm2 values(:1,:2)";
                    //String sql1 = "insert into norm values(:1,:2)";
                    //String sql1 = "insert into norm1 values(:1,:2)";
                    PreparedStatement ps1 = null;
                    LinkedHashSet rscObj1 = DBConnectionManager.getResources(sql1);
                    Iterator itr1 = rscObj1.iterator();
                    if (itr1.hasNext()) {
                        ps1 = (PreparedStatement) itr1.next();
                    }
                    try {
                        ps1.setString(1, drugName);
                        ps1.setString(2, str);
                        ps1.executeUpdate();
                    } catch (SQLException se) {
                    }
                    try {
                        DBConnectionManager.releaseResources(rscObj1);
                    } catch (Exception e) {
                   }


            }
            }
        } catch (SQLException se) {

        }
        try {
            DBConnectionManager.releaseResources(rscObj);
        } catch (Exception e) {

        }


    }
}
