package org.csu.edu.servlet;

import com.google.gson.Gson;
import org.csu.edu.bean.DivorceClass;
import org.csu.edu.bean.MarriageResponse;
import org.csu.edu.service.CustodyService;
import org.csu.edu.util.MStrUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CustodyServlet", urlPatterns = {"/custody"})
public class CustodyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        Gson mgson = new Gson();
        DivorceClass dClass = null;
        //获取请求参数，并封装到DivorceClass中
        String divorceStr = request.getParameter("custody");    //要修改为正确的键值
//        String divorceStr = "{\"description\":\"你们双方因感情不和分居两年\",\"error\":\"、且对方有家暴行为、对方有非常严重的虐待行为\",\"id\":7,\"sex\":\"男方\"}";
        this.getServletContext().log(divorceStr);
        try {
            dClass = mgson.fromJson(divorceStr, DivorceClass.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (dClass != null){
            this.getServletContext().log(dClass.toString());
        }else {
            this.getServletContext().log("dclass is null");
        }


//        int classIndexM = Integer.valueOf(request.getParameter("index"));


        //先构造一个假的DivorceClass
//        DivorceClass dClass = new DivorceClass();
//        dClass.setId(5);
//        dClass.setSex("男");                       //这个要确定为男方还是男
//        String dcrt = "双方感情不和分居两年,没有在一起生活,监狱坐牢";
//        String err = "对方有吸毒行为,对方有家暴行为";
//        dClass.setDescription(dcrt);
//        dClass.setError(err);

        //获取相关路径,dirRoot->resources目录绝对路径
        //jgmtPath->judgements给定这一类下的相似案例路径
        String servletRoot = this.getServletContext().getRealPath("/resources");     //得到resource的目录
        String type = "custody";                                                        //type为大类里边的名称
        int n = 5, classIndex = dClass.getId();                                         //n为要返回相似案例的篇数，classIndex为请求的类下标
        String dirRoot = MStrUtil.concatPath(servletRoot, type, classIndex);            //得到这一小类的根目录

        CustodyService cSerivce = new CustodyService(dirRoot, dClass);
        MarriageResponse dResponse = cSerivce.getDVResponse(n);

        Gson tgson = new Gson();
        String reStr = tgson.toJson(dResponse);
        PrintWriter pWrite = response.getWriter();
        pWrite.write(reStr);
        pWrite.flush();
        pWrite.close();
    }
}
