/*
**The MarriageResponse encapsulates all the information that
* the server sends back to the client, which represents a complete
* set of legal advice. MarriageResponse as all category information return interface,
* contains the winning probability information, header information,
* marital disputes overview information, prepare the material information, legal costs,
* judgments templates, proposals for action information, laws, and similar cases.
 */
package org.csu.edu.bean;

public class MarriageResponse {
    private String chance;
    private String header;
    private String divorcePath;
    private String mtlPrepare;
    private String legalCost;
    private String indictment;
    private String action;
    private String relatedRaw;
    private String[] cases;

    public MarriageResponse(String chance, String header, String divorcePath, String mtlPrepare, String legalCost,
                            String indictment, String action, String[] cases, String relatedRaw){
        this.chance = chance;
        this.header = header;
        this.divorcePath = divorcePath;
        this.mtlPrepare = mtlPrepare;
        this.legalCost = legalCost;
        this.indictment = indictment;
        this.action = action;
        this.cases = cases;
        this.relatedRaw = relatedRaw;
    }

    public MarriageResponse(){}

    public String getChance() {
        return chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDivorcePath() {
        return divorcePath;
    }

    public void setDivorcePath(String divorcePath) {
        this.divorcePath = divorcePath;
    }

    public String getMtlPrepare() {
        return mtlPrepare;
    }

    public void setMtlPrepare(String mtlPrepare) {
        this.mtlPrepare = mtlPrepare;
    }

    public String getLegalCost() {
        return legalCost;
    }

    public void setLegalCost(String legalCost) {
        this.legalCost = legalCost;
    }

    public String getIndictment() {
        return indictment;
    }

    public void setIndictment(String indictment) {
        this.indictment = indictment;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String[] getCases() {
        return cases;
    }

    public void setCases(String[] cases) {
        this.cases = cases;
    }

    public String getRelatedRaw() {
        return relatedRaw;
    }

    public void setRelatedRaw(String relatedRaw) {
        this.relatedRaw = relatedRaw;
    }
}
