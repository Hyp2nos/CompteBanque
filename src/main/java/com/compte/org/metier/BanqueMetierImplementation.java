package com.compte.org.metier;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compte.org.dao.CompteRepository;
import com.compte.org.dao.OperationRepository;
import com.compte.org.enteties.Compte;
import com.compte.org.enteties.CompteCourant;
import com.compte.org.enteties.Retrait;
import com.compte.org.enteties.Versement;

@Service
@Transactional
public class BanqueMetierImplementation implements IBanqueMetier {
	@Autowired
	private CompteRepository compteRepository;
	
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		Compte cp = compteRepository.findById(codeCpte).orElse(null);
		if (cp==null) throw new RuntimeException("Compte  introuvable");		
		return cp;
		
	}

	@Override
	public void verser(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Compte cp = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSold(cp.getSold()+ montant);
		compteRepository.save(cp);

	}

	@Override
	public void retirer(String codeCpte, double montant) {
		// TODO Auto-generated method stub
		Compte cp = consulterCompte(codeCpte);
		double facilitesCaisse=0;
		if (cp instanceof CompteCourant) {
			facilitesCaisse=((CompteCourant) cp).getDecouvert();
		}
		if (cp.getSold()+ facilitesCaisse<montant) {
			throw new RuntimeException("Solde insuffisant");
		}
		Retrait v = new Retrait(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSold(cp.getSold() - montant);
		compteRepository.save(cp);

	}

	@Override
	public void virement(String codeCpte1, String codeCpt2, double montant) {
		// TODO Auto-generated method stub
		retirer(codeCpte1, montant);
		verser(codeCpte1, montant);

	}
//
//	@Override
//	public Pageable listOperation(String codeCpte, int page, int size) {
//		// TODO Auto-generated method stub
//		return operationRepository.listOperation(codeCpte, new QPageRequest(page, size));
//	}

	@Override
	public Pageable listOperation(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

//	@SuppressWarnings({ "deprecation", "unchecked" })
//	@Override
//	public Pageable listOperation(String codeCpte, int page, int size) {
//		// TODO Auto-generated method stub
//		List<Order> list = new ArrayList<>();
//		list.addAll("dateOperation");
//		Sort sort = new Sort(list);
//		return  operationRepository.listOperation(codeCpte, new PageRequest(page, size, Sort.Direction.ASC));
//	}

}
