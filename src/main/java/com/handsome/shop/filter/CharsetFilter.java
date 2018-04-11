package com.handsome.shop.filter;

import com.handsome.shop.framework.Config;

import javax.servlet.*;
import java.io.IOException;

/**
 * by wangrongjun on 2017/6/22.
 */
public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("\n--------- load config start ---------");
//        ConfigUtil.read(Config.class, "GuiMeiDatabaseConfig.txt", true);
        System.out.println("username:" + Config.username);
        System.out.println("password:" + Config.password);
        System.out.println("dbName:" + Config.dbName);
        System.out.println("printSql:" + Config.printSql);
        System.out.println("printResult:" + Config.printResult);
        System.out.println("--------- load config finish --------\n");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(Config.charset);
        servletResponse.setCharacterEncoding(Config.charset);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
