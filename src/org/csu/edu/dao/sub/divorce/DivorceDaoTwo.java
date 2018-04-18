package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;

import java.io.File;

public class DivorceDaoTwo extends MarriageDao {
    public DivorceDaoTwo(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMarriagePart() {
        String divorceM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        String replacement = String.join("，", dClass.getDescription());
        divorceM = divorceM.replaceAll("<description>.*?</discription", replacement);
        return divorceM;
    }
}
