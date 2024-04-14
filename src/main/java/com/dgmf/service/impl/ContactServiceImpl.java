package com.dgmf.service.impl;

import com.dgmf.entity.Contact;
import com.dgmf.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {
    // Already Done by "@Slf4j" Lombok Annotation
    // private static Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Override
    public Boolean saveMessageDetails(Contact contact) {
        Boolean isSaved = true;
        // TODO - Need to Persist the Data into the DB
        log.info(contact.toString());

        return isSaved;
    }
}
