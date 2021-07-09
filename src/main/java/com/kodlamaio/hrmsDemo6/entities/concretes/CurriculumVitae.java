package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curriculum_vitaes")
public class CurriculumVitae {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "photo_link")
	private String photoLink;

	@Column(name = "github_link")
	private String githubLink;

	@Column(name = "linkedin_link")
	private String linkedinLink;

	@Column(name = "description")
	private String description;

	@Column(name = "create_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDate createDate = LocalDate.now();;

	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<School> schools;

	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WorkExperience> workExperiences;

	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Language> languages;

	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TechnologyKnowledge> technologyKnowledges;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_seeker_id")// Yazmaya gerek yok ama kod okunurluluğu için iyidir.
	private JobSeeker jobSeeker;

	public void setCreateDate(LocalDate createDate) {
		this.createDate = LocalDate.now();
	}

}
