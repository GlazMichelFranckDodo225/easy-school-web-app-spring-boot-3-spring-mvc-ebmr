package com.dgmf.repository;

import com.dgmf.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
// public interface ContactRepository extends CrudRepository<Contact, Integer> {
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByStatus(String status);
    // @Query("SELECT c FROM Contact c WHERE c.status = :status")
    @Query(
            value = "SELECT * FROM contact_msg c WHERE c.status = :status",
            nativeQuery = true
    )
    Page<Contact> findByStatus(@Param("status") String status, Pageable pageable);
}
