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
		System.out.println("service");
		
		List<EmployeEntity> ees=dao.findByName(ee.getName());
		

		if(ees.isEmpty()){
			System.out.println("write it");
			return dao.save(ee);			
		}else {
			System.out.println("add null id");
			
			ee.setId(null);
			return ee;
		}
		

	}
	
	public List<EmployeEntity> findAll(){
		return (List<EmployeEntity>) dao.findAll();
	}

	public List<EmployeEntity> fillAllDiff(String diff) {
		return dao.findDistinct(diff);
	}
	
}
