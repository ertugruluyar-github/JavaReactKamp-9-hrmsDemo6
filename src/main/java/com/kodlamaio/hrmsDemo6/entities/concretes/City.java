package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisements"})
//@JsonIgnoreProperties bende çalışmıyor. Direkt field'ın üstüne ekleyerek yaptım.
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", nullable = false)
	@NotNull
	@NotBlank
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "city")
	private List<JobAdvertisement> jobAdvertisements;
	// Burada JsonIgnore diyerek Json verisi çekerken bunu alamayağız.
	// Bunu neden hep böyle bidirectional yapmak zorundayız ki?
	// Zaten JobAdvertisement'den hem city hem employer bilgisine ulaşabiliyoruz.
	// Burayı(bu üç satırı) silerek unidirectional ilişki yapsak yine aynı json
	// bilgisini alabiliyoruz.
	// Neden hep bidirectional yaptığımızı anlamadım.

}
