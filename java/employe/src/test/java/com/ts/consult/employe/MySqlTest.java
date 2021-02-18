package com.ts.consult.employe;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ts.consult.employe.controler.EmployeeRestController;
import com.ts.consult.employe.dto.ReceiveDto;
import com.ts.consult.employe.entities.EmployeEntity;

@RunWith(SpringRunner.class )
@SpringBootTest
public class MySqlTest {
	
	@Autowired
    private EmployeeRestController rest;
	
	@Test
	public void mysqlTest() {
		List<EmployeEntity> ees;
		
		EmployeEntity alext = new EmployeEntity();
		alext.setName("Dupont");
		alext.setFirstname("Alex");
		
		EmployeEntity alexd = new EmployeEntity();
		alexd.setName("Dupond");
		alexd.setFirstname("Alex");
		
		
		ReceiveDto rdto=new ReceiveDto();
		rdto.setEmploye(alext);
		
		rdto=rest.addEmploye(rdto,null);
		assertTrue(rdto.getEmploye().getName().equals(alext.getName()));
		assertNotNull(rdto.getEmploye().getId());
		
		rdto=rest.addEmploye(rdto,null);
		assertTrue(rdto.getEmploye().getName().equals(alext.getName()));
		assertNull(rdto.getEmploye().getId());
		
		ees =rest.navigation("id");
		assertEquals(1,ees.size());
		
		rdto.setEmploye(alexd);
		rdto=rest.addEmploye(rdto,null);
		assertTrue(rdto.getEmploye().getName().equals(alexd.getName()));
		assertNotNull(rdto.getEmploye().getId());
		
		ees =rest.navigation("id");
		assertEquals(2,ees.size());
		
		ees =rest.navigation("firstname");
		assertEquals(1,ees.size());
		
		
		
		
	}
	
}
