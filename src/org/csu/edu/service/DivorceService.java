package org.csu.edu.service;

import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.DivorceResponse;
import org.csu.edu.dao.MarriageDao;
import org.csu.edu.dao.sub.divorce.*;

public class DivorceService {
    private MarriageDao marriageDao;

    public DivorceService(String dirRoot, DivorceClass dClass){
        int classIndex = dClass.getId();
        switch (classIndex){
            case 0:                                 //第一类，特征“双方都想离婚”
                marriageDao = new DivorceDaoZero(dirRoot, dClass);
                break;
            case 1:                                 //第二类，特征"女方在生完孩子一年后、只有男方想离婚、咨询方为男性"
                marriageDao = new DivorceDaoOne(dirRoot, dClass);
                break;
            case 2:                                 //第三类，特征"女方在生完孩子一年后、只有对方想离婚、咨询方为女方"
                marriageDao = new DivorceDaoTwo(dirRoot, dClass);
                break;
            case 3:                                 //第四类，特征"只有咨询方想离婚、对方是现役军人、且无重大过错"
                marriageDao = new DivorceDaoThree(dirRoot, dClass);
                break;
            case 4:                                 //第五类，特征"只有咨询方想离婚、对方无重大过错"
                marriageDao = new DivorceDaoFour(dirRoot, dClass);
                break;
            case 5:                                 //第六类，特征"只有咨询方想离婚、对方有重大过错"
                marriageDao = new DivorceDaoFive(dirRoot, dClass);
                break;
            case 6:                                 //第七类，特征"只有咨询方对象想离婚、咨询者是现役军人且无重大过错"
                marriageDao = new DivorceDaoSix(dirRoot, dClass);
                break;
            case 7:                                 //第八类，特征"只有咨询方对象想离婚、咨询者无重大过错"
                marriageDao = new DivorceDaoSeven(dirRoot, dClass);
                break;
            case 8:                                 //第九类，特征"只有咨询方对方想离婚、咨询方有重大过错"
                marriageDao = new DivorceDaoEight(dirRoot, dClass);
                break;
            default:                                //默认返回第一类
                marriageDao = new MarriageDao(dirRoot, dClass);
                break;
        }
    }

    public DivorceResponse getDVResponse(int n){
        return marriageDao.getDVResponse(n);
    }
}
