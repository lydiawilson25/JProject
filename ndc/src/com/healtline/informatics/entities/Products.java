package com.healtline.informatics.entities;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/15/14
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class Products {

    private String productID;
    private String productNdc;
    private String productTypeName;
    private String ProprietaryName;
    private String ProprietaryNameSuffix;
    private String NonProprietaryName;
    private String dosageFormName;
    private String routeName;
    private Date startMarketingDate;
    private Date endMarketingDate;
    private String marketingCategoryName;
    private String applNo;
    private String labellerName;
    private String substanceName;
    private String activeNumeratorStrength;
    private String activeIngredUnit;
    private String pharmClasses;
    private String deaSchedule;
    private int foundNdcProdTmp;
    private int foundNdcProd;

    @Override
    public String toString() {
        return "Products{" +
                "productID='" + productID + '\'' +
                ", productNdc='" + productNdc + '\'' +
                ", productTypeName='" + productTypeName + '\'' +
                ", ProprietaryName='" + ProprietaryName + '\'' +
                ", ProprietaryNameSuffix='" + ProprietaryNameSuffix + '\'' +
                ", NonProprietaryName='" + NonProprietaryName + '\'' +
                ", dosageFormName='" + dosageFormName + '\'' +
                ", routeName='" + routeName + '\'' +
                ", startMarketingDate=" + startMarketingDate +
                ", endMarketingDate=" + endMarketingDate +
                ", marketingCategoryName='" + marketingCategoryName + '\'' +
                ", applNo='" + applNo + '\'' +
                ", labellerName='" + labellerName + '\'' +
                ", substanceName='" + substanceName + '\'' +
                ", activeNumeratorStrength='" + activeNumeratorStrength + '\'' +
                ", activeIngredUnit='" + activeIngredUnit + '\'' +
                ", pharmClasses='" + pharmClasses + '\'' +
                ", deaSchedule='" + deaSchedule + '\'' +
                ", foundNdcProdTmp=" + foundNdcProdTmp +
                ", foundNdcProd=" + foundNdcProd +
                '}';
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductNdc() {
        return productNdc;
    }

    public void setProductNdc(String productNdc) {
        this.productNdc = productNdc;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProprietaryName() {
        return ProprietaryName;
    }

    public void setProprietaryName(String proprietaryName) {
        ProprietaryName = proprietaryName;
    }

    public String getProprietaryNameSuffix() {
        return ProprietaryNameSuffix;
    }

    public void setProprietaryNameSuffix(String proprietaryNameSuffix) {
        ProprietaryNameSuffix = proprietaryNameSuffix;
    }

    public String getNonProprietaryName() {
        return NonProprietaryName;
    }

    public void setNonProprietaryName(String nonProprietaryName) {
        NonProprietaryName = nonProprietaryName;
    }

    public String getDosageFormName() {
        return dosageFormName;
    }

    public void setDosageFormName(String dosageFormName) {
        this.dosageFormName = dosageFormName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Date getStartMarketingDate() {
        return startMarketingDate;
    }

    public void setStartMarketingDate(Date startMarketingDate) {
        this.startMarketingDate = startMarketingDate;
    }

    public Date getEndMarketingDate() {
        return endMarketingDate;
    }

    public void setEndMarketingDate(Date endMarketingDate) {
        this.endMarketingDate = endMarketingDate;
    }

    public String getMarketingCategoryName() {
        return marketingCategoryName;
    }

    public void setMarketingCategoryName(String marketingCategoryName) {
        this.marketingCategoryName = marketingCategoryName;
    }

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getLabellerName() {
        return labellerName;
    }

    public void setLabellerName(String labellerName) {
        this.labellerName = labellerName;
    }

    public String getSubstanceName() {
        return substanceName;
    }

    public void setSubstanceName(String substanceName) {
        this.substanceName = substanceName;
    }

    public String getActiveNumeratorStrength() {
        return activeNumeratorStrength;
    }

    public void setActiveNumeratorStrength(String activeNumeratorStrength) {
        this.activeNumeratorStrength = activeNumeratorStrength;
    }

    public String getActiveIngredUnit() {
        return activeIngredUnit;
    }

    public void setActiveIngredUnit(String activeIngredUnit) {
        this.activeIngredUnit = activeIngredUnit;
    }

    public String getPharmClasses() {
        return pharmClasses;
    }

    public void setPharmClasses(String pharmClasses) {
        this.pharmClasses = pharmClasses;
    }

    public String getDeaSchedule() {
        return deaSchedule;
    }

    public void setDeaSchedule(String deaSchedule) {
        this.deaSchedule = deaSchedule;
    }

    public int getFoundNdcProdTmp() {
        return foundNdcProdTmp;
    }

    public void setFoundNdcProdTmp(int foundNdcProdTmp) {
        this.foundNdcProdTmp = foundNdcProdTmp;
    }

    public int getFoundNdcProd() {
        return foundNdcProd;
    }

    public void setFoundNdcProd(int foundNdcProd) {
        this.foundNdcProd = foundNdcProd;
    }
}
