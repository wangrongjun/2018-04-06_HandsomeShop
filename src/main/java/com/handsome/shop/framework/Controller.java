package com.handsome.shop.framework;

import com.wangrj.java_lib.java_util.LogUtil;
import com.wangrj.java_lib.java_util.TextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wangrongjun on 2017/6/17.
 */
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // login.do -> com.guimei.shop.action.LoginAction
        String path = request.getServletPath();// /login.do
        path = path.substring(1);
        path = path.substring(0, 1).toUpperCase() + path.substring(1, path.length() - 3);
        String packageName = "com.handsome.shop.action." + path + "Action";
        LogUtil.print(packageName);
        try {
            Class actionClass = Class.forName(packageName);
            Action action = (Action) actionClass.newInstance();
            String newPath = action.execute(request, response);
            if (TextUtil.isEmpty(newPath)) {
                return;
            }
            if (newPath.startsWith("-")) {
                response.sendRedirect(newPath.substring(1));
            } else {
                request.getRequestDispatcher(newPath).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.print(e.toString());
            response.sendRedirect("error_404.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
