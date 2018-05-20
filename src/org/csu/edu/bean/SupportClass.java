/*
**The SupportClass mainly encapsulates the request parameters sent from
* the client and serves as the interface for passing parameters between the modules.
* Support is mainly used to Support the dispute processing module, including
* the request information maintenance category, consulting, gender, income, number of children,
* both sides pay lowest and highest pay and their bad behavior, etc.
 */
package org.csu.edu.bean;

public class SupportClass {
    private int id;
    private String sex;
    private double myIncome;
    private double income;
    private double childCost;
    private int childNum;
    private double minPay = 0;
    private double maxPay = 0;
    private String  error;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getMyIncome() {
        return myIncome;
    }

    public void setMyIncome(double myIncome) {
        this.myIncome = myIncome;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getChildCost() {
        return childCost;
    }

    public void setChildCost(double childCost) {
        this.childCost = childCost;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public double getMinPay() {
        return minPay;
    }

    public void setMinPay(double minPay) {
        this.minPay = minPay;
    }

    public double getMaxPay() {
        return maxPay;
    }

    public void setMaxPay(double maxPay) {
        this.maxPay = maxPay;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
