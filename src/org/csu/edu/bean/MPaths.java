package org.csu.edu.bean;

import org.csu.edu.util.MStrUtil;

public class MPaths {
    private String jgmtDir;
    private String chancePath;
    private String headerPath;
    private String marriagePath;
    private String mtlPreparePath;
    private String legalCostPath;
    private String indictmentPath;
    private String actionPath;
    private String relatedLawPath;

    public MPaths(String dirRoot){
        this(dirRoot, "judgements", "chance.txt", "header.txt", "divorcePart.txt",
                "mtlPrepare.txt", "legalCost.txt", "indictment.txt", "action.txt", "relatedRaw.txt");
    }

    public MPaths(String dirRoot, String jgmtDir, String chancePath, String headerPath, String marriagePath, String mtlPreparePath,
                  String legalCostPath, String indictmentPath, String actionPath, String relatedLawPath){
        this.jgmtDir = MStrUtil.concatPath(dirRoot, jgmtDir);
        this.chancePath = MStrUtil.concatPath(dirRoot, chancePath);
        this.headerPath = MStrUtil.concatPath(dirRoot, headerPath);
        this.marriagePath = MStrUtil.concatPath(dirRoot, marriagePath);
        this.mtlPreparePath = MStrUtil.concatPath(dirRoot, mtlPreparePath);
        this.legalCostPath = MStrUtil.concatPath(dirRoot, legalCostPath);
        this.indictmentPath = MStrUtil.concatPath(dirRoot, indictmentPath);
        this.actionPath = MStrUtil.concatPath(dirRoot, actionPath);
        this.relatedLawPath = MStrUtil.concatPath(dirRoot, relatedLawPath);
    }

    public String getJgmtDir() {
        return jgmtDir;
    }

    public void setJgmtDir(String jgmtDir) {
        this.jgmtDir = jgmtDir;
    }

    public String getChancePath() {
        return chancePath;
    }

    public void setChancePath(String chancePath) {
        this.chancePath = chancePath;
    }

    public String getHeaderPath() {
        return headerPath;
    }

    public void setHeaderPath(String headerPath) {
        this.headerPath = headerPath;
    }

    public String getMarriagePath() {
        return marriagePath;
    }

    public void setMarriagePath(String marriagePath) {
        this.marriagePath = marriagePath;
    }

    public String getMtlPreparePath() {
        return mtlPreparePath;
    }

    public void setMtlPreparePath(String mtlPreparePath) {
        this.mtlPreparePath = mtlPreparePath;
    }

    public String getLegalCostPath() {
        return legalCostPath;
    }

    public void setLegalCostPath(String legalCostPath) {
        this.legalCostPath = legalCostPath;
    }

    public String getIndictmentPath() {
        return indictmentPath;
    }

    public void setIndictmentPath(String indictmentPath) {
        this.indictmentPath = indictmentPath;
    }

    public String getActionPath() {
        return actionPath;
    }

    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }

    public String getRelatedLawPath() {
        return relatedLawPath;
    }

    public void setRelatedLawPath(String relatedLawPath) {
        this.relatedLawPath = relatedLawPath;
    }
}
