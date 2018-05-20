/*
** AlimonyService as a middleware server logic function,
* used to handle support dispute, as the service layer,
* be invoked as a servlet to obtain lower level service interface,
* upward downward, call the concrete dao layer to tackle the
* problem of specific logic here, speaking, reading and writing
* database and read and write the file content. At the same time,
* AlimonyService also selects different lower-level modules to
* handle the business logic according to the different parameters
* of servlets. Finally, the processing result is obtained from the
* lower module and returned to the servlet layer,
* which is returned to the user by the servlet.
 */
package org.csu.edu.service;

import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.bean.SupportClass;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.dao.sub.alimony.AlimonyDao;

public class AlimonyService {
    private MarriageDao marriageDao;
    public AlimonyService(String dirRoot, SupportClass sClass){
        int classIndex = sClass.getId();
        switch (classIndex){
            case 0:
            case 1:         //Invoke the first class Alimony
                marriageDao = new AlimonyDao(dirRoot, null, sClass);
                break;
            case 2:
            case 3:         //Invoke the second class Alimony
                marriageDao = new AlimonyDao(dirRoot, null, sClass);
                break;
            default:        //Invoke the third class Alimony
                marriageDao = new AlimonyDao(dirRoot, null, sClass);
                break;
        }
    }

    public MarriageResponse getDVResponse(int n){
        return marriageDao.getDVResponse(n);
    }
}
