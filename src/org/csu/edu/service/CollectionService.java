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
