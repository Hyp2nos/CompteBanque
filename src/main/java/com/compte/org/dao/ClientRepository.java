package com.compte.org.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compte.org.enteties.Client;

public interface ClientRepository  extends JpaRepository<Client, Long>{

}
