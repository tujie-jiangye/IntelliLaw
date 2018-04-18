package org.csu.edu.dao.sub.custody;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.util.MStrUtil;

import java.io.File;

public class Custody extends MarriageDao {
    public Custody(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
        String childPath = MStrUtil.concatPath(dirRoot, "childPart.txt");
        this.mPaths.setMarriagePath(childPath);
    }

    public String getChild(){
        String chdPathM = mFileUtil.readContent(new File(this.mPaths.getMarriagePath()));
        String sexM = isMan() ? "男方" : "女方";
        String rvSexM = isMan() ? "女方" : "男方";
        chdPathM = chdPathM.replaceAll("<sex>.*?</sex>", sexM);
        chdPathM = chdPathM.replaceAll("<rvSex>.*?</rvSex>", rvSexM);
        chdPathM = chdPathM.replaceAll("<error>.*?</error>", this.dClass.getError());
        return chdPathM;
    }

    @Override
    public String getMarriagePart() {
        return this.getChild();
    }
}
