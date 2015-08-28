package com.healtline.informatics.check;

import com.healtline.informatics.db.DBConnectionManager;
import com.healtline.informatics.utils.FDAProperties;
import com.healtline.informatics.utils.ResourceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 11/5/14
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class OpenFDA {

    public static void main(String args[]) {
        long starttime = Calendar.getInstance().getTime().getTime();
        //System.out.println(starttime);
        FDAProperties.setProperties();
        PreparedStatement ps = null;
        PreparedStatement inrPs = null;
        ResultSet rs;
        int reqCount = 1;
        int count = 0;
        final int batchSize = 100;
        String sql = "SELECT DISTINCT PRODUCT_NDC FROM INF_NDC_PRD_CURRENT WHERE MARKETING_CATEGORY_NAME not like '%UNAPPROVED%' AND PRODUCT_NDC IS NOT NULL and product_ndc not in (select product_ndc from inf_open_fda_new)";
        LinkedHashSet rscObj = DBConnectionManager.getResources(sql);
        Iterator itr = rscObj.iterator();
        if (itr.hasNext()) {
            ps = (PreparedStatement) itr.next();
        }
        int drugCounter = 1;
        String inrSql = "INSERT INTO INF_OPEN_FDA_NEW VALUES (:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30,:31,:32,:33,:34,:35,:36,:37,:38,:39,:40,:41,:42,:43,:44,:45,:46,:47,:48,:49,:50,:51,:52,:53,:54,:55,:56,:57,:58)";
        LinkedHashSet updRscObj = DBConnectionManager.getResources(inrSql);
        itr = updRscObj.iterator();
        if (itr.hasNext()) {
            inrPs = (PreparedStatement) itr.next();
        }

        try {
            rs = ps.executeQuery();

            while (rs.next()) {
                String productNDC = rs.getString(1);
                System.out.println("=============================================================================================================================================");
                System.out.println("DrugCount is " + drugCounter + "   productNDC:" + productNDC);
                try {
                    String response = callURL("https://api.fda.gov/drug/label.json?api_key=twB3hlxtXFe30TaIhmA0CQVFhRZQh4q7asJddibR&search=openfda.product_ndc=%22" + productNDC + "%22");
                    if (response != null && !("".equals(response))) {

                        JSONObject jsonResult = new JSONObject(response);
                        //int total = jsonResult.getJSONObject("meta").getJSONObject("results").getInt("total");

                        //if (total == 1) {
                        JSONArray results = jsonResult.getJSONArray("results");
                        if (results != null && results.length() == 1) {
                            try {
                                JSONObject resultsArr = results.getJSONObject(0);

                                JSONArray indications_and_usage;
                                String indications_and_usage_val = "";
                                try {
                                    indications_and_usage = resultsArr.getJSONArray("indications_and_usage");
                                    for (int i = 0; i < indications_and_usage.length(); i++) {
                                        indications_and_usage_val = indications_and_usage_val + indications_and_usage.get(i).toString() + "#";
                                    }
                                    indications_and_usage_val = indications_and_usage_val.substring(0, indications_and_usage_val.lastIndexOf("#"));
                                    //System.out.println("indications_and_usage: "+indications_and_usage_val);
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }


                                JSONArray stop_use;
                                String stop_use_val = "";
                                try {
                                    stop_use = resultsArr.getJSONArray("stop_use");
                                    for (int i = 0; i < stop_use.length(); i++) {
                                        stop_use_val = stop_use_val + stop_use.get(i).toString() + "#";
                                    }
                                    stop_use_val = stop_use_val.substring(0, stop_use_val.lastIndexOf("#"));
                                    //System.out.println("stop_use: "+stop_use_val.substring(0, stop_use_val.lastIndexOf("#")));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }


                                JSONArray adverse_reactions;
                                String adverse_reactions_val = "";
                                try {
                                    adverse_reactions = resultsArr.getJSONArray("adverse_reactions");
                                    for (int i = 0; i < adverse_reactions.length(); i++) {
                                        adverse_reactions_val = adverse_reactions_val + adverse_reactions.get(i).toString() + "#";
                                    }
                                    adverse_reactions_val = adverse_reactions_val.substring(0, adverse_reactions_val.lastIndexOf("#"));
                                    //System.out.println("adverse_reactions: "+adverse_reactions_val.substring(0, adverse_reactions_val.lastIndexOf("#")));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }


                                JSONArray alarms;
                                String alarms_val = "";
                                try {
                                    alarms = resultsArr.getJSONArray("alarms");
                                    for (int i = 0; i < alarms.length(); i++) {
                                        alarms_val = alarms_val + alarms.get(i).toString() + "#";
                                    }
                                    alarms_val = alarms_val.substring(0, alarms_val.lastIndexOf("#"));
                                    //System.out.println("alarms: "+alarms_val.substring(0, alarms_val.lastIndexOf("#")));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }

                                JSONArray contraindications;
                                String contraindications_val = "";
                                try {
                                    contraindications = resultsArr.getJSONArray("contraindications");
                                    for (int i = 0; i < contraindications.length(); i++) {
                                        contraindications_val = contraindications_val + contraindications.get(i).toString() + "#";
                                    }
                                    contraindications_val = contraindications_val.substring(0, contraindications_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("contraindications: "+contraindications_val.substring(0, contraindications_val.lastIndexOf("#")));

                                JSONArray dosage_forms_and_strengths;
                                String dosage_forms_and_strengths_val = "";
                                try {
                                    dosage_forms_and_strengths = resultsArr.getJSONArray("dosage_forms_and_strengths");
                                    for (int i = 0; i < dosage_forms_and_strengths.length(); i++) {
                                        dosage_forms_and_strengths_val = dosage_forms_and_strengths_val + dosage_forms_and_strengths.get(i).toString() + "#";
                                    }
                                    dosage_forms_and_strengths_val = dosage_forms_and_strengths_val.substring(0, dosage_forms_and_strengths_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("dosage_forms_and_strengths: "+dosage_forms_and_strengths_val.substring(0, dosage_forms_and_strengths_val.lastIndexOf("#")));

                                JSONArray drug_abuse_and_dependence;
                                String drug_abuse_and_dependence_val = "";
                                try {
                                    drug_abuse_and_dependence = resultsArr.getJSONArray("drug_abuse_and_dependence");
                                    for (int i = 0; i < drug_abuse_and_dependence.length(); i++) {
                                        drug_abuse_and_dependence_val = drug_abuse_and_dependence_val + drug_abuse_and_dependence.get(i).toString() + "#";
                                    }
                                    drug_abuse_and_dependence_val = drug_abuse_and_dependence_val.substring(0, drug_abuse_and_dependence_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("drug_abuse_and_dependence: "+drug_abuse_and_dependence_val.substring(0, drug_abuse_and_dependence_val.lastIndexOf("#")));

                                JSONArray drug_interactions;
                                String drug_interactions_val = "";
                                try {
                                    drug_interactions = resultsArr.getJSONArray("drug_interactions");
                                    for (int i = 0; i < drug_interactions.length(); i++) {
                                        drug_interactions_val = drug_interactions_val + drug_interactions.get(i).toString() + "#";
                                    }
                                    drug_interactions_val = drug_interactions_val.substring(0, drug_interactions_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("drug_interactions: "+drug_interactions_val.substring(0, drug_interactions_val.lastIndexOf("#")));

                                JSONArray food_safety_warning;
                                String food_safety_warning_val = "";
                                try {
                                    food_safety_warning = resultsArr.getJSONArray("food_safety_warning");
                                    for (int i = 0; i < food_safety_warning.length(); i++) {
                                        food_safety_warning_val = food_safety_warning_val + food_safety_warning.get(i).toString() + "#";
                                    }
                                    food_safety_warning_val = food_safety_warning_val.substring(0, food_safety_warning_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("food_safety_warning: "+food_safety_warning_val.substring(0, food_safety_warning_val.lastIndexOf("#")));

                                JSONArray health_claim;
                                String health_claim_val = "";
                                try {
                                    health_claim = resultsArr.getJSONArray("health_claim");
                                    for (int i = 0; i < health_claim.length(); i++) {
                                        health_claim_val = health_claim_val + health_claim.get(i).toString() + "#";
                                    }
                                    health_claim_val = health_claim_val.substring(0, health_claim_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("health_claim: "+health_claim_val.substring(0, health_claim_val.lastIndexOf("#")));

                                JSONArray how_supplied;
                                String how_supplied_val = "";
                                try {
                                    how_supplied = resultsArr.getJSONArray("how_supplied");
                                    for (int i = 0; i < how_supplied.length(); i++) {
                                        how_supplied_val = how_supplied_val + how_supplied.get(i).toString() + "#";
                                    }
                                    how_supplied_val = how_supplied_val.substring(0, how_supplied_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("how_supplied: "+how_supplied_val.substring(0, how_supplied_val.lastIndexOf("#")));

                                JSONArray laboratory_tests;
                                String laboratory_tests_val = "";
                                try {
                                    laboratory_tests = resultsArr.getJSONArray("laboratory_tests");
                                    for (int i = 0; i < laboratory_tests.length(); i++) {
                                        laboratory_tests_val = laboratory_tests_val + laboratory_tests.get(i).toString() + "#";
                                    }
                                    laboratory_tests_val = laboratory_tests_val.substring(0, laboratory_tests_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("laboratory_tests: "+laboratory_tests_val.substring(0, laboratory_tests_val.lastIndexOf("#")));

                                JSONArray do_not_use;
                                String do_not_use_val = "";
                                try {
                                    do_not_use = resultsArr.getJSONArray("do_not_use");
                                    for (int i = 0; i < do_not_use.length(); i++) {
                                        do_not_use_val = do_not_use_val + do_not_use.get(i).toString() + "#";
                                    }
                                    do_not_use_val = do_not_use_val.substring(0, do_not_use_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("do_not_use: "+do_not_use_val.substring(0, do_not_use_val.lastIndexOf("#")));

                                JSONArray pediatric_use;
                                String pediatric_use_val = "";
                                try {
                                    pediatric_use = resultsArr.getJSONArray("pediatric_use");
                                    for (int i = 0; i < pediatric_use.length(); i++) {
                                        pediatric_use_val = pediatric_use_val + pediatric_use.get(i).toString() + "#";
                                    }
                                    pediatric_use_val = pediatric_use_val.substring(0, pediatric_use_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pediatric_use: "+pediatric_use_val.substring(0, pediatric_use_val.lastIndexOf("#")));

                                JSONArray precautions;
                                String precautions_val = "";
                                try {
                                    precautions = resultsArr.getJSONArray("precautions");
                                    for (int i = 0; i < precautions.length(); i++) {
                                        precautions_val = precautions_val + precautions.get(i).toString() + "#";
                                    }
                                    precautions_val = precautions_val.substring(0, precautions_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("precautions: "+precautions_val.substring(0, precautions_val.lastIndexOf("#")));

                                JSONArray risks;
                                String risks_val = "";
                                try {
                                    risks = resultsArr.getJSONArray("risks");
                                    for (int i = 0; i < risks.length(); i++) {
                                        risks_val = risks_val + risks.get(i).toString() + "#";
                                    }
                                    risks_val = risks_val.substring(0, risks_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("risks: "+risks_val.substring(0, risks_val.lastIndexOf("#")));

                                JSONArray use_in_specific_populations;
                                String use_in_specific_populations_val = "";
                                try {
                                    use_in_specific_populations = resultsArr.getJSONArray("use_in_specific_populations");
                                    for (int i = 0; i < use_in_specific_populations.length(); i++) {
                                        use_in_specific_populations_val = use_in_specific_populations_val + use_in_specific_populations.get(i).toString() + "#";
                                    }
                                    use_in_specific_populations_val = use_in_specific_populations_val.substring(0, use_in_specific_populations_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("use_in_specific_populations: "+use_in_specific_populations_val.substring(0, use_in_specific_populations_val.lastIndexOf("#")));

                                String keep_out_of_reach_of_children_val = "";
                                JSONArray keep_out_of_reach_of_children;
                                try {
                                    keep_out_of_reach_of_children = resultsArr.getJSONArray("keep_out_of_reach_of_children");
                                    for (int i = 0; i < keep_out_of_reach_of_children.length(); i++) {
                                        keep_out_of_reach_of_children_val = keep_out_of_reach_of_children_val + keep_out_of_reach_of_children.get(i).toString() + "#";
                                    }
                                    keep_out_of_reach_of_children_val = keep_out_of_reach_of_children_val.substring(0, keep_out_of_reach_of_children_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("keep_out_of_reach_of_children: "+keep_out_of_reach_of_children_val.substring(0, keep_out_of_reach_of_children_val.lastIndexOf("#")));

                                String questions_Val = "";
                                JSONArray questions;
                                try {
                                    questions = resultsArr.getJSONArray("questions");
                                    for (int i = 0; i < questions.length(); i++) {
                                        questions_Val = questions_Val + questions.get(i).toString() + "#";
                                    }
                                    questions_Val = questions_Val.substring(0, questions_Val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("questions: "+questions_Val.substring(0, questions_Val.lastIndexOf("#")));

                                String dosage_and_administration_val = "";
                                JSONArray dosage_and_administration;
                                try {
                                    dosage_and_administration = resultsArr.getJSONArray("dosage_and_administration");
                                    for (int i = 0; i < dosage_and_administration.length(); i++) {
                                        dosage_and_administration_val = dosage_and_administration_val + dosage_and_administration.get(i).toString() + "#";
                                    }
                                    dosage_and_administration_val = dosage_and_administration_val.substring(0, dosage_and_administration_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("dosage_and_administration: "+dosage_and_administration_val.substring(0, dosage_and_administration_val.lastIndexOf("#")));

                                String purpose_val = "";
                                JSONArray purpose;
                                try {
                                    purpose = resultsArr.getJSONArray("purpose");
                                    for (int i = 0; i < purpose.length(); i++) {
                                        purpose_val = purpose_val + purpose.get(i).toString() + "#";
                                    }
                                    purpose_val = purpose_val.substring(0, purpose_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("purpose: "+purpose_val.substring(0, purpose_val.lastIndexOf("#")));

                                String version_val = "";
                                try {
                                    version_val = resultsArr.getString("version");
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("version: " + version_val);

                                String package_label_principal_display_panel_val = "";
                                JSONArray package_label_principal_display_panel;
                                try {
                                    package_label_principal_display_panel = resultsArr.getJSONArray("package_label_principal_display_panel");
                                    for (int i = 0; i < package_label_principal_display_panel.length(); i++) {
                                        package_label_principal_display_panel_val = package_label_principal_display_panel_val + package_label_principal_display_panel.get(i).toString() + "#";
                                    }
                                    package_label_principal_display_panel_val = package_label_principal_display_panel_val.substring(0, package_label_principal_display_panel_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("package_label_principal_display_panel: "+package_label_principal_display_panel_val.substring(0, package_label_principal_display_panel_val.lastIndexOf("#")));

                                String active_ingredient_val = "";
                                JSONArray active_ingredient;
                                try {
                                    active_ingredient = resultsArr.getJSONArray("active_ingredient");
                                    for (int i = 0; i < active_ingredient.length(); i++) {
                                        active_ingredient_val = active_ingredient_val + active_ingredient.get(i).toString() + "#";
                                    }
                                    active_ingredient_val = active_ingredient_val.substring(0, active_ingredient_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("active_ingredient: "+active_ingredient_val.substring(0, active_ingredient_val.lastIndexOf("#")));

                                String inactive_ingredient_val = "";
                                JSONArray inactive_ingredient;
                                try {
                                    inactive_ingredient = resultsArr.getJSONArray("inactive_ingredient");
                                    for (int i = 0; i < inactive_ingredient.length(); i++) {
                                        inactive_ingredient_val = inactive_ingredient_val + inactive_ingredient.get(i).toString() + "#";
                                    }
                                    inactive_ingredient_val = inactive_ingredient_val.substring(0, inactive_ingredient_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("inactive_ingredient: "+inactive_ingredient_val.substring(0, inactive_ingredient_val.lastIndexOf("#")));

                                String effective_time_val = "";
                                try {
                                    effective_time_val = resultsArr.getString("effective_time");
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("effective_time: "+effective_time_val);

                                JSONObject openfda = resultsArr.getJSONObject("openfda");

                                String spl_id_val = "";
                                JSONArray spl_id;
                                try {
                                    spl_id = openfda.getJSONArray("spl_id");
                                    for (int i = 0; i < spl_id.length(); i++) {
                                        spl_id_val = spl_id_val + spl_id.get(i).toString() + "#";
                                    }
                                    spl_id_val = spl_id_val.substring(0, spl_id_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("spl_id: "+spl_id_val.substring(0, spl_id_val.lastIndexOf("#")));

                                String product_ndc_val = "";
                                JSONArray product_ndc;
                                try {
                                    product_ndc = openfda.getJSONArray("product_ndc");
                                    for (int i = 0; i < product_ndc.length(); i++) {
                                        product_ndc_val = product_ndc_val + product_ndc.get(i).toString() + "#";
                                    }
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("product_ndc: "+product_ndc_val.substring(0, product_ndc_val.lastIndexOf("#")));

                                String is_original_packager_val = "";
                                JSONArray is_original_packager;
                                try {
                                    is_original_packager = openfda.getJSONArray("is_original_packager");
                                    for (int i = 0; i < is_original_packager.length(); i++) {
                                        is_original_packager_val = is_original_packager_val + is_original_packager.get(i).toString() + "#";
                                    }
                                    is_original_packager_val = is_original_packager_val.substring(0, is_original_packager_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("is_original_packager: "+is_original_packager_val.substring(0, is_original_packager_val.lastIndexOf("#")));

                                String route_val = "";
                                JSONArray route;
                                try {
                                    route = openfda.getJSONArray("route");
                                    for (int i = 0; i < route.length(); i++) {
                                        route_val = route_val + route.get(i).toString() + "#";
                                    }
                                    route_val = route_val.substring(0, route_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("route: "+route_val.substring(0, route_val.lastIndexOf("#")));

                                String substance_name_val = "";
                                JSONArray substance_name;
                                try {
                                    substance_name = openfda.getJSONArray("substance_name");
                                    for (int i = 0; i < substance_name.length(); i++) {
                                        substance_name_val = substance_name_val + substance_name.get(i).toString() + "; ";
                                    }
                                    substance_name_val = substance_name_val.substring(0, substance_name_val.lastIndexOf(";"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("substance_name: "+substance_name_val.substring(0, substance_name_val.lastIndexOf(";")));

                                String package_ndc_val = "";
                                JSONArray package_ndc;
                                try {
                                    package_ndc = openfda.getJSONArray("package_ndc");
                                    for (int i = 0; i < package_ndc.length(); i++) {
                                        package_ndc_val = package_ndc_val + package_ndc.get(i).toString() + "#";
                                    }
                                    package_ndc_val = package_ndc_val.substring(0, package_ndc_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("package_ndc: "+package_ndc_val.substring(0, package_ndc_val.lastIndexOf("#")));

                                String product_type_val = "";
                                JSONArray product_type;
                                try {
                                    product_type = openfda.getJSONArray("product_type");
                                    for (int i = 0; i < product_type.length(); i++) {
                                        product_type_val = product_type_val + product_type.get(i).toString() + "#";
                                    }
                                    product_type_val = product_type_val.substring(0, product_type_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("product_type: "+product_type_val.substring(0, product_type_val.lastIndexOf("#")));


                                String generic_name_val = "";
                                JSONArray generic_name;
                                try {
                                    generic_name = openfda.getJSONArray("generic_name");
                                    for (int i = 0; i < generic_name.length(); i++) {
                                        generic_name_val = generic_name_val + generic_name.get(i).toString() + "#";
                                    }
                                    generic_name_val = generic_name_val.substring(0, generic_name_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("generic_name: "+generic_name_val.substring(0, generic_name_val.lastIndexOf("#")));

                                String manufacturer_name_val = "";
                                JSONArray manufacturer_name;
                                try {
                                    manufacturer_name = openfda.getJSONArray("manufacturer_name");
                                    for (int i = 0; i < manufacturer_name.length(); i++) {
                                        manufacturer_name_val = manufacturer_name_val + manufacturer_name.get(i).toString() + "#";
                                    }
                                    manufacturer_name_val = manufacturer_name_val.substring(0, manufacturer_name_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("manufacturer_name: "+manufacturer_name_val.substring(0, manufacturer_name_val.lastIndexOf("#")));

                                String brand_name_val = "";
                                JSONArray brand_name;
                                try {
                                    brand_name = openfda.getJSONArray("brand_name");
                                    for (int i = 0; i < brand_name.length(); i++) {
                                        brand_name_val = brand_name_val + brand_name.get(i).toString() + "#";
                                    }
                                    brand_name_val = brand_name_val.substring(0, brand_name_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("brand_name: "+brand_name_val.substring(0, brand_name_val.lastIndexOf("#")));

                                String application_number_val = "";
                                JSONArray application_number;
                                try {
                                    application_number = openfda.getJSONArray("application_number");
                                    for (int i = 0; i < application_number.length(); i++) {
                                        application_number_val = application_number_val + application_number.get(i).toString() + "#";
                                    }
                                    application_number_val = application_number_val.substring(0, application_number_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("application_number: "+application_number_val.substring(0, application_number_val.lastIndexOf("#")));

                                String rxcui_val = "";
                                JSONArray rxcui;
                                try {
                                    rxcui = openfda.getJSONArray("rxcui");
                                    for (int i = 0; i < rxcui.length(); i++) {
                                        rxcui_val = rxcui_val + rxcui.get(i).toString() + "#";
                                    }
                                    rxcui_val = rxcui_val.substring(0, rxcui_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("rxcui: "+rxcui_val.substring(0, rxcui_val.lastIndexOf("#")));

                                String rxstring_val = "";
                                JSONArray rxstring;
                                try {
                                    rxstring = openfda.getJSONArray("rxstring");
                                    for (int i = 0; i < rxstring.length(); i++) {
                                        rxstring_val = rxstring_val + rxstring.get(i).toString() + "#";
                                    }
                                    rxstring_val = rxstring_val.substring(0, rxstring_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("rxstring: "+rxstring_val.substring(0, rxstring_val.lastIndexOf("#")));

                                String rxtty_val = "";
                                JSONArray rxtty;
                                try {
                                    rxtty = openfda.getJSONArray("rxtty");
                                    for (int i = 0; i < rxtty.length(); i++) {
                                        rxtty_val = rxtty_val + rxtty.get(i).toString() + "#";
                                    }
                                    rxtty_val = rxtty_val.substring(0, rxtty_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("rxtty: "+rxtty_val.substring(0, rxtty_val.lastIndexOf("#")));

                                String rxtty_exact_val = "";
                                JSONArray rxtty_exact;
                                try {
                                    rxtty_exact = openfda.getJSONArray("rxtty_exact");
                                    for (int i = 0; i < rxtty_exact.length(); i++) {
                                        rxtty_exact_val = rxtty_exact_val + rxtty_exact.get(i).toString() + "#";
                                    }
                                    rxtty_exact_val = rxtty_exact_val.substring(0, rxtty_exact_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("rxtty_exact: "+rxtty_exact_val.substring(0, rxtty_exact_val.lastIndexOf("#")));

                                String unii_val = "";
                                JSONArray unii;
                                try {
                                    unii = openfda.getJSONArray("unii");
                                    for (int i = 0; i < unii.length(); i++) {
                                        unii_val = unii_val + unii.get(i).toString() + "#";
                                    }
                                    unii_val = unii_val.substring(0, unii_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("unii: "+unii_val.substring(0, unii_val.lastIndexOf("#")));

                                String nui_val = "";
                                JSONArray nui;
                                try {
                                    nui = openfda.getJSONArray("nui");
                                    for (int i = 0; i < nui.length(); i++) {
                                        nui_val = nui_val + nui.get(i).toString() + "#";
                                    }
                                    nui_val = nui_val.substring(0, nui_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("nui: "+nui_val.substring(0, nui_val.lastIndexOf("#")));

                                String upc_val = "";
                                JSONArray upc;
                                try {
                                    upc = openfda.getJSONArray("upc");
                                    for (int i = 0; i < upc.length(); i++) {
                                        upc_val = upc_val + upc.get(i).toString() + "#";
                                    }
                                    upc_val = upc_val.substring(0, upc_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("upc: "+upc_val.substring(0, upc_val.lastIndexOf("#")));

                                String pharm_class_moa_exact_val = "";
                                JSONArray pharm_class_moa_exact;
                                try {
                                    pharm_class_moa_exact = openfda.getJSONArray("pharm_class_moa_exact");
                                    for (int i = 0; i < pharm_class_moa_exact.length(); i++) {
                                        pharm_class_moa_exact_val = pharm_class_moa_exact_val + pharm_class_moa_exact.get(i).toString() + "#";
                                    }
                                    pharm_class_moa_exact_val = pharm_class_moa_exact_val.substring(0, pharm_class_moa_exact_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_moa_exact: "+pharm_class_moa_exact_val.substring(0, pharm_class_moa_exact_val.lastIndexOf("#")));

                                String pharm_class_moa_val = "";
                                JSONArray pharm_class_moa;
                                try {
                                    pharm_class_moa = openfda.getJSONArray("pharm_class_moa");
                                    for (int i = 0; i < pharm_class_moa.length(); i++) {
                                        pharm_class_moa_val = pharm_class_moa_val + pharm_class_moa.get(i).toString() + "#";
                                    }
                                    pharm_class_moa_val = pharm_class_moa_val.substring(0, pharm_class_moa_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_moa: "+pharm_class_moa_val.substring(0, pharm_class_moa_val.lastIndexOf("#")));

                                String pharm_class_pe_val = "";
                                JSONArray pharm_class_pe;
                                try {
                                    pharm_class_pe = openfda.getJSONArray("pharm_class_pe");
                                    for (int i = 0; i < pharm_class_pe.length(); i++) {
                                        pharm_class_pe_val = pharm_class_pe_val + pharm_class_pe.get(i).toString() + "#";
                                    }
                                    pharm_class_pe_val = pharm_class_pe_val.substring(0, pharm_class_pe_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_pe: "+pharm_class_pe_val.substring(0, pharm_class_pe_val.lastIndexOf("#")));

                                String pharm_class_pe_exact_val = "";
                                JSONArray pharm_class_pe_exact;
                                try {
                                    pharm_class_pe_exact = openfda.getJSONArray("pharm_class_pe_exact");
                                    for (int i = 0; i < pharm_class_pe_exact.length(); i++) {
                                        pharm_class_pe_exact_val = pharm_class_pe_exact_val + pharm_class_pe_exact.get(i).toString() + "#";
                                    }
                                    pharm_class_pe_exact_val = pharm_class_pe_exact_val.substring(0, pharm_class_pe_exact_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_pe_exact: "+pharm_class_pe_exact_val.substring(0, pharm_class_pe_exact_val.lastIndexOf("#")));

                                String pharm_class_cs_val = "";
                                JSONArray pharm_class_cs;
                                try {
                                    pharm_class_cs = openfda.getJSONArray("pharm_class_cs");
                                    for (int i = 0; i < pharm_class_cs.length(); i++) {
                                        pharm_class_cs_val = pharm_class_cs_val + pharm_class_cs.get(i).toString() + "#";
                                    }
                                    pharm_class_cs_val = pharm_class_cs_val.substring(0, pharm_class_cs_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_cs: "+pharm_class_cs_val.substring(0, pharm_class_cs_val.lastIndexOf("#")));

                                String pharm_class_cs_exact_val = "";
                                JSONArray pharm_class_cs_exact;
                                try {
                                    pharm_class_cs_exact = openfda.getJSONArray("pharm_class_cs_exact");
                                    for (int i = 0; i < pharm_class_cs_exact.length(); i++) {
                                        pharm_class_cs_exact_val = pharm_class_cs_exact_val + pharm_class_cs_exact.get(i).toString() + "#";
                                    }
                                    pharm_class_cs_exact_val = pharm_class_cs_exact_val.substring(0, pharm_class_cs_exact_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_cs_exact: "+pharm_class_cs_exact_val.substring(0, pharm_class_cs_exact_val.lastIndexOf("#")));

                                String pharm_class_epc_val = "";
                                JSONArray pharm_class_epc;
                                try {
                                    pharm_class_epc = openfda.getJSONArray("pharm_class_epc");
                                    for (int i = 0; i < pharm_class_epc.length(); i++) {
                                        pharm_class_epc_val = pharm_class_epc_val + pharm_class_epc.get(i).toString() + "#";
                                    }
                                    pharm_class_epc_val = pharm_class_epc_val.substring(0, pharm_class_epc_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_epc: "+pharm_class_epc_val.substring(0, pharm_class_epc_val.lastIndexOf("#")));

                                String pharm_class_epc_exact_val = "";
                                JSONArray pharm_class_epc_exact;
                                try {
                                    pharm_class_epc_exact = openfda.getJSONArray("pharm_class_epc_exact");
                                    for (int i = 0; i < pharm_class_epc_exact.length(); i++) {
                                        pharm_class_epc_exact_val = pharm_class_epc_exact_val + pharm_class_epc_exact.get(i).toString() + "#";
                                    }
                                    pharm_class_epc_exact_val = pharm_class_epc_exact_val.substring(0, pharm_class_epc_exact_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("pharm_class_epc_exact: "+pharm_class_epc_exact_val.substring(0, pharm_class_epc_exact_val.lastIndexOf("#")));

                                String spl_product_data_elements_val = "";
                                JSONArray spl_product_data_elements;
                                try {
                                    spl_product_data_elements = resultsArr.getJSONArray("spl_product_data_elements");
                                    for (int i = 0; i < spl_product_data_elements.length(); i++) {
                                        spl_product_data_elements_val = spl_product_data_elements_val + spl_product_data_elements.get(i).toString() + "#";
                                    }
                                    spl_product_data_elements_val = spl_product_data_elements_val.substring(0, spl_product_data_elements_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("spl_product_data_elements: "+spl_product_data_elements_val.substring(0, spl_product_data_elements_val.lastIndexOf("#")));

                                String when_using_val = "";
                                JSONArray when_using;
                                try {
                                    when_using = resultsArr.getJSONArray("when_using");
                                    for (int i = 0; i < when_using.length(); i++) {
                                        when_using_val = when_using_val + when_using.get(i).toString() + "#";
                                    }
                                    when_using_val = when_using_val.substring(0, when_using_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("when_using: "+when_using_val.substring(0, when_using_val.lastIndexOf("#")));

                                String spl_unclassified_section_val = "";
                                JSONArray spl_unclassified_section;
                                try {
                                    spl_unclassified_section = resultsArr.getJSONArray("spl_unclassified_section");
                                    for (int i = 0; i < spl_unclassified_section.length(); i++) {
                                        spl_unclassified_section_val = spl_unclassified_section_val + spl_unclassified_section.get(i).toString() + "#";
                                    }
                                    spl_unclassified_section_val = spl_unclassified_section_val.substring(0, spl_unclassified_section_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("spl_unclassified_section: "+spl_unclassified_section_val.substring(0, spl_unclassified_section_val.lastIndexOf("#")));

                                String warnings_val = "";
                                JSONArray warnings;
                                try {
                                    warnings = resultsArr.getJSONArray("warnings");
                                    for (int i = 0; i < warnings.length(); i++) {
                                        warnings_val = warnings_val + warnings.get(i).toString() + "#";
                                    }
                                    warnings_val = warnings_val.substring(0, warnings_val.lastIndexOf("#"));
                                } catch (JSONException e) {
                                    //System.out.println(e.getLocalizedMessage());
                                }
                                //System.out.println("warnings: "+warnings_val.substring(0, warnings_val.lastIndexOf("#")));

                                inrPs.setString(1, productNDC);
                                inrPs.setString(2, substance_name_val);
                                inrPs.setString(3, brand_name_val);
                                inrPs.setString(4, generic_name_val);
                                inrPs.setString(5, route_val);
                                inrPs.setString(6, manufacturer_name_val);
                                inrPs.setString(7, package_ndc_val);
                                inrPs.setString(8, product_type_val);
                                inrPs.setString(9, application_number_val);
                                inrPs.setString(10, rxcui_val);
                                inrPs.setString(11, rxstring_val);
                                inrPs.setString(12, rxtty_val);
                                inrPs.setString(13, rxtty_exact_val);
                                inrPs.setString(14, unii_val);
                                inrPs.setString(15, nui_val);
                                inrPs.setString(16, upc_val);
                                inrPs.setString(17, pharm_class_moa_val);
                                inrPs.setString(18, pharm_class_moa_exact_val);
                                inrPs.setString(19, pharm_class_pe_val);
                                inrPs.setString(20, pharm_class_pe_exact_val);
                                inrPs.setString(21, pharm_class_cs_val);
                                inrPs.setString(22, pharm_class_cs_exact_val);
                                inrPs.setString(23, pharm_class_epc_val);
                                inrPs.setString(24, pharm_class_epc_exact_val);
                                inrPs.setString(25, active_ingredient_val);
                                inrPs.setString(26, inactive_ingredient_val);
                                inrPs.setString(27, indications_and_usage_val);
                                inrPs.setString(28, stop_use_val);
                                inrPs.setString(29, keep_out_of_reach_of_children_val);
                                inrPs.setString(30, questions_Val);
                                inrPs.setString(31, dosage_and_administration_val);
                                inrPs.setString(32, purpose_val);
                                inrPs.setString(33, version_val);
                                inrPs.setString(34, package_label_principal_display_panel_val);
                                inrPs.setString(35, effective_time_val);
                                inrPs.setString(36, spl_id_val);
                                inrPs.setString(37, is_original_packager_val);
                                inrPs.setString(38, spl_product_data_elements_val);
                                inrPs.setString(39, when_using_val);
                                inrPs.setString(40, spl_unclassified_section_val);
                                inrPs.setString(41, warnings_val);
                                inrPs.setString(42, product_ndc_val);
                                inrPs.setString(43, adverse_reactions_val);
                                inrPs.setString(44, alarms_val);
                                inrPs.setString(45, contraindications_val);
                                inrPs.setString(46, dosage_forms_and_strengths_val);
                                inrPs.setString(47, drug_abuse_and_dependence_val);
                                inrPs.setString(48, drug_interactions_val);
                                inrPs.setString(49, food_safety_warning_val);
                                inrPs.setString(50, health_claim_val);
                                inrPs.setString(51, how_supplied_val);
                                inrPs.setString(52, laboratory_tests_val);
                                inrPs.setString(53, do_not_use_val);
                                inrPs.setString(54, pediatric_use_val);
                                inrPs.setString(55, precautions_val);
                                inrPs.setString(56, risks_val);
                                inrPs.setString(57, use_in_specific_populations_val);
                                inrPs.setString(58, "YES");
                                inrPs.addBatch();
                                System.out.println("PRESENT");
                            } catch (Exception e) {
                                //System.out.println("Error:" + productNDC + e.getLocalizedMessage());
                                inrPs.setString(1, productNDC);
                                inrPs.setString(2, null);
                                inrPs.setString(3, null);
                                inrPs.setString(4, null);
                                inrPs.setString(5, null);
                                inrPs.setString(6, null);
                                inrPs.setString(7, null);
                                inrPs.setString(8, null);
                                inrPs.setString(9, null);
                                inrPs.setString(10, null);
                                inrPs.setString(11, null);
                                inrPs.setString(12, null);
                                inrPs.setString(13, null);
                                inrPs.setString(14, null);
                                inrPs.setString(15, null);
                                inrPs.setString(16, null);
                                inrPs.setString(17, null);
                                inrPs.setString(18, null);
                                inrPs.setString(19, null);
                                inrPs.setString(20, null);
                                inrPs.setString(21, null);
                                inrPs.setString(22, null);
                                inrPs.setString(23, null);
                                inrPs.setString(24, null);
                                inrPs.setString(25, null);
                                inrPs.setString(26, null);
                                inrPs.setString(27, null);
                                inrPs.setString(28, null);
                                inrPs.setString(29, null);
                                inrPs.setString(30, null);
                                inrPs.setString(31, null);
                                inrPs.setString(32, null);
                                inrPs.setString(33, null);
                                inrPs.setString(34, null);
                                inrPs.setString(35, null);
                                inrPs.setString(36, null);
                                inrPs.setString(37, null);
                                inrPs.setString(38, null);
                                inrPs.setString(39, null);
                                inrPs.setString(40, null);
                                inrPs.setString(41, null);
                                inrPs.setString(42, null);
                                inrPs.setString(43, null);
                                inrPs.setString(44, null);
                                inrPs.setString(45, null);
                                inrPs.setString(46, null);
                                inrPs.setString(47, null);
                                inrPs.setString(48, null);
                                inrPs.setString(49, null);
                                inrPs.setString(50, null);
                                inrPs.setString(51, null);
                                inrPs.setString(52, null);
                                inrPs.setString(53, null);
                                inrPs.setString(54, null);
                                inrPs.setString(55, null);
                                inrPs.setString(56, null);
                                inrPs.setString(57, null);
                                inrPs.setString(58, "NO");
                                inrPs.addBatch();
                                System.out.println("NO");
                            }
                        } else {
                            //System.out.println("Error: " + productNDC);
                            inrPs.setString(1, productNDC);
                            inrPs.setString(2, null);
                            inrPs.setString(3, null);
                            inrPs.setString(4, null);
                            inrPs.setString(5, null);
                            inrPs.setString(6, null);
                            inrPs.setString(7, null);
                            inrPs.setString(8, null);
                            inrPs.setString(9, null);
                            inrPs.setString(10, null);
                            inrPs.setString(11, null);
                            inrPs.setString(12, null);
                            inrPs.setString(13, null);
                            inrPs.setString(14, null);
                            inrPs.setString(15, null);
                            inrPs.setString(16, null);
                            inrPs.setString(17, null);
                            inrPs.setString(18, null);
                            inrPs.setString(19, null);
                            inrPs.setString(20, null);
                            inrPs.setString(21, null);
                            inrPs.setString(22, null);
                            inrPs.setString(23, null);
                            inrPs.setString(24, null);
                            inrPs.setString(25, null);
                            inrPs.setString(26, null);
                            inrPs.setString(27, null);
                            inrPs.setString(28, null);
                            inrPs.setString(29, null);
                            inrPs.setString(30, null);
                            inrPs.setString(31, null);
                            inrPs.setString(32, null);
                            inrPs.setString(33, null);
                            inrPs.setString(34, null);
                            inrPs.setString(35, null);
                            inrPs.setString(36, null);
                            inrPs.setString(37, null);
                            inrPs.setString(38, null);
                            inrPs.setString(39, null);
                            inrPs.setString(40, null);
                            inrPs.setString(41, null);
                            inrPs.setString(42, null);
                            inrPs.setString(43, null);
                            inrPs.setString(44, null);
                            inrPs.setString(45, null);
                            inrPs.setString(46, null);
                            inrPs.setString(47, null);
                            inrPs.setString(48, null);
                            inrPs.setString(49, null);
                            inrPs.setString(50, null);
                            inrPs.setString(51, null);
                            inrPs.setString(52, null);
                            inrPs.setString(53, null);
                            inrPs.setString(54, null);
                            inrPs.setString(55, null);
                            inrPs.setString(56, null);
                            inrPs.setString(57, null);
                            inrPs.setString(58, "NO");
                            inrPs.addBatch();
                            System.out.println("NO");
                        }
                    }else{
                        //System.out.println("Error: " + productNDC);
                        inrPs.setString(1, productNDC);
                        inrPs.setString(2, null);
                        inrPs.setString(3, null);
                        inrPs.setString(4, null);
                        inrPs.setString(5, null);
                        inrPs.setString(6, null);
                        inrPs.setString(7, null);
                        inrPs.setString(8, null);
                        inrPs.setString(9, null);
                        inrPs.setString(10, null);
                        inrPs.setString(11, null);
                        inrPs.setString(12, null);
                        inrPs.setString(13, null);
                        inrPs.setString(14, null);
                        inrPs.setString(15, null);
                        inrPs.setString(16, null);
                        inrPs.setString(17, null);
                        inrPs.setString(18, null);
                        inrPs.setString(19, null);
                        inrPs.setString(20, null);
                        inrPs.setString(21, null);
                        inrPs.setString(22, null);
                        inrPs.setString(23, null);
                        inrPs.setString(24, null);
                        inrPs.setString(25, null);
                        inrPs.setString(26, null);
                        inrPs.setString(27, null);
                        inrPs.setString(28, null);
                        inrPs.setString(29, null);
                        inrPs.setString(30, null);
                        inrPs.setString(31, null);
                        inrPs.setString(32, null);
                        inrPs.setString(33, null);
                        inrPs.setString(34, null);
                        inrPs.setString(35, null);
                        inrPs.setString(36, null);
                        inrPs.setString(37, null);
                        inrPs.setString(38, null);
                        inrPs.setString(39, null);
                        inrPs.setString(40, null);
                        inrPs.setString(41, null);
                        inrPs.setString(42, null);
                        inrPs.setString(43, null);
                        inrPs.setString(44, null);
                        inrPs.setString(45, null);
                        inrPs.setString(46, null);
                        inrPs.setString(47, null);
                        inrPs.setString(48, null);
                        inrPs.setString(49, null);
                        inrPs.setString(50, null);
                        inrPs.setString(51, null);
                        inrPs.setString(52, null);
                        inrPs.setString(53, null);
                        inrPs.setString(54, null);
                        inrPs.setString(55, null);
                        inrPs.setString(56, null);
                        inrPs.setString(57, null);
                        inrPs.setString(58, "NO");
                        inrPs.addBatch();
                        System.out.println("NO");
                    }
                } catch (Exception e) {
                    //System.out.println("Error: " + e.getLocalizedMessage());
                    inrPs.setString(1, productNDC);
                    inrPs.setString(2, null);
                    inrPs.setString(3, null);
                    inrPs.setString(4, null);
                    inrPs.setString(5, null);
                    inrPs.setString(6, null);
                    inrPs.setString(7, null);
                    inrPs.setString(8, null);
                    inrPs.setString(9, null);
                    inrPs.setString(10, null);
                    inrPs.setString(11, null);
                    inrPs.setString(12, null);
                    inrPs.setString(13, null);
                    inrPs.setString(14, null);
                    inrPs.setString(15, null);
                    inrPs.setString(16, null);
                    inrPs.setString(17, null);
                    inrPs.setString(18, null);
                    inrPs.setString(19, null);
                    inrPs.setString(20, null);
                    inrPs.setString(21, null);
                    inrPs.setString(22, null);
                    inrPs.setString(23, null);
                    inrPs.setString(24, null);
                    inrPs.setString(25, null);
                    inrPs.setString(26, null);
                    inrPs.setString(27, null);
                    inrPs.setString(28, null);
                    inrPs.setString(29, null);
                    inrPs.setString(30, null);
                    inrPs.setString(31, null);
                    inrPs.setString(32, null);
                    inrPs.setString(33, null);
                    inrPs.setString(34, null);
                    inrPs.setString(35, null);
                    inrPs.setString(36, null);
                    inrPs.setString(37, null);
                    inrPs.setString(38, null);
                    inrPs.setString(39, null);
                    inrPs.setString(40, null);
                    inrPs.setString(41, null);
                    inrPs.setString(42, null);
                    inrPs.setString(43, null);
                    inrPs.setString(44, null);
                    inrPs.setString(45, null);
                    inrPs.setString(46, null);
                    inrPs.setString(47, null);
                    inrPs.setString(48, null);
                    inrPs.setString(49, null);
                    inrPs.setString(50, null);
                    inrPs.setString(51, null);
                    inrPs.setString(52, null);
                    inrPs.setString(53, null);
                    inrPs.setString(54, null);
                    inrPs.setString(55, null);
                    inrPs.setString(56, null);
                    inrPs.setString(57, null);
                    inrPs.setString(58, "NO");
                    inrPs.addBatch();
                    System.out.println("NO");
                }

                if (++count % batchSize == 0) {
                    //System.out.println("Executed batch");
                    inrPs.executeBatch();
                }
                System.out.println("Batch Counter" + count);
                reqCount++;
                if (reqCount % 240 == 0) {
                    long endtime = Calendar.getInstance().getTime().getTime();
                    int sec = (int) (endtime - starttime) / 1000;
                    if (sec < 60) {
                        try {
                            Thread.sleep(60 - sec);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        starttime = Calendar.getInstance().getTime().getTime();
                    }
                }
                drugCounter++;
            }
            inrPs.executeBatch(); // insert remaining records
            rs.close();
            DBConnectionManager.releaseResources(updRscObj);
            DBConnectionManager.releaseResources(rscObj);
        } catch (SQLException e) {
            System.out.println("The exception is: " + e.getMessage());
        }
    }

    public static String callURL(String myURL) throws Exception {
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
            //sb.append("Error");
            //System.out.println("ERRRRRRRRRRRRRRRRRRRROR");
            throw new Exception(e);
            //throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }
        return sb.toString();
    }
}
