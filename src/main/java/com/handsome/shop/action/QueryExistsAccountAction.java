package com.handsome.shop.action;

import com.handsome.shop.dao.CustomerDao;
import com.handsome.shop.framework.DaoFactory;
import com.handsome.shop.framework.ActionSupport;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * by wangrongjun on 2017/9/22.
 */
public class QueryExistsAccountAction extends ActionSupport {

    @Override
    protected String execute() throws
            ServletException, IOException, ParamErrorException, CustomerNotExistsException {
        String phone = checkStringParameter("phone");
        CustomerDao customerDao = DaoFactory.getCustomerDao();
        boolean exists = customerDao.queryByPhone(phone) != null;
        response.getWriter().write(String.valueOf(exists));
        return null;
    }

}
