package org.csu.edu.dao;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.DivorceResponse;
import org.csu.edu.bean.MPaths;
import org.csu.edu.util.MFileUtil;

import java.io.File;

public class MarriageDao {
    protected DivorceClass dClass;
    protected MFileUtil mFileUtil;
    protected MPaths mPaths;

    public MarriageDao(String dirRoot, DivorceClass dClass){
        this.mPaths = new MPaths(dirRoot);
        this.mFileUtil = new MFileUtil();
        this.dClass = dClass;
    }

    //判断是男还是女
    public boolean isMan(){
        String MSex = dClass.getSex();
        if(MSex == "男" || MSex == "男方" || MSex.equals("男") || MSex.equals("男方")){
            return true;
        }
        return false;
    }

    //读取胜诉概率文件返回结果
    public String getChance(){
        //String chance = PyUtil.InvokePy();  //调用py
        String content = mFileUtil.readContent(new File(mPaths.getChancePath()));
        String result = content.trim().split(" ")[0];
        //chance = result;
        return  result;
    }

    //得到相似案例
    public String[] getSimilarCases(int n){
        String[] cases = mFileUtil.getRDJudgment(mPaths.getJgmtDir(), n);
        return cases;
    }

    //得到头部信息
    public String getHeader(){
        String headerM = mFileUtil.readContent(new File(mPaths.getHeaderPath()));
        String replacement = isMan() ? "先生" : "女士";
        headerM = headerM.replaceAll("<sex>.*?</sex>", replacement);
        return headerM;
    }

    //得到能不能离婚部分
    public String getMarriagePart(){
        String divorceM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        String replacement = isMan() ? "女方" : "男方";
        divorceM = divorceM.replaceAll("<rvSex>.*?</rvSex>", replacement);
        return  divorceM;
    }

    //得到材料准备部分
    public String getMtlPrepare(){
        String prepareM = mFileUtil.readContent(new File(mPaths.getMtlPreparePath()));
        return prepareM;
    }

    //得到诉讼费
    public String getLegalCosts(){
        String legalCostsM = mFileUtil.readContent(new File(mPaths.getLegalCostPath()));
        return legalCostsM;
    }

    //得到起诉状
    public String getIndictment(){
        String indictmentM = mFileUtil.readContent(new File(mPaths.getIndictmentPath()));
        return indictmentM;
    }

    //得到行动建议
    public String getAction(){
        String actionM = mFileUtil.readContent(new File(mPaths.getActionPath()));
        return actionM;
    }

    //得到相关法律
    public String getRelatedRaw(){
        String rawM = mFileUtil.readContent(new File(mPaths.getRelatedLawPath()));
        return rawM;
    }

    //定义一个外观，统一调用
    public DivorceResponse getDVResponse(int n){
        return new DivorceResponse(
                getChance(),
                getHeader(),
                getMarriagePart(),
                getMtlPrepare(),
                getLegalCosts(),
                getIndictment(),
                getAction(),
                getSimilarCases(n),
                getRelatedRaw()
        );
    }
}
