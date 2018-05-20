/*
** CustodyService as a middleware server logic function,
* to deal with common property dispute, as the service layer,
* be invoked as a servlet to obtain the lower service interface upwards,
* downwards, call specific custody dao layer to tackle the
* problem of specific logic here, speaking, reading and
* writing database and read and write the file content. At the same time,
* CustodyService also selects different lower-level modules to
* handle the business logic according to the different parameters of servlets.
* Finally, the processing result is obtained from the lower module and
* returned to the servlet layer, which is returned to the user by the servlet.
 */
package org.csu.edu.service;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.dao.sub.custody.*;

public class CustodyService {
    private MarriageDao marriageDao;
    public CustodyService(String dirRoot, DivorceClass dClass){
        int classIndex = dClass.getId();
        switch (classIndex){
            case 0:             //Invoke the first class of custody module
                marriageDao = new Custody(dirRoot, dClass);
                break;
            case 1:             //Invoke the first class of custody module
                marriageDao = new CustodyOne(dirRoot, dClass);
                break;
            case 2:            //Invoke the second class of custody module
                marriageDao = new CustodyTwo(dirRoot, dClass);
                break;
            case 3:            //Invoke the third class of custody module
                marriageDao = new CustodyThree(dirRoot, dClass);
                break;
            case 4:            //Invoke the fourth class of custody module
                marriageDao = new CustodyFour(dirRoot, dClass);
                break;
            case 5:            //Invoke the fifth class of custody module
                marriageDao = new CustodyFive(dirRoot, dClass);
                break;
            case 6:            //Invoke the sixth class of custody module
                marriageDao = new CustodySix(dirRoot, dClass);
                break;
            case 7:            //Invoke the seventh class of custody module
                marriageDao = new CustodySeven(dirRoot, dClass);
                break;
            case 8:            //Invoke the eighth class of custody module
                marriageDao = new CustodyEight(dirRoot, dClass);
                break;
            default:
                marriageDao = new Custody(dirRoot, dClass);
                break;
        }
    }

    public MarriageResponse getDVResponse(int n){
        return marriageDao.getDVResponse(n);
    }
}
