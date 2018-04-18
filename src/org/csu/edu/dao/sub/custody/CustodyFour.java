package org.csu.edu.dao.sub.custody;

import org.csu.edu.bean.DivorceClass;

import java.io.File;

public class CustodyFour extends Custody {
    public CustodyFour(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getChild() {
        String chdPathM = mFileUtil.readContent(new File(this.mPaths.getMarriagePath()));
        //第五类，即类别四有description要替换
        //替换description内容
        chdPathM = chdPathM.replaceAll("<description>.*?</description>", this.dClass.getDescription());
        return chdPathM;
    }
}
