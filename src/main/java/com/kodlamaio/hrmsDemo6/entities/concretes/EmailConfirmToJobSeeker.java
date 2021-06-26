package com.kodlamaio.hrmsDemo6.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kodlamaio.hrmsDemo6.entities.abstracts.EmailConfirm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name="email_confirms_to_job_seeker")
public class EmailConfirmToJobSeeker extends EmailConfirm {
	
	@OneToOne//Unidirectional
	private JobSeeker jobSeeker;
	
	public EmailConfirmToJobSeeker(boolean isConfirm) {
		super(isConfirm);
	}
	
	public EmailConfirmToJobSeeker(JobSeeker jobSeeker) {
		super(false);
		this.jobSeeker = jobSeeker;
	}
	
	public EmailConfirmToJobSeeker(boolean isConfirm, JobSeeker jobSeeker) {
		super(isConfirm);
		this.jobSeeker = jobSeeker;
	}
	
}