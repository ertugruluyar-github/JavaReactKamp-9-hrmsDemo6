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
@Table(name="system_employee_confirms_to_job_advertisement")
public class SystemEmployeeConfirmToJobAdvertisement extends SystemEmployeeConfirm {
	
	@OneToOne
	private JobAdvertisement jobAdvertisement;
	
	public SystemEmployeeConfirmToJobAdvertisement(boolean confirmStatus) {
		super(confirmStatus);
	}
	
	public SystemEmployeeConfirmToJobAdvertisement(JobAdvertisement jobAdvertisement) {
		super(false);
		this.jobAdvertisement = jobAdvertisement;
	}
	
	public SystemEmployeeConfirmToJobAdvertisement(boolean confirmStatus, JobAdvertisement jobAdvertisement) {
		super(confirmStatus);
		this.jobAdvertisement = jobAdvertisement;
	}
	
}
