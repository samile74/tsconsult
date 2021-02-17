package com.ts.consult.employe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.consult.employe.dao.EmployeDao;
import com.ts.consult.employe.entities.EmployeEntity;

@Service
public class EmployeService {
	
	@Autowired
	EmployeDao dao;
	
	public EmployeEntity addEmploye(EmployeEntity ee) {
		
		List<EmployeEntity> ees=dao.findByName(ee.getName());
		
		if(ees.isEmpty()){
			return dao.save(ee);			
		}else {
			ee.setId(null);
			return ee;
		}
		

	}
	
	public List<EmployeEntity> findAll(){
		return (List<EmployeEntity>) dao.findAll();
	}

	public List<EmployeEntity> fillAllDiff(String diff) {
		
		switch(diff) {
		case "id":
			return findAll();
		case "name":
			return dao.findDistinctName();
		case "firstname":
			return dao.findDistinctFirstname();
		case "adress":
			return dao.findDistinctAdress();
		default:
			return findAll();
		}
			
	}
	
}
