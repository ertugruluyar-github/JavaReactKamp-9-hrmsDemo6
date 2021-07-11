package com.kodlamaio.hrmsDemo6.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "country_code", nullable = false)
	@NotNull
	@NotBlank
	private String countryCode;

	@Column(name = "phone_number", nullable = false)
	@NotNull
	@NotBlank
	private String phoneNumber;

	// özellik adı(employer) ile Employer nesnesinin id özelliğini alt çizgi ile
	// birleştirip phone_numbers tablosuna employer_id adında bir column ekleyecek.
	// @JoinColumn(name = "e_id") tabloya eklenecek column un adını değiştirir.
	@JsonIgnore
	@OneToOne
	private Employer employer;

	public PhoneNumber(Integer id, String countryCode, String phoneNumber) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
	}

	public PhoneNumber(String countryCode, String phoneNumber) {
		super();
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
	}

}
