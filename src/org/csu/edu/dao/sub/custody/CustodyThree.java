package org.csu.edu.dao.sub.custody;

import org.csu.edu.bean.DivorceClass;

import java.io.File;

public class CustodyThree extends CustodyZero {
    public CustodyThree(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getChild() {
        String chdPathM = mFileUtil.readContent(new File(this.mPaths.getMarriagePath()));
        //第四类，即类别三有description要替换
        //替换description内容
        chdPathM = chdPathM.replaceAll("<description>.*?</description>", this.dClass.getDescription());
        return chdPathM;
    }
}
