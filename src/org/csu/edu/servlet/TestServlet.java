package org.csu.edu.servlet;

//import org.csu.edu.bean.MPaths;
//import org.csu.edu.util.MFileUtil;
//import org.csu.edu.util.MStrUtil;

import org.csu.edu.util.PyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
    public static String RESULT_JSP = "/WEB-INF/jsp/ShowResult.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String dirRoot = this.getServletContext().getRealPath("/resources");
//        String type = "divorce";
//        int n = 5, classIndex = 0;
//        String path = MStrUtil.concatPath(dirRoot, type, classIndex);
//        MPaths mPaths = new MPaths(path);
//        MFileUtil mfUtil = new MFileUtil();
//        String[] results = mfUtil.getRDJudgment(mPaths.getJgmtDir(), n);
//        request.getRequestDispatcher(RESULT_JSP).forward(request, response);
        String result = PyUtil.InvokePy();
//        String result = "congratulation";
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("result", result);
        request.getRequestDispatcher(RESULT_JSP).forward(request, response);
    }
}
