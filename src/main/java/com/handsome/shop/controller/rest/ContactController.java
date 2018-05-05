package com.handsome.shop.controller.rest;

import com.handsome.shop.dao.ContactDao;
import com.handsome.shop.entity.Contact;
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
@ResponseBody
@RequestMapping("/rest/customer/{customerId}/contact")
public class ContactController {

    @Resource
    private ContactDao contactDao;

    @GetMapping
    public List<Contact> list(@PathVariable("customerId") int customerId) {
        List<Contact> contactList = contactDao.queryByCustomerId(customerId);
        return contactList;
    }

    @PostMapping
    public RequestStatus add(@PathVariable("customerId") int customerId,
                             @Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().get(0).getDefaultMessage());
        }
        contactDao.insert(contact);
        if (contact.isDefaultContact()) {
            contactDao.setDefaultContact(customerId, contact.getContactId());
        }
        return RequestStatus.success();
    }

    @PutMapping("/default")
    public RequestStatus setDefaultContact(@PathVariable("customerId") int customerId,
                                           @NotNull int defaultContactId) {
        contactDao.setDefaultContact(customerId, defaultContactId);
        return RequestStatus.success();
    }

}
