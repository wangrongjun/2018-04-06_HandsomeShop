package com.handsome.shop.framework;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wangrongjun on 2018/5/23.
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            response.getOutputStream().write(ex.getMessage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
