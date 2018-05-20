package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.util.MStrUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DivorceDaoFive extends MarriageDao {
    public DivorceDaoFive(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMarriagePart() {
        /*
        * 将性别替换成对方
        * 将description和error分别替换
         */
        String divorceM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        String rvSexM = isMan() ? "女方" : "男方";
        String sexM = isMan() ? "男方" : "女方";
        String replacementD = dClass.getDescription();
        String replacementE = dClass.getError();
        divorceM = divorceM.replaceAll("<rvSex>.*?</rvSex>", rvSexM);
        divorceM = divorceM.replaceAll("<sex>.*?</sex>", sexM);
        divorceM = divorceM.replaceAll("<description>.*?</description>", replacementD);
        divorceM = divorceM.replaceAll("<error>.*?</error>", replacementE);
        return divorceM;
    }

    @Override
    public String getMtlPrepare() {
        String mtlPrepareM = mFileUtil.readContent(new File(mPaths.getMtlPreparePath()));
        //先把性别替换
        String sexM = isMan() ? "男方" : "女方";
        mtlPrepareM = mtlPrepareM.replaceAll("<sex>.*?</sex>", sexM);
        //如果没有出现在description中的内容就从文本中清除，如果存在，则清除相对应的标签
        //首先替换出现在相关证据收集指南里边的内容
        //替换description中的内容
//        String[] tags = {"<separation>.*?</separation>", "<notLive2>.*?</notLive2>", "<prison>.*?</prison>", "<defect>.*?</defect>"};
//        String[] dKeywords = {"感情不和分居两年", "没有在一起生活", "监狱坐牢", "身体缺陷或者存在疾病"};
//        for (int i = 0; i < tags.length; i++){
//            if (!MStrUtil.contains(dKeywords[i], dClass.getDescription())){
//                mtlPrepareM.replaceFirst(tags[i], "");
//            }else {
//                Pattern pattern = Pattern.compile(tags[i]);
//                Matcher matcher = pattern.matcher(mtlPrepareM);
//                if (matcher.find()){
//                    mtlPrepareM.replaceFirst(tags[i], "\n" + matcher.group(1));
//                }
//            }
//        }
//        //替换而error的内容
//        String[] tages = {"<violence>.*?</violence>", "<abandon>.*?</abandon>", "<abuse>.*</abuse>",
//                "<drug>.*?</drug>", "<gamble>.*?</gamble>", "<bigamy>.*?</bigamy>", "<derailment>.*?</derailment>"};
//        String[] ekeywords = {"家暴行为", "遗弃行为", "虐待行为", "吸毒行为", "赌博行为", "重婚行为", "出轨行为"};
//        for (int i = 0; i < tages.length; i++){
//            if (!MStrUtil.contains(ekeywords[i], dClass.getError())){
//                mtlPrepareM.replaceFirst(tags[i], "");
//            }else {
//                Pattern pattern = Pattern.compile(tages[i]);
//                Matcher matcher = pattern.matcher(mtlPrepareM);
//                if (matcher.find()){
//                    mtlPrepareM.replaceFirst(tages[i], "\n" + matcher.group(1));
//                }
//            }
//        }


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
                    mtlPrepareM = mtlPrepareM.replaceFirst(tag, "<br>" + r);
                }
                //替换诉前提醒部分
                if(matcher.find()){
                    String r = matcher.group(1);
                    mtlPrepareM = mtlPrepareM.replaceAll(tag, "<br>" + r);
                }
            }else {
                mtlPrepareM = mtlPrepareM.replaceAll(tag, "");
            }
        }
        return mtlPrepareM;
    }
}
