package com.geneinsure.cqmservice.repository;

import com.geneinsure.cqmservice.model.entity.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, String> {
}
