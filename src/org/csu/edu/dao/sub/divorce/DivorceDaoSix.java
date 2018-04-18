package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;

import java.io.File;

public class DivorceDaoSix extends MarriageDao {
    public DivorceDaoSix(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMarriagePart() {
        String divorceM = mFileUtil.readContent(new File(mPaths.getMarriagePath()));
        String replacement = isMan() ? "女方" : "男方"; //得到对方称呼
        divorceM = divorceM.replaceAll("<rvSex>.*?</rvSex>", replacement);
        return divorceM;
    }
}
