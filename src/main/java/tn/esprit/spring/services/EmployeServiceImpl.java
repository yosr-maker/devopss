package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	private static final Logger l = Logger.getLogger(EmployeServiceImpl.class);

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	//yousr(sonar)
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Optional<Employe> value= employeRepository.findById(employeId);
		if (value.isPresent()) {
		Employe employe = value.get();
		employe.setEmail(email);
		employeRepository.save(employe);}

	}

//yousr(sonar)
	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		l.info("affecterEmployeADepartement loading...");

		
		Optional<Departement> value = deptRepoistory.findById(depId);
		Optional<Employe> valuee = employeRepository.findById(employeId);

		if (value.isPresent()) {
			Departement depManagedEntity = value.get();
		

			if (valuee.isPresent()) {
				Employe employeManagedEntity = valuee.get();
			

			
			if(depManagedEntity.getEmployes() == null){

				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
			}else{

				depManagedEntity.getEmployes().add(employeManagedEntity);
			}

			// à ajouter? 
			deptRepoistory.save(depManagedEntity); 
			}	}
	
	}
	
	//yousr(sonar)
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		l.info("desaffecterEmployeDuDepartement loading...");
		
		
		Optional<Departement> value = deptRepoistory.findById(depId);

		if (value.isPresent()) {
			Departement dep = value.get();
			
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
	}
		}

	
	public int ajouterContrat(Contrat contrat) {
		l.info("ajouterContrat loading...");
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

//yousr(sonar)
	public void affecterContratAEmploye(int contratId, int employeId) {
		l.info("affecterContratAEmploye loading...");

		Optional<Contrat> value = contratRepoistory.findById(contratId);
		Optional<Employe> valuee = employeRepository.findById(employeId);

		
		if (value.isPresent()) {
			Contrat contratManagedEntity = value.get();
		

			if (valuee.isPresent()) {
				Employe employeManagedEntity = valuee.get();
				
				contratManagedEntity.setEmploye(employeManagedEntity);
				contratRepoistory.save(contratManagedEntity);
			}
		}

	}
//yousr(sonar)
	public String getEmployePrenomById(int employeId) {
		l.info("getEmployePrenomById loading...");
		
		Optional<Employe> value = employeRepository.findById(employeId);
		Employe employeManagedEntity = new Employe();
		if (value.isPresent()) {
			employeManagedEntity = value.get();
			

		}
		return employeManagedEntity.getPrenom();
	}
	
	//yousr(sonar)
	public void deleteEmployeById(int employeId)
	{
		l.info("deleteEmployeById loading...");

		Optional<Employe> value = employeRepository.findById(employeId);

		if (value.isPresent()) {
			Employe employe = value.get();
		
		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
	}
	}
	
	//yousr(sonar)
	public void deleteContratById(int contratId) {
		Optional<Contrat> value = contratRepoistory.findById(contratId);

		if (value.isPresent()) {
			Contrat contratManagedEntity = value.get() ;
		contratRepoistory.delete(contratManagedEntity);

	}
	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return  employeRepository.findAll();
	}

}
