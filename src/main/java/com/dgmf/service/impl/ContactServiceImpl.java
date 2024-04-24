package com.dgmf.service.impl;

import com.dgmf.entity.Contact;
import com.dgmf.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Slf4j
// @RequestScope
@SessionScope
public class ContactServiceImpl implements ContactService {
    // Already Done by "@Slf4j" Lombok Annotation
    // private static Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);
    private int counter = 0;

    public ContactServiceImpl() {
        System.out.println("Contact Service Implementation Bean initialized");
    }

    @Override
    public Boolean saveMessageDetails(Contact contact) {
        Boolean isSaved = true;
        // TODO - Need to Persist the Data into the DB
        log.info(contact.toString());

        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
