package com.pragma.poc.repository;


import com.pragma.poc.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {


    public Boolean existsById(int id);
    public Client findById(int id);
}
