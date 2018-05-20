/*
**AlimonyDao as a subclass of dispute all processing support inheritance
* in all disputes MarriageDao processing module, and according to the particularity
* of custody disputes, modify the relevant part of the path, and the relevant part
* of the file name. With respect to the processing of the different parts,
* AlimonyDao returns the correct result by overloading the relevant methods
* to distinguish it from the MarriageDao processing.
 */
package org.csu.edu.dao.sub.alimony;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.SupportClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.util.MStrUtil;

import java.io.File;

public class AlimonyDao extends MarriageDao {
    protected SupportClass sClass;
    public AlimonyDao(String dirRoot, DivorceClass dClass, SupportClass sClass) {
        super(dirRoot, dClass);
        String supportPart = MStrUtil.concatPath(dirRoot, "supportPart.txt");
        this.mPaths.setMarriagePath(supportPart);
        this.sClass = sClass;
    }

    public String getSupportPart(){
        String sptPartM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        //替换对方称呼
        //替换孩子数量
        //替换最小值
        //替换最大值
        String sexM = isMan() ? "女方" : "男方";
        String childNumM = sClass.getChildNum() + "";
        String minPayM = sClass.getMinPay() + "";
        String maxPayM = sClass.getMaxPay() + "";
        sptPartM = sptPartM.replaceAll("<rvSex>.*?</rvSex>", sexM);
        sptPartM = sptPartM.replaceAll("<minPay>.*?</minPay>", minPayM);
        sptPartM = sptPartM.replaceAll("<maxPay>.*?</maxPay>", maxPayM);
        sptPartM = sptPartM.replaceAll("<childNum>.*?</childNum>", childNumM);
        return sptPartM;
    }

    @Override
    public String getHeader() {
        return super.getHeader();
    }

    @Override
    public boolean isMan(){
        String MSex = this.sClass.getSex();
        if(MSex == "男" || MSex == "男方" || MSex.equals("男") || MSex.equals("男方")){
            return true;
        }
        return false;
    }

    @Override
    public String getMarriagePart(){
        return this.getSupportPart();
    }

    @Override
    public String getChance() {
        return null;
    }
}
