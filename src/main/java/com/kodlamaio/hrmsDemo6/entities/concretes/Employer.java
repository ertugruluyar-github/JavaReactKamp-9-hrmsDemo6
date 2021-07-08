package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodlamaio.hrmsDemo6.entities.abstracts.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "employers")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisements"})
//@JsonIgnoreProperties bende çalışmıyor. Direkt field'ın üstüne ekleyerek yaptım.
public class Employer extends User {

	@Column(name = "company_name", nullable = false)
	@NotNull
	@NotBlank
	private String companyName;

	@Column(name = "web_site", nullable = false)
	@NotNull
	@NotBlank
	private String webSite;

	@Column(name = "employer_last_update_json_string")
	private String employerLastUpdateJsonString;

	@Column(name = "on_update_process_status", nullable = false)
	@NotNull
	private boolean OnUpdateProcessStatus = false;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
	private PhoneNumber phoneNumber;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE)
	private SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE)
	private EmailConfirmToEmployer emailConfirmToEmployer;

	@JsonIgnore
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobAdvertisement> jobAdvertisements;
	// Burada JsonIgnore diyerek Json verisi çekerken bunu alamayağız.
	// Bunu neden hep böyle bidirectional yapmak zorundayız ki?
	// Zaten JobAdvertisement'den hem city hem employer bilgisine ulaşabiliyoruz.
	// Burayı(bu üç satırı) silerek unidirectional ilişki yapsak yine aynı json
	// bilgisini alabiliyoruz.
	// Neden hep bidirectional yaptığımızı anlamadım.
	// Bidirectional yapınca bu cascade yapabilme avantajı var(sonradan farkettim).

	public Employer(Integer id, String email, String password, String companyName, String webSite) {
		super(id, email, password);
		this.companyName = companyName;
		this.webSite = webSite;
	}

	public Employer(String email, String password, String companyName, String webSite) {
		super(email, password);
		this.companyName = companyName;
		this.webSite = webSite;
	}

}
