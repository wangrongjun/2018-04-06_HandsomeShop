package com.handsome.shop.framework;

import com.handsome.shop.bean.Customer;
import com.wangrj.java_lib.java_util.TextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wangrongjun on 2017/6/23.
 */
public abstract class ActionSupport implements Action {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected abstract String execute() throws ServletException, IOException,
            ParamErrorException, CustomerNotExistsException;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        try {
            return execute();
        } catch (ParamErrorException e) {
            com.wangrj.java_lib.java_util.LogUtil.print(e.toString());
            return "-error_param.jsp";
        } catch (CustomerNotExistsException e) {
            request.setAttribute("msg", "请登录");
            return "login.jsp";
        }
    }

    protected int getIntegerParameter(String parameterName, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    protected int checkIntegerParameter(String parameterName) throws ParamErrorException {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (NumberFormatException e) {
            throw new ParamErrorException(e.getMessage());
        }
    }

    protected String checkStringParameter(String parameterName) throws ParamErrorException {
        String parameter = request.getParameter(parameterName);
        if (TextUtil.isEmpty(parameter)) {
            throw new ParamErrorException("parameter not exists: " + parameterName);
        }
        return parameter;
    }

    protected Customer checkCustomer() throws CustomerNotExistsException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer == null) {
            throw new CustomerNotExistsException();
        }
        return customer;
    }

    protected class ParamErrorException extends Exception {
        ParamErrorException(String message) {
            super(message);
        }
    }

    protected class CustomerNotExistsException extends Exception {
    }

}
