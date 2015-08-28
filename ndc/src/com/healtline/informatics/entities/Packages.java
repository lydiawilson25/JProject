package com.healtline.informatics.entities;

/**
 * Created with IntelliJ IDEA.
 * User: wstephen
 * Date: 10/31/14
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Packages {
    private String productID;
    private String productNdc;
    private String ndcPkgCode;
    private String pkgDesc;
    private int foundNdcPkgTmp;
    private int foundNdcPkg;

    @Override
    public String toString() {
        return "Packages{" +
                "productID='" + productID + '\'' +
                ", productNdc='" + productNdc + '\'' +
                ", ndcPkgCode='" + ndcPkgCode + '\'' +
                ", pkgDesc='" + pkgDesc + '\'' +
                ", foundNdcPkgTmp=" + foundNdcPkgTmp +
                ", foundNdcPkg=" + foundNdcPkg +
                '}';
    }

    public int getFoundNdcPkg() {
        return foundNdcPkg;
    }

    public void setFoundNdcPkg(int foundNdcPkg) {
        this.foundNdcPkg = foundNdcPkg;
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

    public String getNdcPkgCode() {
        return ndcPkgCode;
    }

    public void setNdcPkgCode(String ndcPkgCode) {
        this.ndcPkgCode = ndcPkgCode;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public int getFoundNdcPkgTmp() {
        return foundNdcPkgTmp;
    }

    public void setFoundNdcPkgTmp(int foundNdcPkgTmp) {
        this.foundNdcPkgTmp = foundNdcPkgTmp;
    }
}
