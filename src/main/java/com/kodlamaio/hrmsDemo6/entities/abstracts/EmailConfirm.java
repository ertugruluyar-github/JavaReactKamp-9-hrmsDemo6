package com.kodlamaio.hrmsDemo6.entities.abstracts;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="email_confirms")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EmailConfirm extends Confirm {
	
	@Column(name="is_confirm", nullable = false)
	@NotNull
	@NotBlank
	private boolean isConfirm = false;
	
	@Column(name="date_of_confirm")
	@NotNull
	@PastOrPresent
	private LocalDate dateOfConfirm = LocalDate.now();
	
	public void setDateOfConfirm(LocalDate dateOfConfirm) {
		this.dateOfConfirm = LocalDate.now();
	}
	
}