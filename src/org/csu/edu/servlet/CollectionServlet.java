/*
** CollectionServlet located at the servlet layer, the main function is
* to receive the client request parameters about common property dispute,
* at the same time call the lower module service layer handles the business logic,
* and obtain relevant return results, and returned to the user.
* The current implementation is mainly aimed at the Android end,
* so the return result is json type data, and when the business is extended to the web,
* the return result is rendered by the view parser rendering to complete the adaption of the web.
* In addition, the AlimonyServlet does some error-handling,
* checking the error parameters for passing and processing null values,
* and logging the related errors in the logs.
 */
package org.csu.edu.servlet;

import com.google.gson.Gson;
import org.csu.edu.bean.CollectiveClass;
import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.service.CollectionService;
import org.csu.edu.util.MStrUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CollectionServlet", urlPatterns = {"/collection"})
public class CollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String collStr = request.getParameter("collection");
        Gson mgson = new Gson();
        CollectiveClass cClass = null;
        //将 请求参数封装到SupportClass里边
//        String collStr = "{\"id\":3,\"sex\":\"女方\",\"myIncome\":0.0,\"income\":0.0,\"childCost\":0.0,\"childNum\":0,\"minPay\":550.0,\"maxPay\":30000.0}";
        this.getServletContext().log(collStr);
        try {
            cClass = mgson.fromJson(collStr, CollectiveClass.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (cClass != null){
            this.getServletContext().log(cClass.toString());
        }else {
            this.getServletContext().log("dclass is null");
        }
//        CollectiveClass cClass = new CollectiveClass();
//        cClass.setSex("男方");
//        cClass.setError1("男方存在家暴、吸毒等行为");
//        cClass.setHouseNum(3);
//        cClass.setHouseValue(new double[]{10000.00, 2000.00, 0});
//        cClass.setHouseRate(new double[]{40.0, 50.0, 0});
//        cClass.setHouseDistribute(new double[]{5000.00, 5000.00, 1000.00, 1000.00, 15000.00, 5000.00});
//        cClass.setHouseState(new String[]{"你家人送的", "婚前继承的，你继承的", "已经协商好了"});
//        cClass.setCarValue(new double[]{10000.00, 2000.00, 20000.00});
//        cClass.setCarNum(2);
//        cClass.setCarState(new String[]{"已经协商好了", "婚前继承的，你继承的"});
//        cClass.setStroageValue(new double[]{10000.00, 2000.00, 0});
//        cClass.setShopNum(3);
//        cClass.setStroageDistribute(new double[]{5000.00, 5000.00, 1000.00, 1000.00, 15000.00, 5000.00});
//        cClass.setStroageState(new String[]{"你家人送的", "婚前继承的，你继承的", "已经协商好了"});
//        cClass.setStroageRate(new double[]{40.0, 50.0, 0});
//        cClass.setKnowledgeState(new int[]{1, 1, 0});
//        cClass.setBelongs(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
//        cClass.setAccount(new double[]{100, 100});
//        cClass.setFund(new double[]{100, 0});
//        collStr = new Gson().toJson(cClass);

        String servletRoot = this.getServletContext().getRealPath("/resources");     //得到resource的目录
        String type = "collection";                                                        //type为大类里边的名称
        int n = 5, classIndex = 0;                                         //n为要返回相似案例的篇数，classIndex为请求的类下标
        String dirRoot = MStrUtil.concatPath(servletRoot, type, classIndex);            //得到具体小类的根目录

        CollectionService aService = new CollectionService(dirRoot, cClass);
        MarriageResponse mResponse = aService.getDVResponse(n);

        Gson tgson = new Gson();
        String reStr = tgson.toJson(mResponse);
        PrintWriter pWrite = response.getWriter();
        pWrite.write(reStr);
        pWrite.flush();
        pWrite.close();
    }
}
