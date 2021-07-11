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

/**
 * @author Ertuğrul Uyar
 * @LinkedIn www.linkedin.com/in/ertugruluyar
 * @GitHub https://github.com/euyar42
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="system_employee_confirms")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SystemEmployeeConfirm extends Confirm {
	
	@Column(name="confirm_status", nullable = false)
	@NotNull
	private boolean confirmStatus = false;
	
	@Column(name="date_of_confirm")
	@NotNull
	@PastOrPresent
	private LocalDateTime dateOfConfirm = LocalDateTime.now();
	
	public SystemEmployeeConfirm(boolean confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	
	public void setDateOfConfirm(LocalDateTime dateOfConfirm) {
		this.dateOfConfirm = LocalDateTime.now();
	}
	
}