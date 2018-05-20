package org.csu.edu.dao.sub.itntnMarriage;

import org.csu.edu.bean.DivorceClass;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IMarriageDaoTwoDao extends ITNTNMarriageDao {
    public IMarriageDaoTwoDao(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMarriagePart() {
        String marriageM = this.mFileUtil.readContent(new File(this.mPaths.getMarriagePath()));
        int classIndex = dClass.getId();
        for (int i = 2; i <= 22; i++){
            String repl = String.format("<replacement%d>.*?</replacement%d>", i, i);
            if (i != classIndex){
                marriageM = marriageM.replaceAll(repl, "");
            }else {
                String contRepl = String.format("<replacement%d>(.*?)</replacement%d>", i, i);
                Pattern pattern = Pattern.compile(contRepl);
                Matcher matcher = pattern.matcher(marriageM);
                //替换准备证据收集部分
                if (matcher.find()){
                    String r = matcher.group(1);
                    marriageM = marriageM.replaceAll(repl, r);
                }
            }
        }
        return marriageM;
    }
}
