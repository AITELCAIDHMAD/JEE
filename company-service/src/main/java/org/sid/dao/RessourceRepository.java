package org.sid.dao;

import org.sid.entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RessourceRepository extends JpaRepository<Ressource, Long> { 
}
