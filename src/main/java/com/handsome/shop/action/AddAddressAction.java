package com.handsome.shop.action;

import com.handsome.shop.bean.Address;
import com.handsome.shop.bean.Customer;
import com.handsome.shop.dao.AddressDao;
import com.handsome.shop.framework.ActionSupport;
import com.handsome.shop.framework.DaoFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * by wangrongjun on 2017/6/20.
 */
public class AddAddressAction extends ActionSupport {
    @Override
    protected String execute() throws ServletException, IOException, ParamErrorException, CustomerNotExistsException {
        Customer customer = checkCustomer();
        String address = checkStringParameter("address");

        AddressDao addressDao = DaoFactory.getAddressDao();
        Address newAddress = new Address(customer, address);
        addressDao.insert(newAddress);

        List<Address> addressList = (List<Address>) request.getSession().getAttribute("addressList");
        if (addressList != null) {
            addressList.add(0, new Address(null, address));
        }
        return "-create_order.jsp";
    }
}
