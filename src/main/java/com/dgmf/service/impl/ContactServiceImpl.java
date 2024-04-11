package com.dgmf.service.impl;

import com.dgmf.entity.Contact;
import com.dgmf.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private static Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Override
    public Boolean saveMessageDetails(Contact contact) {
        Boolean isSaved = true;
        // TODO - Need to Persist the Data into the DB
        log.info(contact.toString());

        return isSaved;
    }
}
