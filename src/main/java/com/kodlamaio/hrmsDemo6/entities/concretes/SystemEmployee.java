package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodlamaio.hrmsDemo6.entities.abstracts.User;

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
@Entity
@Table(name = "system_employees")
public class SystemEmployee extends User {

	@Column(name = "first_name", nullable = false)
	@NotNull
	@NotBlank
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotNull
	@NotBlank
	private String lastName;

	@Column(name = "nationality_id", nullable = false, unique = true)
	@NotNull
	@NotBlank
	private String nationalityId;

	@Column(name = "date_of_birth", nullable = false)
	@NotNull
	@Past
	private LocalDate dateOfBirth;

	@Column(name = "gender", nullable = false)
	@NotNull
	@NotBlank
	private String gender;

	@JsonIgnore
	@OneToMany(mappedBy = "systemEmployee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SystemEmployeeConfirmToEmployer> systemEmployeeConfirmToEmployers = new ArrayList<SystemEmployeeConfirmToEmployer>();

	@JsonIgnore
	@OneToMany(mappedBy = "systemEmployee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SystemEmployeeConfirmToJobAdvertisement> systemEmployeeConfirmToJobAdvertisements = new ArrayList<SystemEmployeeConfirmToJobAdvertisement>();

	public SystemEmployee(Integer id, String email, String password, String firstName, String lastName,
			String nationalityId, LocalDate dateOfBirth, String gender) {
		super(id, email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalityId = nationalityId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public SystemEmployee(String email, String password, String firstName, String lastName, String nationalityId,
			LocalDate dateOfBirth, String gender) {
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalityId = nationalityId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

}
