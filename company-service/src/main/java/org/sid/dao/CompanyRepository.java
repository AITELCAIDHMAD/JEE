package org.sid.dao;

import org.sid.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource //spring data rest exposer webservice
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
