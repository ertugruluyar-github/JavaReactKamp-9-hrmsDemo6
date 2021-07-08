package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="technology_knowledges")
public class TechnologyKnowledge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name", nullable = false)
	@NotNull
	@NotBlank
	private String name;
	
	@Column(name = "add_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDate addDate = LocalDate.now();
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;
	
	
	public void setAddDate(LocalDate addDate) {
		this.addDate = LocalDate.now();
	} 
	
}
