/*
** Custody as all processing subclasses of Custody dispute,
* inherited by all disputes MarriageDao processing module,
* and according to the particularity of Custody disputes,
* modify the relevant part of the path, and the relevant part of the file name.
* For the processing of the non-different parts, the method is
* implemented by overloading related methods to achieve the difference
* between the processing of the treatment and return the correct result.
* In addition, according to the particularity of this kind of dispute,
* the author adds another function to deal with this kind of problem in a special way.
 */
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

    /*
    ** get the path of the major description of children
     */
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
