package com.handsome.shop.framework;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wangrongjun on 2018/5/23.
 */
public class ExceptionHandler extends DefaultHandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String errorMsg = null;
        if (ex instanceof NoHandlerFoundException) {
            errorMsg = ex.toString();
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            errorMsg = ex.toString();
        } else if (ex instanceof MissingServletRequestParameterException) {
            errorMsg = ex.toString();
        } else if (ex instanceof TypeMismatchException) {
            errorMsg = ex.toString();
//        } else if (ex instanceof NoSuchRequestHandlingMethodException) {
//            errorMsg = ex.toString();
        } else if (ex instanceof MethodArgumentNotValidException) {
            // hibernate-validated验证框架验证参数出错时抛出的异常。请求方法中的参数后面不能有BindingResult，否则不抛异常。
            errorMsg = ex.toString();
        }

//        if (errorMsg != null) {
        try {
            response.getOutputStream().write(ex.getMessage().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return new ModelAndView();
        return null;
//        }

//        return super.resolveException(request, response, handler, ex);
    }

}
