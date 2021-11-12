package com.compte.org.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compte.org.enteties.Compte;

public interface CompteRepository  extends JpaRepository<Compte, String>{

}
