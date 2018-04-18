package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;

import java.io.File;

public class DivorceDaoFour extends MarriageDao {
    public DivorceDaoFour(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getMtlPrepare() {
        String mtlPrepareM = mFileUtil.readContent(new File(mPaths.getMtlPreparePath()));
        String replacement = isMan() ? "女方" : "男方";
        mtlPrepareM = mtlPrepareM.replaceAll("<rvSex>.*?</rvSex>", replacement);
        return mtlPrepareM;
    }
}
