package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.util.MStrUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DivorceDaoEight extends MarriageDao {
    public DivorceDaoEight(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMarriagePart() {
        String divorceM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        String sexRep = isMan() ? "女方" : "男方";
        divorceM = divorceM.replaceAll("<rvSex>.*?</rvSex>", sexRep);
        String dcrtRep = dClass.getDescription();
        String errRep = dClass.getError();
        divorceM = divorceM.replaceAll("<description>.*?</description>", dcrtRep);
        divorceM = divorceM.replaceAll("<error>.*?</error>", errRep);
        return divorceM;
    }

    @Override
    public String getMtlPrepare() {
        String mtlPrepareM = mFileUtil.readContent(new File(mPaths.getMtlPreparePath()));

        //将描述替换成对方
        String sexRep = isMan() ? "女方" : "男方";
        mtlPrepareM = mtlPrepareM.replaceAll("<rvSex>.*?</rvSex>", sexRep);

        //根据description和error里边的内容选择替换某部分内容
        String[] tags = {
                "<separation>.*?</separation>",
                "<notLive2>.*?</notLive2>",
                "<violence>.*?</violence>",
                "<abandon>.*?</abandon>",
                "<abuse>.*?</abuse>",
                "<drug>.*?</drug>",
                "<gamble>.*?</gamble>",
                "<bigamy>.*?<bigamy>",
                "<derailment>.*?</derailment>",
                "<defect>.*?</defect>",
                "<liveTog>.*?</liveTog>",
                "<prison>.*?</prison>",
        };
        String[] tagsWithGroup = {
                "<separation>(.*?)</separation>",
                "<notLive2>(.*?)</notLive2>",
                "<violence>(.*?)</violence>",
                "<abandon>(.*?)</abandon>",
                "<abuse>(.*?)</abuse>",
                "<drug>(.*?)</drug>",
                "<gamble>(.*?)</gamble>",
                "<bigamy>(.*?)<bigamy>",
                "<derailment>(.*?)</derailment>",
                "<defect>(.*?)</defect>",
                "<liveTog>(.*?)</liveTog>",
                "<prison>(.*?)</prison>",
        };
        String[] keywords = {
                "感情不和分居",
                "没有在一起生活",
                "家暴行为",
                "遗弃行为",
                "虐待行为",
                "吸毒行为",
                "赌博行为",
                "重婚行为",
                "出轨行为",
                "存在缺陷",
                "与他人同居",
                "监狱坐牢",
        };
        for (int i = 0; i < tags.length; i++){
            String tag = tags[i], keyword = keywords[i];
            if (MStrUtil.contains(keyword, dClass.getDescription()) || MStrUtil.contains(keyword, dClass.getError())){
                //如果有这方面的内容，则去除相应的标签
                Pattern pattern = Pattern.compile(tagsWithGroup[i]);
                Matcher matcher = pattern.matcher(mtlPrepareM);
                //替换准备证据收集部分
                if (matcher.find()){
                    String r = matcher.group(1);
                    mtlPrepareM = mtlPrepareM.replaceAll(tag, "\n" + r);
                }
            }else {
                mtlPrepareM = mtlPrepareM.replaceAll(tag, "");
            }
        }
        return mtlPrepareM;
    }
}
