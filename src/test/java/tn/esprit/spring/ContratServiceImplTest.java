
package tn.esprit.spring;

import org.junit.Before;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Contrat;

import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.ContratService;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {

	

Logger logger = LoggerFactory.getLogger(ContratServiceImplTest.class);
	@Autowired
	ContratService cs;
	@Autowired 
	IEmployeService iemployeservice;
	@Autowired 
	ContratRepository contratRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	
	
	
	@Before
    public void setup1() {
		contratRepository.deleteAll();
        logger.info("intialisation done");
    }
	
	@Before
    public void setup2() {
		contratRepository.deleteAll();
        logger.info("intialisation done");
    }
	
	@Before
    public void setup3() {
		contratRepository.deleteAll();
        logger.info("intialisation done");
    }
	
	
	
	
	
	@Test(timeout =2000)
	public void testgetAllContrats() {
		List<Contrat> contrats = contratRepository.findAll();
		assertThat(contrats).size().isNotEqualTo(null);
		logger.info("la liste des contrats est :", contrats);
	}



	@Test(timeout =2000)
	public void testajouterContrat() throws ParseException {
		try{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = dateFormat.parse("2021-06-09");
	Contrat u = new Contrat(1, date,"CDD",200);
	iemployeservice.ajouterContrat(u);
	logger.info("ajout avec succés de contrat: " , u.getReference());
	}	catch(Exception e){
		logger.error("Erreur dans addcontrat() : " + e);
		
	}
		
	}


	
	
@Test
	public void testdeleteContratById() {
	
				Contrat c = new Contrat(1, "CDD", 2);
				iemployeservice.ajouterContrat(c);
				iemployeservice.deleteContratById(c.getReference());
				 assertThat(contratRepository.findAll()).isEmpty();
        		logger.info("deleted successfully" );
				
}	



	 @Test
		public void deleteAllContratJPQL() {		
		iemployeservice.deleteAllContratJPQL() ;
		assertThat(contratRepository.findAll().isEmpty() );
		logger.info("deleted successfully" );		
	 }



	@Test(timeout =3000)
	public void testretrieveParDateJpql() throws ParseException {
		try{
	SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
	Date date1= date.parse("2020-01-01");
	Date date2= date.parse("2021-12-30");
	cs.retrieveParDateJpql(date1,date2);
	logger.info("test retrieveParDateJpql avec succes");
	}	catch(Exception e){
		logger.error("Erreur dans retrieveDate() : " + e);
		
	}
	}
	
	
	
}
