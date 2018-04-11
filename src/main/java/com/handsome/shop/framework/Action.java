package com.handsome.shop.framework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wangrongjun on 2017/6/17.
 */
public interface Action {

    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
