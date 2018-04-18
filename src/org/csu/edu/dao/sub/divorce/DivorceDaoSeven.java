package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;

import java.io.File;

public class DivorceDaoSeven extends MarriageDao {
    public DivorceDaoSeven(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMarriagePart() {
        String divorceM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        String replacement = isMan() ? "女方" : "男方";
        divorceM = divorceM.replaceAll("<rvSex>.*?</rvSex>", replacement);
        return divorceM;
    }
}
