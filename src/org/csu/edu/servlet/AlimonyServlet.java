package org.csu.edu.servlet;

import com.google.gson.Gson;
import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.bean.SupportClass;
import org.csu.edu.service.AlimonyService;
import org.csu.edu.util.MStrUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AlimonyServlet", urlPatterns = {"/alimony"})
public class AlimonyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        Gson mgson = new Gson();
        SupportClass sClass = null;
        //将 请求参数封装到SupportClass里边
        String alinomyStr = request.getParameter("alimony");
//        String alinomyStr = "{\"id\":3,\"sex\":\"女方\",\"myIncome\":0.0,\"income\":0.0,\"childCost\":0.0,\"childNum\":0,\"minPay\":550.0,\"maxPay\":30000.0}";
        this.getServletContext().log(alinomyStr);
        try {
            sClass = mgson.fromJson(alinomyStr, SupportClass.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (sClass != null){
            this.getServletContext().log(sClass.toString());
        }else {
            this.getServletContext().log("dclass is null");
        }

        //获取相关路径,dirRoot->resources目录绝对路径
        String servletRoot = this.getServletContext().getRealPath("/resources");     //得到resource的目录
        String type = "alimony";                                                        //type为大类里边的名称
        int n = 5, classIndex = sClass.getId();                                         //n为要返回相似案例的篇数，classIndex为请求的类下标
        String dirRoot = MStrUtil.concatPath(servletRoot, type, classIndex);            //得到具体小类的根目录

        AlimonyService aService = new AlimonyService(dirRoot, sClass);
        MarriageResponse mResponse = aService.getDVResponse(n);

        //将MarriageResponse封装到json，作为结果返回
        Gson tgson = new Gson();
        String reStr = tgson.toJson(mResponse);
        PrintWriter pWrite = response.getWriter();
        pWrite.write(reStr);
        pWrite.flush();
        pWrite.close();
    }
}
