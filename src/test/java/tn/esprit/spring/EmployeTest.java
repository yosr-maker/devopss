package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class EmployeTest {
	@Autowired
	IEmployeService employeService;

	 EmployeRepository employeRepository;
	 
	 
	 private static final Logger l =
				LogManager.getLogger(EmployeTest.class);
	
	 
@Test
public void testajouterEmploye() {
	
	Employe employe = new Employe("Aissa", "nada", "nada1@gmail.com" , true, Role.CHEF_DEPARTEMENT);
	Employe employe1 = new Employe("Aissa", "nada", "nada1@gmail.com" , true, Role.CHEF_DEPARTEMENT);
	
	employeService.ajouterEmploye(employe);
assertNotNull(employe.getId());
l.info("l'ajout est effectué avec succés : {} ", employe);

}

@Test
public void testdeleteEmploye(){
	employeService.deleteEmployeById(1);	
}



/*@Test
public void testUpdateEmploye () {
	Employe p = employeRepository.findById(1).get();
	p.setPrenom("yosr");
	employeRepository.save(p);
}*/










}