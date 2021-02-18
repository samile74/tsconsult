package com.ts.consult.employe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ts.consult.employe.entities.EmployeEntity;

@Service
public interface EmployeDao extends CrudRepository<EmployeEntity, Long>{

	@Query(value="SELECT  u FROM EmployeEntity u GROUP BY ?1") 
	List<EmployeEntity> findDistinct(String column);

	List<EmployeEntity> findByName(String name);
	
}
