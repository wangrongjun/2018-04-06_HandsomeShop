package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.entity.Contact;
import com.handsome.shop.entity.Customer;
import com.handsome.shop.util.RequestStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * by wangrongjun on 2018/5/4.
 */
@Controller
@RequestMapping("/rest/customer/{customerId}/contact")
public class ContactController {

    @Resource
    private ContactDao contactDao;

    @GetMapping
    public List<Contact> list(@PathVariable int customerId) {
        return contactDao.queryByCustomerId(customerId);
    }

    @PostMapping
    public RequestStatus<Integer> add(@PathVariable int customerId,
                             @Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().get(0).getDefaultMessage());
        }
        contact.setCustomer(new Customer(customerId));
        contactDao.insert(contact);
        return RequestStatus.success(contact.getContactId());
    }

    @PutMapping("/default")
    public RequestStatus setDefaultContact(@PathVariable int customerId,
                                           @NotNull int defaultContactId) {
        contactDao.setDefaultContact(customerId, defaultContactId);
        return RequestStatus.success();
    }

}
