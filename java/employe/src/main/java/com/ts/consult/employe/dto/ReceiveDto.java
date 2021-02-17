package com.ts.consult.employe.dto;

import com.ts.consult.employe.entities.EmployeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ReceiveDto {
	String action;
	EmployeEntity employe;
	String error;
}
