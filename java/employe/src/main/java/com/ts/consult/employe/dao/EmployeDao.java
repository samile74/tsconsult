package com.ts.consult.employe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ts.consult.employe.entities.EmployeEntity;

@Service
public interface EmployeDao extends CrudRepository<EmployeEntity, Long>{

	@Query(value="SELECT u FROM EmployeEntity u GROUP BY name") 
	List<EmployeEntity> findDistinctName();

	@Query(value="SELECT  u FROM EmployeEntity u GROUP BY firstname") 
	List<EmployeEntity> findDistinctFirstname();

	@Query(value="SELECT  u FROM EmployeEntity u GROUP BY adress") 
	List<EmployeEntity> findDistinctAdress();

	List<EmployeEntity> findByName(String name);
	
}
