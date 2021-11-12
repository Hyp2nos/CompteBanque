package com.compte.org.metier;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.compte.org.enteties.Compte;
import com.compte.org.enteties.Operation;

public interface IBanqueMetier {
	
	public Compte consulterCompte(String codeCpte);
	public void verser(String codeCpte,double	montant);
	public void retirer(String codeCpte , double montant);
	public void virement(String codeCpte1,String codeCpt2,double montant);
	public Pageable listOperation(String codeCpte , int page , int size);
}
