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
            case 1:
                marriageDao = new AlimonyDao(dirRoot, null, sClass);
                break;
            case 2:
            case 3:
                marriageDao = new AlimonyDao(dirRoot, null, sClass);
                break;
            default:
                marriageDao = new AlimonyDao(dirRoot, null, sClass);
                break;
        }
    }

    public MarriageResponse getDVResponse(int n){
        return marriageDao.getDVResponse(n);
    }
}
