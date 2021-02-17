package com.ts.consult.employe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class EmployeEntity implements Serializable{
	private static final long serialVersionUID = 3816187165509452345L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "employe_name")
	private String name;
	
	@Column(name = "employe_firstname")
	private String firstname;
	
	@Column(name = "employe_adress")
	private String adress;
	
}
