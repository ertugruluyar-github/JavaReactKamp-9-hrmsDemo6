package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;

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
public class SystemEmployeeConfirmToJobAdvertisement extends SystemEmployeeConfirm {
	
	@OneToOne//Unidirectionals
	private JobAdvertisement jobAdvertisement;
	
	public SystemEmployeeConfirmToJobAdvertisement(boolean isConfirm, LocalDate dateOfConfirm) {
		super(isConfirm, dateOfConfirm);
	}
	
}
