/*
**AlimonyDaoOne used to handle specific support dispute,
* inherit all support AlimonyDao dispute processing module,
* and according to the particularity of the custody dispute,
* for specific problems on special processing,
* AlimonyDaoOne by overloading the relevant methods to implement
* different from AlimonyDao processing, and returns the correct results
 */
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
