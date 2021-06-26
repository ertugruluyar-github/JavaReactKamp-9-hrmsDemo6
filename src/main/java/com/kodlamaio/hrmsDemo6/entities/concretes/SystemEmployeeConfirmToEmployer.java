package com.kodlamaio.hrmsDemo6.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kodlamaio.hrmsDemo6.entities.abstracts.SystemEmployeeConfirm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name="system_employee_confirms_to_employer")
public class SystemEmployeeConfirmToEmployer extends SystemEmployeeConfirm {
	
	@OneToOne//Unidirectional
	private Employer employer;
	
	public SystemEmployeeConfirmToEmployer(boolean isConfirm) {
		super(isConfirm);
	}
	
	public SystemEmployeeConfirmToEmployer(Employer employer) {
		super(false);
		this.employer = employer;
	}
	
	public SystemEmployeeConfirmToEmployer(boolean isConfirm, Employer employer) {
		super(isConfirm);
		this.employer = employer;
	}
	
}