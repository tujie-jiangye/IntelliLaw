/*
**DivorceDao as a subclass of all deal with divorce dispute,
* inherited by all disputes MarriageDao processing module,
* and according to the particularity of divorce disputes,
* modify the relevant part of the path, and the relevant part of the file name.
* With respect to the processing of the non-different parts,
* the DivorceDao returns the correct result by reloading the
* relevant method to achieve the difference between the processing
* of the system. In addition, the DivorceDao also adds additional
* functions to deal with this kind of problems according
* to the particularity of such disputes.
 */
package org.csu.edu.dao.sub.divorce;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.util.MStrUtil;

public class DivorceDaoZero extends MarriageDao {
    public DivorceDaoZero(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
        String childPartPath = MStrUtil.concatPath(dirRoot, "divorcePart.txt");
        this.mPaths.setMarriagePath(childPartPath);
    }

    /*
    ** Revise the overview of the divorce section.
     */
    public String getDivorce(){
        return super.getMarriagePart();
    }

    @Override
    public String getMarriagePart() {
        return this.getDivorce();
    }
}
