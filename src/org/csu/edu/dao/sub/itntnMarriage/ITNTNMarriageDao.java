/*
** ITNTNMarriageDao as a subclass of all international marriage disputes,
* inheritance in all disputes MarriageDao processing module,
* and according to the particularity of transnational marriage disputes,
* modify the relevant part of the path, and the relevant part of the file name.
* With respect to the processing of the different parts, ITNTNMarriageDao
* returns the correct result by overloading the relevant methods to distinguish
* it from the MarriageDao. In addition, ITNTNMarriageDao also adds
* other functions to deal with this kind of problems according to
* the particularity of such disputes. Unlike other similar processing modules,
* transnational marriage includes several other functions,
* which are more loaded logically, and can effectively reduce the
* complexity of the problem by planning its four subclasses. Secondly,
* reloading public processing methods can improve the reusability of software.
 */
package org.csu.edu.dao.sub.itntnMarriage;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.dao.MarriageDao;

import java.io.File;

public class ITNTNMarriageDao extends MarriageDao {
    public ITNTNMarriageDao(String dirRoot, DivorceClass dClass) {
        super(dirRoot, dClass);
    }

    @Override
    public String getChance() {
        return null;
    }

    @Override
    public String getHeader() {
        String headerM = this.mFileUtil.readContent(new File(this.mPaths.getHeaderPath()));
        return headerM;
    }
}
