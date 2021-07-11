package com.kodlamaio.hrmsDemo6.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "system_employee_confirms_to_employer")
public class SystemEmployeeConfirmToEmployer extends SystemEmployeeConfirm {

	@OneToOne
	private Employer employer;

	// Yukarıdaki employer'ı ıgnore edip buraya bir employerUpdatejson ekleyerek
	// tüm updatelerin kaydını tutabilirim. dateOfConfirm'de LocalDate den
	// LocalDateTime'a geçtiğim için
	// date'e göre en son - güncel - confirm'e ulaşırken sıkıntı olmaz.

	@ManyToOne
	@JoinColumn(name = "system_employee_id")
	private SystemEmployee systemEmployee;

	public SystemEmployeeConfirmToEmployer(boolean confirmStatus) {
		super(confirmStatus);
	}

	public SystemEmployeeConfirmToEmployer(Employer employer) {
		super(false);
		this.employer = employer;
	}

	public SystemEmployeeConfirmToEmployer(boolean confirmStatus, Employer employer) {
		super(confirmStatus);
		this.employer = employer;
	}

}