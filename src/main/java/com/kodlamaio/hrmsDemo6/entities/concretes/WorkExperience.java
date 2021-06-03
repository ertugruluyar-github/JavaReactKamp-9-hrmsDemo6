package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="work_experiences")
public class WorkExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "work_place_name", nullable = false)
	@NotNull
	@NotBlank
	private String workPlaceName;
	
	@Column(name = "position_name", nullable = false)
	@NotNull
	@NotBlank
	private String positionName;
		
	@Column(name = "start_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@PastOrPresent
	private LocalDate endDate;
	
	@Column(name = "add_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDate addDate = LocalDate.now();
	
	
	@ManyToOne
	@JoinColumn(name = "curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;
	
	
	public void setAddDate(LocalDate addDate) {
		this.addDate = LocalDate.now();
	} 
	
}
