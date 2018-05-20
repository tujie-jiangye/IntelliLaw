/*
** IMarriageService as a middleware server logic function,
* which is used to deal with transnational marriage dispute,
* as the service layer, be invoked as a servlet to obtain the
* lower service interface upwards, downwards, call the specific
* international marriage dao layer to tackle the problem of
* specific logic here, speaking, reading and writing database
* and read and write the file content. At the same time,
* IMarriageService also selects different lower-level modules
* to handle the business logic, depending on the servlet passing parameters.
* Finally, the processing result is obtained from the
* lower module and returned to the servlet layer,
* which is returned to the user by the servlet.
 */
package org.csu.edu.service;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.dao.sub.itntnMarriage.IMarriageDaoTwoDao;
import org.csu.edu.dao.sub.itntnMarriage.IMarriageFourDao;
import org.csu.edu.dao.sub.itntnMarriage.IMarriageThreeDao;
import org.csu.edu.dao.sub.itntnMarriage.ITNTNMarriageDao;

public class IMarriageService {
    private MarriageDao marriageDao;

    public IMarriageService(String dirRoot, DivorceClass dclass, int classIndex){
        switch (classIndex){
            case 0:
            case 1:         //Invoke the divorce dispute processing module.
                marriageDao = new ITNTNMarriageDao(dirRoot, dclass);
                break;
            case 2:         //Invoke the common property dispute processing module.
                marriageDao = new IMarriageDaoTwoDao(dirRoot, dclass);
                break;
            case 3:         //Invoke the maintenance fee dispute processing module.
                marriageDao = new IMarriageThreeDao(dirRoot, dclass);
                break;
            case 4:         //Invoke the custody dispute processing module.
                marriageDao = new IMarriageFourDao(dirRoot, dclass);
                break;
            default:
                marriageDao = new ITNTNMarriageDao(dirRoot, dclass);
        }
    }

    public MarriageResponse getDVResponse(int n){
        return marriageDao.getDVResponse(n);
    }
}
