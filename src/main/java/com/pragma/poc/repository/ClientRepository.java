package com.pragma.poc.repository;


import com.pragma.poc.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {


    boolean existsById(int id);
    Client findById(int id);
}
