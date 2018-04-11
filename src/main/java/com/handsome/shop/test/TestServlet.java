package com.handsome.shop.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * by wangrongjun on 2017/9/21.
 */
@MultipartConfig
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("uploadFile");
        // form-data; name="uploadFile"; filename="1.png"
        String disposition = part.getHeader("content-disposition");
        int pos = disposition.lastIndexOf("=");
        String fileName = disposition.substring(pos + 2, disposition.length() - 1);// 1.png
        String filePath = req.getServletContext().getRealPath("/admin/upload/" + fileName);
        part.write(filePath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
