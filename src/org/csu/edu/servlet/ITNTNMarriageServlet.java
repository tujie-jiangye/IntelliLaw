/*
** ITNTNMarriageServlet located at the servlet layer,
* the main function is to receive the client request parameters about
* the transnational marriage dispute, at the same time call the
* lower module service layer handles the business logic,
* and obtain relevant return results, and returned to the user.
* The current implementation is mainly aimed at the Android end,
* so the return result is json type data, and when the business is extended to the web,
* the return result is rendered by the view parser rendering to
* complete the adaption of the web. In addition,
* the ITNTNMarriageServlet does some error-handling,
* checking the error parameters for passing and processing null values,
* and logging the related errors in the logs.
 */
package org.csu.edu.servlet;

import com.google.gson.Gson;
import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.service.IMarriageService;
import org.csu.edu.util.MStrUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ITNTNMarriageServlet", urlPatterns = {"/itntnMarriage"})
public class ITNTNMarriageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        Gson mgson = new Gson();
        DivorceClass dClass = null;
//
//        dClass = new DivorceClass();
//        dClass.setSex("男方");
//        dClass.setId(36);

        //获取请求参数，并封装到DivorceClass中
        String itntnStr = request.getParameter("itntnMarriage");    //要修改为正确的键值
        this.getServletContext().log(itntnStr);
        try {
            dClass = mgson.fromJson(itntnStr, DivorceClass.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (dClass != null){
            this.getServletContext().log(dClass.toString());
        }else {
            this.getServletContext().log("dclass is null");
        }

        String servletRoot = this.getServletContext().getRealPath("/resources");     //得到resource的目录
        String type = "internationalMarriage";                                                        //type为大类里边的名称
        int n = 5, classIndex = getClassIndex(dClass.getId());                          //n为要返回相似案例的篇数，classIndex为请求的类下标
        String dirRoot = MStrUtil.concatPath(servletRoot, type, classIndex);            //得到具体小类的根目录

        IMarriageService iMarriageService = new IMarriageService(dirRoot, dClass, classIndex);
        MarriageResponse mResponse = iMarriageService.getDVResponse(n);

        Gson tgson = new Gson();
        String reStr = tgson.toJson(mResponse);
        PrintWriter pWrite = response.getWriter();
        pWrite.write(reStr);
        pWrite.flush();
        pWrite.close();
    }

    public static int getClassIndex(int id){
        if (id == 0){
            return 0;
        }else if (id == 1){
            return 1;
        }else if (id >= 2 && id <= 22){
            return 2;
        }else if (id >= 23 && id <= 33){
            return 3;
        }else {
            return 4;
        }
    }
}
