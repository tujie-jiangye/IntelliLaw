/*
** CollectionService as a middleware server logic function,
* to deal with common property dispute, as the service layer,
* be invoked as a servlet to obtain the lower service interface upwards,
* downwards, call the specific collection dao layer to
* tackle the problem of specific logic here, speaking,
* reading and writing database and read and write the file content.
 * At the same time, the CollectionService also chooses to
 * invoke different underlying modules to process the business logic,
 * depending on the servlet passing parameters. Finally,
 * the processing result is obtained from the lower module and returned
 * to the servlet layer, which is returned to the user by the servlet.
 */
package org.csu.edu.service;

import org.csu.edu.bean.CollectiveClass;
import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.dao.sub.collection.CollectionDao;

public class CollectionService {
    private MarriageDao marriageDao;
    public CollectionService(String dirRoot, CollectiveClass cClass){
        marriageDao = new CollectionDao(dirRoot, null, cClass);
    }

    public MarriageResponse getDVResponse(int n){
        return marriageDao.getDVResponse(n);
    }
}
