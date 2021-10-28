package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	
	
	
	
	Logger logger = LoggerFactory.getLogger(EntrepriseServiceImplTest.class);
	
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	IDepartementService idepartement;
	
	
	@Test
	public void  testAjouterEntreprise() throws ParseException{
		
		
		Entreprise entreprise = new Entreprise("vitalait", "AAAAAA") ;
		
		int idEntreprise=ientrepriseservice.ajouterEntreprise(entreprise);
		assertEquals(idEntreprise, entreprise.getId());
		
		
		
		 
	}
	
	@Test
	public void testAjouterDepartement(){
		
		
		Departement department = new Departement("ressourceH");
		int idDepartement = ientrepriseservice.ajouterDepartement(department);
		assertEquals(idDepartement, department.getId());
		
		
	}
	
	
	@Test
	public void deleteEntrepriseById(){
		
		Entreprise entreprises = new Entreprise("vitalait", "bbbbbb") ;
		
		ientrepriseservice.ajouterEntreprise(entreprises);
		assertEquals(entreprises, ientrepriseservice.getEntrepriseById(entreprises.getId()));
		logger.info("done : " ,entreprises);
		ientrepriseservice.deleteEntrepriseById(entreprises.getId());
		assertNull(ientrepriseservice.getEntrepriseById(entreprises.getId()));
		logger.info("l entreprise a été supprimée " ); 
	}

}
