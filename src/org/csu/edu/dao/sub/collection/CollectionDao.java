package org.csu.edu.dao.sub.collection;

import org.csu.edu.bean.CollectiveClass;
import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.util.MStrUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollectionDao extends MarriageDao {
    private CollectiveClass cClass;
    public CollectionDao(String dirRoot, DivorceClass dClass, CollectiveClass cClass) {
        super(dirRoot, dClass);
        this.cClass = cClass;
        String concretePath = MStrUtil.concatPath(dirRoot, "concrete");
        this.mPaths.setMarriagePath(concretePath);
    }

    /*
    * 由于共同财产这一块在resource结构上有很大的不同，因此
    * 这里提供一些方法来获取相应部分的文件目录或者文件路径
    * 由于结构上的相似，提供统一的方法获取庐江
     */

    public String getConcretePath(int n, String type){
        String prePath = String.format("%s/%s", this.mPaths.getMarriagePath(), type);
        return String.format("%s/%d.txt", prePath, n);
    }

//    public String getHousePath(int n){
//        //相对路径为./concrete/house/n.txt
//        return MStrUtil.concatPath("%s/house/%d.txt", this.mPaths.getMarriagePath(), n);
//    }
//
//    public String getCarPath(int n){
//        return MStrUtil.concatPath("%s/car/%d.txt", this.mPaths.getMarriagePath(), n);
//    }

    /*
    * 由于共同财产的财产部分是由各个部分组成的，所以这里提供
    * 一些方法来分别获取各个部分的内容，分别为house房子、car车子、
    * storage仓库、account存款、fund公积金等
    * 分别对应一下。。。方法
    * getHouse： 得到房子部分
     */

    /*
    * 得到房子的信息，根据不通过类别做了不同处理，不同替换
    * @param n： 房子属于分类的第几类，取值范围为0~9
    * @param houseValue： 房子的价值
    * @param rate： 己方出资比率
    * @return String： 返回这一类房子的具体信息
     */
    public String getHouseContent(int n, double houseValue, double rate){
        /*
        * @param n: 表示房子属于那种情况，
         */
        String hPath = getConcretePath(n, "house");
        String houseM = this.mFileUtil.readContent(new File(hPath));
        switch (n){
            //类0/1/9 不经任何处理，直接返回
            case 0:
            case 1:
            case 9:
                break;
            //类2/5/6要将称呼替换为对方
            case 2:
            case 5:
            case 6:
            case 7:
                String rvSexM = isMan() ? "女方" : "男方";
                houseM = houseM.replaceAll("<rvSex>.*?</rvSex>", rvSexM);
                break;
            //类3/4要计算相对应的补偿金额
            case 3:
            case 4:
                double myValue = houseValue * rate, rvValue = houseValue * (1 - rate), avValue = houseValue * 1.0 / 2;
                //分别替换要给对方的价格rvValue、你可以从中获取myValue、房子拍卖到houseValue、各自得到avValue
                //分别对应的标签为house1、house2、house3、house4
                houseM = houseM.replaceAll("<house1>.*?</house1>", myValue + "");
                houseM = houseM.replaceAll("<house2>.*?</house2>", rvValue + "");
                houseM = houseM.replaceAll("<house3>.*?</house3>", houseValue + "");
                houseM = houseM.replaceAll("<house4>.*?</house4>", avValue + "");
                break;
            default:
                break;

        }
        return houseM;
    }

    /*
    * 得到车子的信息
    * @param n： 车子具体属于哪一类，取值范围为 10/11
    * @param carValue： 车子的价格（价值）
    * @return String
     */
    public String getCarContent(int n, double carValue){
        String carPath = getConcretePath(n, "car");
        String carM = this.mFileUtil.readContent(new File(carPath));
        if (n == 11){
            //第11类要替换carValue或者双方得到的均值，替换标签为carValue2/carValue1
            double half = carValue / 2;
            carM = carM.replaceAll("<car1>.*?</car1>", half + "");
            carM = carM.replaceAll("<car2>.*?</car2>", carValue + "");
        }
        return carM;
    }

    /*
    * 得到存款信息
    * @param n: 存款情况所属的具体类别，取值范围 12
    * @param myAcc： 我方存款数额
    * @param rvAcc： 对方存款金额
    * @return String
     */
    public String getAccountContent(int n, double myAcc, double rvAcc){
        String accPath = getConcretePath(n, "account");
        String accM = this.mFileUtil.readContent(new File(accPath));
        //要替换相对应的本方存款，对方存款、总共存款、平均存款
        //分别对应account1/account2/account3/account4
        double total = myAcc + rvAcc;
        accM = accM.replaceAll("<account1>.*?</account1>", myAcc + "");
        accM = accM.replaceAll("<account2>.*?</account2>", rvAcc + "");
        accM = accM.replaceAll("<account3>.*?</account3>", total + "");
        accM = accM.replaceAll("<account4>.*?</account4>", (total / 2)+ "");
        return accM;
    }

    /*
    * 得到仓库的信息
    * @param n: 仓库情况所属类别
    * @param storageValue： 仓库的价值
    * @param rate： 我方出资比率
    * @return String
     */
    public String getStorageContent(int n, double storageValue, double rate){
        /*
         * @param n: 表示仓库属于那种情况，
         */
        rate /= 100;
        String sPath = getConcretePath(n, "storage");
        String storageM = this.mFileUtil.readContent(new File(sPath));
        switch (n){
            //类13/14/22 不经任何处理，直接返回
            case 13:
            case 14:
            case 22:
                break;
            //类15/18/19/20/21要将称呼替换为对方
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
                String rvSexM = isMan() ? "女方" : "男方";
                storageM = storageM.replaceAll("<rvSex>.*?</rvSex>", rvSexM);
                break;
            //类16/17要计算相对应的补偿金额
            case 16:
            case 17:
                double myValue = storageValue * rate, rvValue = storageValue * (1 - rate), avValue = storageValue / 2;
                //分别替换要给对方的价格rvValue、你可以从中获取myValue、房子拍卖到houseValue、各自得到avValue
                //分别对应的标签为house1、house2、house3、house4
                storageM = storageM.replaceAll("<stroage1>.*?</stroage1>", myValue + "");
                storageM = storageM.replaceAll("<stroage2>.*?</stroage2>", rvValue + "");
                storageM = storageM.replaceAll("<stroage3>.*?</stroage3>", storageValue + "");
                storageM = storageM.replaceAll("<stroage4>.*?</stroage4>", avValue + "");
                break;
            default:
                break;

        }
        return storageM;
    }

    /*
    * 返回公积金结果
    * @param n: 具体所属的类
    * @param myFund： 己方公积金数额
    * @param rvFund： 对方公积金数额
    * @return String
     */
    public String getFundContent(int n, double myFund, double rvFund){
        String fPath = getConcretePath(n, "fund");
        String fundM = this.mFileUtil.readContent(new File(fPath));
        double total = myFund + rvFund;
        double avFund = total / 2;
        //替换己方公积金、对方公积金、总额、均值，标签分别为fund1/fund2/fund3/fund4
        fundM = fundM.replaceAll("<fund1>.*?</fund1>", myFund + "");
        fundM = fundM.replaceAll("<fund2>.*?</fund2>", rvFund + "");
        fundM = fundM.replaceAll("<fund3>.*?</fund3>", total + "");
        fundM = fundM.replaceAll("<fund4>.*?</fund4>", avFund + "");
        return fundM;
    }

    /*
    * 获取除了公积金、房子、车子、仓库以外的财产的信息
    * 这些类的特征是要将称呼替换为对方，或者直接返回
    * F: furniture      家具
    * C: Copyright      著作权
    * P: patent         专利权
    * T: trackMark      商标权
    * P: partnership    合伙企业
    * S: securities     有价证券
    * I: Insurance      保证金
    * @param n: 具体所属类别，取值范围为23/24/25/27/28/29/30
     */
    public String getFCPTPSIContent(int n){
        String tPath = getConcretePath(n, "FCPTPSI");
        String fcptpsiM = this.mFileUtil.readContent(new File(tPath));
        String rvSex = isMan() ? "女方" : "男方";
        fcptpsiM = fcptpsiM.replaceAll("<rvSex>.*?</rvSex>", rvSex);
        return fcptpsiM;
    }

    public String getMulCContent(int n, String reM){
        String mulPath = getConcretePath(n, "multiCollection");
        String mulM = this.mFileUtil.readContent(new File(mulPath));
        mulM = mulM.replaceAll("<error>.*?</error>", reM);
        return mulM;
    }

    @Override
    public boolean isMan() {
        String MSex = cClass.getSex();
        if(MSex == "男" || MSex == "男方" || MSex.equals("男") || MSex.equals("男方")){
            return true;
        }
        return false;
    }

    @Override
    public String getChance() {
        return null;
    }

    @Override
    public String getLegalCosts() {
        return null;
    }

    @Override
    public String getMarriagePart() {
        String wholePath = String.format("%s/%s", this.mPaths.getMarriagePath(), "collectionPart.txt");
        String cltM = this.mFileUtil.readContent(new File(wholePath));
        int[] blgs = this.cClass.getBelongs();

        /*
        * part one，这部分内容主要涉及里边详细内容的替换
         */
        if (blgs[0] == 1){                          //有房子分配的需要,替换房子相关内容
            String houseM = "";
            for (int i = 0; i < this.cClass.getHouseNum(); i++){
                houseM += String.format("第%d套房子<br>", i + 1);
                int classIndex = cClass.getHouseClassIndex(i);
                String thouseM = getHouseContent(classIndex, cClass.getHouseValue()[i], cClass.getHouseRate()[i]);
                houseM += thouseM;
                if (i != this.cClass.getHouseNum() - 1){
                    houseM += "<br>";
                }
            }
            //将houseConcrete部分替换为具体内容
            cltM = cltM.replaceAll("<houseConcrete>.*?</houseConcrete>", houseM);
        }

        if (blgs[1] == 1){                          //是否有存款需要处理
            String carM = "";
            for (int i = 0; i < this.cClass.getCarNum(); i++){
                carM += String.format("第%d辆车子<br>", i + 1);
                int classIndex = cClass.getCarClassIndex(i);
                String tcarM = getCarContent(classIndex, this.cClass.getCarValue()[i]);
                carM += tcarM;
                if (i != this.cClass.getCarNum() - 1){
                    carM += "<br>";
                }
            }
            cltM = cltM.replaceAll("<carConcrete>.*?</carConcrete>", carM);
        }

        if (blgs[2] == 1){                          //是否有存款需要处理
            String accountM = getAccountContent(12, cClass.getAccount()[0], cClass.getAccount()[1]);
            cltM = cltM.replaceAll("<accountConcrete>.*?</accountConcrete>", accountM);
        }

        if (blgs[3] == 1){                          //是否有仓库需要处理
            String storageM = "";
            for (int i = 0; i < this.cClass.getShopNum(); i++){
                storageM += String.format("第%d个仓库<br>", i + 1);
                int classIndex = cClass.getStorageClassIndex(i);
                String tStrM = getStorageContent(classIndex, this.cClass.getStroageValue()[i], this.cClass.getStroageRate()[i]);
                storageM += tStrM;
                if (i != this.cClass.getShopNum() - 1){
                    storageM += "<br>";
                }
            }
            cltM = cltM.replaceAll("<storageConcrete>.*?</storageConcrete>", storageM);
        }

        if (blgs[4] == 1){                          //是否有家具电器需要处理，家具只有一类，类下标为27
            String furnitureM = getFCPTPSIContent(27);
            cltM = cltM.replaceAll("<furnitureConcrete>.*?</furnitureConcrete>", furnitureM);
        }

        if (blgs[5] == 1){                          //是否有知识产权需要分配
            boolean isbr = false;
            if (this.cClass.getKnowledgeState()[0] == 1){       //有著作权需要处理，著作权下标23
                String cprtM = getFCPTPSIContent(23);
                cltM = cltM.replaceAll("<copyrightConcrete>.*?</copyrightConcrete>", cprtM);
                isbr = true;
            }else {
                cltM = cltM.replaceAll("<copyrightConcrete>.*?</copyrightConcrete>", "");
            }
            if (this.cClass.getKnowledgeState()[1] == 1){       //有专利权需要处理，专利权下标24
                String patentM = getFCPTPSIContent(24);
                if (isbr){
                    patentM = "<br>" + patentM;
                }
                cltM = cltM.replaceAll("<patentConcrete>.*?</patentConcrete>", patentM);
                isbr = true;
            }else {
                cltM = cltM.replaceAll("<patentConcrete>.*?</patentConcrete>", "");
            }
            if (this.cClass.getKnowledgeState()[2] == 1){       //有商标权需要处理，商标权下标25
                String trackMM = getFCPTPSIContent(25);
                if (isbr){
                    trackMM = "<br>" + trackMM;
                }
                cltM = cltM.replaceAll("<trackMarkConcrete>.*?</trackMarkConcrete>", trackMM);
            }else {
                cltM = cltM.replaceAll("<trackMarkConcrete>.*?</trackMarkConcrete>", "");
            }
        }

        if (blgs[6] == 1){                                      //是否有公积金需要分配，公积金只有一类，下标为26
            String fundM = getFundContent(26, this.cClass.getFund()[0], this.cClass.getFund()[1]);
            cltM = cltM.replaceAll("<fundConcrete>.*?</fundConcrete>", fundM);
        }

        for (int i = 0; i < 3; i++){                            //是否有合伙企业、公司股份要分配，是否有有价证券、保险金要分配
            int classIndex[] = {28, 29, 30};                    //类下标分别为28/29/30
            String replaceM[] = {"<partnerConcrete>.*?</partnerConcrete>", "<securitiesConcrete>.*?</securitiesConcrete>",
                    "<insuranceConcrete>.*?</insuranceConcrete>"};
            if (blgs[7 + i] == 1){
                String reContent = getFCPTPSIContent(classIndex[i]);
                cltM = cltM.replaceAll(replaceM[i], reContent);
            }
        }

        //多分财产的两种情况
        if (cClass.getError1() != null && cClass.getError1() != "" && !"".equals(cClass.getError1())
                && (cClass.getError2() == null || cClass.getError2() == "" || "".equals(cClass.getError2()))){
            String mulM = getMulCContent(31, cClass.getError1());
            cltM = cltM.replaceAll("<multiCollectionPart>.*?</multiCollectionPart>", mulM);
        }else if (cClass.getError2() != null && cClass.getError2() != "" && !"".equals(cClass.getError2())
                &&(cClass.getError1() == null || cClass.getError1() == "" || "".equals(cClass.getError1()))){
            String mulM = getMulCContent(32, cClass.getError2());
            cltM = cltM.replaceAll("<multiCollectionPart>.*?</multiCollectionPart>", mulM);
        }

        /*
        * part two第二部分
        * 主要将没有出现请求列表里边的内容删除，
        * 将出现在请求列表里边的内容去除两端的标签
         */

        String contentRe[] = {
                "<housePart>(.*?)</housePart>",
                "<carPart>(.*?)</carPart>",
                "<accountPart>(.*?)</accountPart>",
                "<storagePart>(.*?)</storagePart>",
                "<furniturePart>(.*?)</furniturePart>",
                "<knownlegePart>(.*?)</knownlegePart>",
                "<fundPart>(.*?)</fundPart>",
                "<insurancePart>(.*?)</insurancePart>",
                "<partnerPart>(.*?)</partnerPart>",
                "<securitiesPart>(.*?)</securitiesPart>"
        };
        String replaceRe[] = {
                "<housePart>.*?</housePart>",
                "<carPart>.*?</carPart>",
                "<accountPart>.*?</accountPart>",
                "<storagePart>.*?</storagePart>",
                "<furniturePart>.*?</furniturePart>",
                "<knownlegePart>.*?</knownlegePart>",
                "<fundPart>.*?</fundPart>",
                "<insurancePart>.*?</insurancePart>",
                "<partnerPart>.*?</partnerPart>",
                "<securitiesPart>.*?</securitiesPart>"
        };

        for (int i = 0; i < 10; i++){
            if (blgs[i] == 1){                              //如果含有相关内容，则去除这部分两端的标签，
                Pattern pattern = Pattern.compile(contentRe[i]);
                Matcher matcher = pattern.matcher(cltM);
                if (matcher.find()){
                    String r = matcher.group(1);
                    cltM = cltM.replaceFirst(replaceRe[i], "<br>" + r);
                }
            }else {                                         //否则，将这一部分删除
                cltM = cltM.replaceAll(replaceRe[i], "");
            }
        }

        return cltM;
    }

}
