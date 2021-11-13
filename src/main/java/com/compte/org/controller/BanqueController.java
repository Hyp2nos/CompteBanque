package com.compte.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compte.org.enteties.Compte;
import com.compte.org.enteties.Operation;
import com.compte.org.metier.IBanqueMetier;

@Controller
public class BanqueController {

	@Autowired
	private IBanqueMetier banque;
	
	@RequestMapping("/operations")
	public String index() {
		
		return "compte";
	}
	
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCompte ) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp = banque.consulterCompte(codeCompte);
			Page<Operation> page  = (Page<Operation>) banque.listOperation(codeCompte, 0, 0);
			model.addAttribute("compte", cp);	
			
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		return "compte";
	}
}
