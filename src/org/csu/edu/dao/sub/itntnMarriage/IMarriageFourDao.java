package org.csu.edu.dao.sub.itntnMarriage;

import org.csu.edu.bean.DivorceClass;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IMarriageFourDao extends  ITNTNMarriageDao {
    public IMarriageFourDao(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMarriagePart() {
        String marriageM = this.mFileUtil.readContent(new File(this.mPaths.getMarriagePath()));
        int classIndex = dClass.getId();
        for (int i = 34; i <= 36; i++){
            String repl = String.format("<replacement%d>.*?</replacement%d>", i, i);
            if (classIndex != i){
                marriageM = marriageM.replaceAll(repl, "");
            }else {
                String contRepl = String.format("<replacement%d>(.*?)</replacement%d>", i, i);
                Pattern pattern = Pattern.compile(contRepl);
                Matcher matcher = pattern.matcher(marriageM);
                //取得相应内容并替换
                if (matcher.find()){
                    String r = matcher.group(1);
                    marriageM = marriageM.replaceAll(repl, r);
                }
            }
        }
        return marriageM;
    }
}
