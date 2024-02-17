package com.alexandre.oliveira.crudclients.repositories;

import com.alexandre.oliveira.crudclients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
