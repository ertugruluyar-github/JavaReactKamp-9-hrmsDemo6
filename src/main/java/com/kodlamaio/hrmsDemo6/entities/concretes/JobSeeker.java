package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "job_seekers")
public class JobSeeker extends User {

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
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CurriculumVitae> curriculumVitaes = new ArrayList<CurriculumVitae>();

	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE)
	private EmailConfirmToJobSeeker emailConfirmToJobSeeker;

	@ManyToMany
	@JoinTable(name = "job_seekers_who_like_it_favourite_job_advertisements", joinColumns = {
			@JoinColumn(name = "job_seeker_who_like_it_id") }, inverseJoinColumns = {
					@JoinColumn(name = "favourite_job_advertisement_id") })
	private Set<JobAdvertisement> favouriteJobAdvertisements = new HashSet<JobAdvertisement>();

	public JobSeeker(Integer id, String email, String password, String firstName, String lastName, String nationalityId,
			LocalDate dateOfBirth, String gender) {
		super(id, email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalityId = nationalityId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public JobSeeker(String email, String password, String firstName, String lastName, String nationalityId,
			LocalDate dateOfBirth, String gender) {
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalityId = nationalityId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public boolean addJobAdvertisement(JobAdvertisement jobAdvertisement) {
		return this.favouriteJobAdvertisements.add(jobAdvertisement)
				&& jobAdvertisement.getJobSeekersWhoLikeIt().add(this);
	}

	public boolean removeJobAdvertisement(JobAdvertisement jobAdvertisement) {
		return this.favouriteJobAdvertisements.remove(jobAdvertisement)
				&& jobAdvertisement.getJobSeekersWhoLikeIt().remove(this);
	}

}
