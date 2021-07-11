package com.kodlamaio.hrmsDemo6.entities.abstracts;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
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
	
	@Column(name="confirm_status", nullable = false)
	@NotNull
	private boolean confirmStatus = false;
	
	@Column(name="date_of_confirm")
	@NotNull
	@PastOrPresent
	private LocalDateTime dateOfConfirm = LocalDateTime.now();
	
	public EmailConfirm(boolean confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	
	public void setDateOfConfirm(LocalDateTime dateOfConfirm) {
		this.dateOfConfirm = LocalDateTime.now();
	}
	
}