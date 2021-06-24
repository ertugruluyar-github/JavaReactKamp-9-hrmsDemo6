package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodlamaio.hrmsDemo6.entities.abstracts.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name="employers")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisements"})
//@JsonIgnoreProperties bende çalışmıyor. Direkt field'ın üstüne ekleyerek yaptım.
public class Employer extends User {
	
	@Column(name="company_name", nullable = false)
	@NotNull
	@NotBlank
	private String companyName;
	
	@Column(name="web_site", nullable = false)
	@NotNull
	@NotBlank
	private String webSite;
	
	@Column(name="employer_last_update")
	private String employerLastUpdateJsonString;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employer")
	private List<JobAdvertisement> jobAdvertisements;
	// Burada JsonIgnore diyerek Json verisi çekerken bunu alamayağız.
	// Bunu neden hep böyle bidirectional yapmak zorundayız ki?
	// Zaten JobAdvertisement'den hem city hem employer bilgisine ulaşabiliyoruz.
	// Burayı(bu üç satırı) silerek unidirectional ilişki yapsak yine aynı json bilgisini alabiliyoruz.
	// Neden hep bidirectional yaptığımızı anlamadım.
	
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
