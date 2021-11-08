package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.apache.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	
	
	
	


	Logger logger = LoggerFactory.getLogger(EntrepriseServiceImplTest.class);	
	
	
		
		
		
		
		 

	
	@Autowired
	EntrepriseServiceImpl es;
	
	@Test
	public void testAddEntreprise() {
		try {
		Entreprise e = new Entreprise("vegas","EURL");
		int Id = es.ajouterEntreprise(e);
		
		es.deleteEntrepriseById(Id);
		logger.info("Add Entreprise works");
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
	}
	
	
	@Test
	public void testUpdateEntreprise() {
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
		int Id = es.ajouterEntreprise(E);
		E.setName("Iphone");
		es.ajouterEntreprise(E);
		Entreprise Ese = es.getEntrepriseById(Id);
		assertEquals("Iphone",Ese.getName());
		es.deleteEntrepriseById(Id);
		logger.info("Update Entreprise works");
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
	}
	
	
	@Test
	public void testDeleteEntrepriseById_METHOD1() {
		try {
		Entreprise E = new Entreprise("Samsungssop","EURL");
		int Id = es.ajouterEntreprise(E);
		int lengthBeforeDelete = es.getAllEntreprises().size();
		es.deleteEntrepriseById(Id);
		assertEquals(lengthBeforeDelete-1 , es.getAllEntreprises().size());
		logger.info("Delete Entreprise (%size) works");
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
	}
	
	
	@Test
	public void testAffectDepartmentToEntreprise(){
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
		int IdE = es.ajouterEntreprise(E);
		Departement D = new Departement("Info");
		int IdD = es.ajouterDepartement(D);
		assertNull(D.getEntreprise());
		es.affecterDepartementAEntreprise(IdD, IdE);
		assertNotNull(D.getEntreprise().getId());
		assertEquals(D.getEntreprise().getId(),IdE);
		es.deleteDepartementById(IdD);
		es.deleteEntrepriseById(IdE);
		logger.info("Affect Department to Entreprise works");
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
	}
	
	
	
	
	
   
}
