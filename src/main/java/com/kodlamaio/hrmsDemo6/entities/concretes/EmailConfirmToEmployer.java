package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;

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
@Table(name="email_confirms_to_employer")
public class EmailConfirmToEmployer extends EmailConfirm {
	
	@OneToOne//Unidirectional
	private Employer employer;
	
	public EmailConfirmToEmployer(boolean isConfirm, LocalDate dateOfConfirm) {
		super(isConfirm, dateOfConfirm);
	}
	
}