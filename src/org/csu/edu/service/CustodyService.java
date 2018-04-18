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
            case 0:
                marriageDao = new Custody(dirRoot, dClass);
                break;
            case 1:
                marriageDao = new CustodyOne(dirRoot, dClass);
                break;
            case 2:
                marriageDao = new CustodyTwo(dirRoot, dClass);
                break;
            case 3:
                marriageDao = new CustodyThree(dirRoot, dClass);
                break;
            case 4:
                marriageDao = new CustodyFour(dirRoot, dClass);
                break;
            case 5:
                marriageDao = new CustodyFive(dirRoot, dClass);
                break;
            case 6:
                marriageDao = new CustodySix(dirRoot, dClass);
                break;
            case 7:
                marriageDao = new CustodySeven(dirRoot, dClass);
                break;
            case 8:
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
