package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.util.MStrUtil;

public class DivorceDaoZero extends MarriageDao {
    public DivorceDaoZero(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
        String childPartPath = MStrUtil.concatPath(dirRoot, "childPart.txt");
        this.mPaths.setMarriagePath(childPartPath);
    }

    public String getDivorce(){
        return super.getMarriagePart();
    }

    @Override
    public String getMarriagePart() {
        return this.getDivorce();
    }
}
