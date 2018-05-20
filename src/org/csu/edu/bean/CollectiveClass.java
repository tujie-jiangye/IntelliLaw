/*
**The CollectiveClass mainly encapsulates the request parameters
* sent from the client and serves as the interface for passing parameters
* between the modules. Support is mainly used for common property processing module,
* containing all the common property of the relevant information, including gender,
 * both fault, jointly owned consulting party the quantity, value, the investment
 * proportion of the house, the quantity, value, the investment proportion of the car,
  * the account balance, the value of the warehouse, the male money, cooperative enterprises,
  * securities, insurance and other information.
 */
package org.csu.edu.bean;

public class CollectiveClass {
    private String sex;
    private String error1;                      //代表你有什么过错
    private String error2;                      //代表对方有什么过错
    private double [] HouseValue;               //代表房子的价格 ，0，1，2下表对应第一，二，三套房子，没有选满3套，剩余部分初始化为0
    private double [] CarValue;                 //代表车子的价格 ，0，1，2下表对应第一，二，三套房子，没有选满3套，剩余部分初始化为0
    private double [] stroageValue;             //代表仓库的价格 ，0，1，2下表对应第一，二，三套房子，没有选满3套，剩余部分初始化为0
    private double [] account;                  //代表存款，0代表我的存款，1代表对方的存款
    private double [] fund;                     //代表公积金，0代表我的公积金，1代表对方的公积金
    private double [] HouseRate;                //代表房子出资的比率
    private double [] StroageRate;              //代表仓库出资的比率
    private double [] HouseDistribute;          //代表分别出资多少钱，0，第一套房子你出资多少，1第一套房子对方出资多少
    private double [] StroageDistribute;        //代表分别出资多少钱，0，第一个仓库你出资多少，1第一个仓库对方出资多少
    private String [] HouseState;               //代表房子判决的属性信息
    private String [] CarState;                 //代表车子判决的属性信息
    private String [] StroageState;             //代表仓库判决的属性信息
    private int [] knowledgeState;              //代表有哪些知识产权 know[0]==1代表有著作权 know[1]==1代表有专利权 know[2]==1代表有商标权
    private int [] belongs;                     //代表财产有哪些，0房子1车子2存款3仓库4家具家电5知识产权6公积金7合作企业8有价证券9保险金
    private int houseNum;                       //房子数量
    private int carNum;                         //车子数量
    private int shopNum;                        //仓库数量


    /*
    * 得到第index套房子所属类别
    * @param index： 第index套房子
     */
    public int getHouseClassIndex(int index){
        if (HouseState[index] == "已经协商好了" || "已经协商好了".equals(HouseState[index])){
            return 0;
        }else if (HouseState[index].contains("购买的（盖的房子）") && HouseState[index].contains("婚前购买")){
            if (HouseDistribute[2 * index + 1] < 0.01){         //仅己方出资
                return 1;
            }else if(HouseDistribute[2 * index] < 0.01){        //仅对方出资
                return 2;
            }else {                                             //双方出资
                return 3;
            }
        }else if (HouseState[index].contains("购买的（盖的房子）") && HouseState[index].contains("婚后购买")){
            return 4;
        }else if (HouseState[index] == "你家人送的" || "你家人送的".equals(HouseState[index])){
            return 5;
        }else if (HouseState[index] == "对方家人送的" || "对方家人送的".equals(HouseState[index])){
            return 6;
        }else if (HouseState[index].contains("婚前继承的") && HouseState[index].contains("你继承的")){
            return 7;
        }else if (HouseState[index].contains("婚前继承的") && HouseState[index].contains("对方继承的")){
            return 8;
        }else if (HouseState[index].contains("婚后继承的")){
            return 9;
        }
        return 0;
    }

    /*
    * 得到第index辆车子所属类别
    * @param index： 第index辆车子
    * @return int
     */
    public int getCarClassIndex(int index){
        if (CarState[index] == "已经协商好了" || "已经协商好了".equals(CarState[index])){
            return 10;
        }else {
            return 11;
        }
    }

    /*
    * 得到第index个仓库所属类别
    * @param index： 第index个仓库
    * @return int
     */
    public int getStorageClassIndex(int index){
        if (StroageState[index].equalsIgnoreCase("已经协商好了")){
            return 13;
        }else if (StroageState[index].contains("购买的仓库") && StroageState[index].contains("婚前购买")){
            if(StroageDistribute[2 * index + 1] < 0.01) //婚前购买仅有你出钱
                return 14;
            else if(StroageDistribute[2 * index] < 0.01) //婚前购买仅有对方出钱
                return 15;
            else
                return 16; //婚前购买双方都有出资
        }else if (StroageState[index].contains("购买的仓库") && StroageState[index].contains("婚后购买")){
            return 17;
        }else if(StroageState[index] == "你家人送的" || "你家人送的".equals(StroageState[index])){
            return 18;
        }else if(StroageState[index] == "对方家人送的" || "对方家人送的".equals(StroageState[index])){
            return 19;
        }else if(StroageState[index].contains("婚前继承的") && HouseState[index].contains("你继承的")){
            return 20;
        }else if(StroageState[index].contains("婚前继承的") && HouseState[index].contains("对方继承的")){
            return 21;
        }else if(StroageState[index].contains("婚后继承的")){
            return 22;
        }
        return 13;
    }

//    /*
//    * 得到第index知识产权所属类别
//    * @param index： 第index个知识产权
//    * @return int
//     */
//    public int getKnownClassIndex(int index){
//        if (this.knowledgeState[index] == 1){
//
//        }
//    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getError1() {
        return error1;
    }

    public void setError1(String error1) {
        this.error1 = error1;
    }

    public String getError2() {
        return error2;
    }

    public void setError2(String error2) {
        this.error2 = error2;
    }

    public double[] getHouseValue() {
        return HouseValue;
    }

    public void setHouseValue(double[] houseValue) {
        HouseValue = houseValue;
    }

    public double[] getCarValue() {
        return CarValue;
    }

    public void setCarValue(double[] carValue) {
        CarValue = carValue;
    }

    public double[] getStroageValue() {
        return stroageValue;
    }

    public void setStroageValue(double[] stroageValue) {
        this.stroageValue = stroageValue;
    }

    public double[] getAccount() {
        return account;
    }

    public void setAccount(double[] account) {
        this.account = account;
    }

    public double[] getFund() {
        return fund;
    }

    public void setFund(double[] fund) {
        this.fund = fund;
    }

    public double[] getHouseRate() {
        return HouseRate;
    }

    public void setHouseRate(double[] houseRate) {
        HouseRate = houseRate;
    }

    public double[] getStroageRate() {
        return StroageRate;
    }

    public void setStroageRate(double[] stroageRate) {
        StroageRate = stroageRate;
    }

    public double[] getHouseDistribute() {
        return HouseDistribute;
    }

    public void setHouseDistribute(double[] houseDistribute) {
        HouseDistribute = houseDistribute;
    }

    public double[] getStroageDistribute() {
        return StroageDistribute;
    }

    public void setStroageDistribute(double[] stroageDistribute) {
        StroageDistribute = stroageDistribute;
    }

    public String[] getHouseState() {
        return HouseState;
    }

    public void setHouseState(String[] houseState) {
        HouseState = houseState;
    }

    public String[] getCarState() {
        return CarState;
    }

    public void setCarState(String[] carState) {
        CarState = carState;
    }

    public String[] getStroageState() {
        return StroageState;
    }

    public void setStroageState(String[] stroageState) {
        StroageState = stroageState;
    }

    public int[] getKnowledgeState() {
        return knowledgeState;
    }

    public void setKnowledgeState(int[] knowledgeState) {
        this.knowledgeState = knowledgeState;
    }

    public int[] getBelongs() {
        return belongs;
    }

    public void setBelongs(int[] belongs) {
        this.belongs = belongs;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }
}
