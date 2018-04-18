package org.csu.edu.dao.sub.alimony;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.SupportClass;

import java.io.File;

public class AlimonyDaoOne extends AlimonyDao {
    public AlimonyDaoOne(String dirRoot, DivorceClass dClass, SupportClass sClass) {
        super(dirRoot, dClass, sClass);
    }

    @Override
    public String getSupportPart() {
        String sptPartM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        return sptPartM;
    }
}
