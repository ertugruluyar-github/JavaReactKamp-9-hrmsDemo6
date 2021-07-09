package com.kodlamaio.hrmsDemo6.entities.concretes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_advertisements")
public class JobAdvertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "description", nullable = false) // zorunlu
	@NotNull
	@NotBlank
	private String description;

	@Column(name = "min_salary") // opsiyonel
	@Positive
	private int minSalary;

	@Column(name = "max_salary") // opsiyonel
	@Positive
	private int maxSalary;

	@Column(name = "number_of_position", nullable = false) // zorunlu
	@NotNull
	@Positive
	private short numberOfPosition;

	@Column(name = "application_deadline")
	@Future
	private LocalDate applicationDeadline;

	@Column(name = "release_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDate releaseDate = LocalDate.now();
	// Json'da releaseDate gönderilmezse. Null bırakamaz ama yinede olsun.

	@Column(name = "active", nullable = false)
	@NotNull
	private boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_position_id")
	private EmployeePosition employeePosition;

	// Default olarak sütun ismini, field adı(city) ile City'nin primary key
	// adını(id)
	// "_" ile birleştirerek city_id şeklinde oluşturur. Ekstra bu adı düzenlemek
	// için JoinColumn'a ihtiyaç yoktur.
	// ama kod okunurluğu için iyi oluyor.
	@ManyToOne(fetch = FetchType.LAZY) // (cascade = CascadeType.PERSIST)// JobAdvertisement kaydedince City'yi de
										// otomatik kaydeder.
	@JoinColumn(name = "city_id")
	private City city;
	// Henüz sistemde hazır kayıtlı şehirler olmadığı için iş ilanı kaydederken
	// otoatik city'yi de kaydetmesi için cascade koyulabilir.
	// şehirleri önceden veri tabanına kaydedersek buna gerek kalmaz.

	// Default olarak sütun ismini, field adı(employer) ile Employer'ın primary key
	// adını(id)
	// "_" ile birleştirerek employer_id şeklinde oluşturur. Ekstra bu adı
	// düzenlemek için JoinColumn'a ihtiyaç yoktur.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employer_id")
	private Employer employer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "working_place_type_id")
	private WorkingPlaceType workingPlaceType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "working_time_type_id")
	private WorkingTimeType workingTimeType;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE)
	private SystemEmployeeConfirmToJobAdvertisement systemEmployeeConfirmToJobAdvertisement;

	@JsonIgnore
	@ManyToMany(mappedBy = "favouriteJobAdvertisements")
	private Set<JobSeeker> jobSeekersWhoLikeIt = new HashSet<JobSeeker>();

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = LocalDate.now();
		// Json'da releaseDate gönderilmeye çalışılırsa işe yaramayacak.
	}

}
