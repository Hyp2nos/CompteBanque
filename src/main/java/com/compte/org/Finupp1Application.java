package com.compte.org;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.compte.org.dao.ClientRepository;
import com.compte.org.dao.CompteRepository;
import com.compte.org.dao.OperationRepository;
import com.compte.org.enteties.Client;
import com.compte.org.enteties.Compte;
import com.compte.org.enteties.CompteCourant;
import com.compte.org.enteties.CompteEpargne;
import com.compte.org.enteties.Operation;
import com.compte.org.enteties.Retrait;
import com.compte.org.enteties.Versement;
import com.compte.org.metier.BanqueMetierImplementation;
import com.compte.org.metier.IBanqueMetier;

@SpringBootApplication
public class Finupp1Application implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banque;
	public static void main(String[] args) {
//		ApplicationContext ctx = SpringApplication.run(Finupp1Application.class, args);
//		ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
//		clientRepository.save(new Client("Fifaliana","fifa@gmail.com"));
		SpringApplication.run(Finupp1Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Client c1 = clientRepository.save(new Client("Henintsoa", "henintsoa@gmail"));
		
		Compte cmp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000, c1, 6000));
		Compte cmp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 6000, c1, 5.5));
		
		operationRepository.save(new Versement(new Date(), 9000, cmp1));
		operationRepository.save(new Versement(new Date(), 6000, cmp1));
		operationRepository.save(new Versement(new Date(), 3000, cmp1));
		operationRepository.save(new Retrait(new Date(), 9000, cmp1));
		
		
		operationRepository.save(new Versement(new Date(), 9000, cmp2));
		operationRepository.save(new Versement(new Date(), 6000, cmp2));
		operationRepository.save(new Versement(new Date(), 3000, cmp2));
		operationRepository.save(new Retrait(new Date(), 9000, cmp2));


		banque.verser("c1", 111111111);
	}

}
