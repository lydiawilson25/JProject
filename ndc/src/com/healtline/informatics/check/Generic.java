package com.healtline.informatics.check;

import com.healtline.informatics.db.DBConnectionManager;
import com.healtline.informatics.utils.FDAProperties;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 11/7/14
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Generic {
    public static void main(String args[]){
        FDAProperties.setProperties();
        PreparedStatement ps = null;
        PreparedStatement inrPs = null;
        ResultSet rs;
        String[] strlen = {"intravenous","combination pack","foam", "talbets","tablets","irrigating","systems","tabelts","capsules","allergy tablets","topical","scalp solution","pm capsules","extended-release","sustained release","rapidly","modified release","modified-release","sustained-release","extended release","delayed-release","delayed release","pumpspray","injectable","sprinkle", "kit for","nasal ","for injection","am","pm","liqui-gels","congestion relief","ophthalmic","vaginal","chewable","for oral","injection","inhalation","orally","nasal solution","oral","powder for solution","for injection","inhalation","suspension", "emulsion", "kit", "insert", "enema", "crystal",  "intrauterine device", "patch", "suspension/ drops", "dressing", "poultice", "lotion/shampoo", "plaster", "irrigant", "solution", "capsule", "syrup", "powder", "cloth", "oil", "elixir", "concentrate", "sponge", "globule", "spray", "injection", "gel", "suppository", "stick", "strip", "shampoo", "lozenge", "lipstick", "tape", "gas", "rinse", "inhalant", "film", "salve", "extract", "tablet", "cream", "paste", "soap", "disc", "for solution", "ring", "liquid", "swab", "jelly", "douche", "implant", "pellet", "tincture", "aerosol", "pill", "wafer", "ear drops","cellular sheet", "caplets","system","lotion", "solution/ drops", "ointment", "for suspension", "granule", "mouthwash", "liniment", "pastille"};
        String sql = "select distinct generic_drug_name from INF_NDC_DRUGS where generic_drug_name is not null";
        //String sql = "select distinct brand_name from INF_NDC_DRUGS where brand_name is not null";// and brand_name = 'DEXEDRINE ORAL SOLUTION (ELIXIR)'";
        LinkedHashSet rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }

        String inrSql = "update INF_NDC_DRUGS set generic_name = :1 where generic_drug_name = :2";
        //String inrSql = "update INF_NDC_DRUGS set brand_name_actual = :1 where brand_name = :2";
        LinkedHashSet updRscObj = DBConnectionManager.getResources(inrSql);
        itr = updRscObj.iterator();
        if (itr.hasNext()) {
            inrPs = (PreparedStatement) itr.next();
        }

        try {
            rs = ps.executeQuery();

            while (rs.next()) {                                                //   CHILDREN'S ZYRTEC (ALLERGY) AND (HIVES-RELIEF
                String oldGenerics = rs.getString(1);
                String generics = oldGenerics;
                generics.replace(","," ");
                if(generics.contains("("))
                    generics = generics.substring(0, generics.indexOf("("));
                System.out.println("generics:"+generics);
                boolean modified = false;
                int previousIndex = 0;
                for(String str: strlen){
                    String regex = "\\b"+str+"\\b";
                    System.out.println("regex:"+regex);
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(generics.toLowerCase());

                    while(matcher.find() == true){
                        int start = matcher.start();
                        System.out.println("start:"+start);
                        if(previousIndex == 0) {
                            previousIndex = start;
                        } else if (start < previousIndex){
                            previousIndex = start;
                        }

                    }


/*
                    if(generics.toLowerCase().contains(str)){
                        String name = generics.toLowerCase().substring(0,generics.toLowerCase().indexOf(str));
                        System.out.println("name:"+name);
                        if(name.toLowerCase().contains("tablets"))
                            name = name.substring(0,generics.toLowerCase().indexOf("tablets"));
                        else if(name.toLowerCase().contains("for"))
                            name = name.substring(0,generics.toLowerCase().indexOf("for"));
                        else if(name.toLowerCase().contains("extended-release"))
                            name = name.substring(0,generics.toLowerCase().indexOf("extended-release"));
                        else if(name.toLowerCase().contains("powder"))
                            name = name.substring(0,generics.toLowerCase().indexOf("powder"));
                        else if(name.toLowerCase().contains("("))
                            name = name.substring(0,generics.toLowerCase().indexOf("("));
                        //System.out.println();
                        if(name != null && !("".equalsIgnoreCase(name))){
                        inrPs.setString(1,name);
                        inrPs.setString(2,generics);
                        inrPs.executeUpdate();

                        } else {
                            inrPs.setString(1,generics);
                            inrPs.setString(2,generics);
                            inrPs.executeUpdate();
                        }
                        modified = true;
                        break;
                    }*/
                }
                if(previousIndex != 0){
                    System.out.println(generics.toLowerCase().substring(0,previousIndex));
                    inrPs.setString(1,generics.toLowerCase().substring(0,previousIndex));
                    inrPs.setString(2,oldGenerics);
                    inrPs.executeUpdate();
                } else {
                    inrPs.setString(1,generics);
                    inrPs.setString(2,oldGenerics);
                    inrPs.executeUpdate();
                }

/*                if(!modified){

                        inrPs.setString(1,generics);
                        inrPs.setString(2,generics);
                        inrPs.executeUpdate();

                }*/
            }
            rs.close();
            DBConnectionManager.releaseResources(updRscObj);
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException e) {
            System.out.println("The exception is: " + e.getMessage());
        }
            }
}
